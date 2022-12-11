package com.example.breaking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView

class NewsActivity : AppCompatActivity(), Listener {
    companion object {
        const val QUERY = "QUERY"
        const val CATEGORIES = "CATEGORIES"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val query = intent.getStringExtra(QUERY)
        val categories = intent.getStringExtra(CATEGORIES)

        Log.d("Query", query.toString())
        Log.d("Categories", categories.toString())

        val recyclerView: RecyclerView = findViewById(R.id.news_recycler_view)
        recyclerView.adapter = NewsAdapter(this)
    }

    override fun onItemClick(news: News) {
        Log.d("clicked", "clicked")
    }
}