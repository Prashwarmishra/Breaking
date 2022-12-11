package com.example.breaking

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest

class NewsActivity : AppCompatActivity(), Listener {
    companion object {
        const val QUERY = "QUERY"
        const val CATEGORIES = "CATEGORIES"
    }

    private var newsData: MutableList<News> = mutableListOf()
    private val mAdapter = NewsAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val query = intent.getStringExtra(QUERY).toString()
        val categories = intent.getStringExtra(CATEGORIES).toString()

        val recyclerView: RecyclerView = findViewById(R.id.news_recycler_view)
        recyclerView.adapter = mAdapter
        loadData(query, categories)
    }

    private fun loadData(query: String, categories: String) {
        var url =
            "https://gnews.io/api/v4/top-headlines?token=2fb00e7cce5cd8a1431c2aa33a97a6af&lang=en&country=in"

        // update the url with categories and query
        val categoriesList = categories.substring(1, categories.length - 1).split(",")
        if (categoriesList.isNotEmpty()) {
            for (category in categoriesList) {
                url += "&topic=${category.trim()}"
            }
        }

        if (query.isNotEmpty()) {
            url += "&q=${query}"
        }

        val progressBar: ProgressBar = findViewById(R.id.progress_bar)
        progressBar.visibility = View.VISIBLE

        // create a request, format and add to newsData
        val request = JsonObjectRequest(Request.Method.GET, url, null, { request ->
            val dataArray = request.getJSONArray("articles")
            newsData.clear()
            for (i in 0 until dataArray.length()-1) {
                val item = dataArray.getJSONObject(i)
                val source = item.getJSONObject("source")
                val news = News(
                    item.getString("image"),
                    item.getString("title"),
                    source.getString("name"),
                    item.getString("url")
                )
                newsData.add(news)
            }
            // once we have the data, update the adapter data
            mAdapter.updateData(newsData)
        }, { Log.d("failed too fetch news", it.localizedMessage) })

        progressBar.visibility = View.GONE

        // add request to queue
        VolleyQueue(this).addToRequestQueue(request)
    }

    override fun onItemClick(newsItem: News) {
        val builder = CustomTabsIntent.Builder()
        val customBuilder = builder.build()
        customBuilder.launchUrl(this, Uri.parse(newsItem.url))
    }
}