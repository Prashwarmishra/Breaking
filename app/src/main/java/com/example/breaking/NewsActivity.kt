package com.example.breaking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class NewsActivity : AppCompatActivity() {
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

    }
}