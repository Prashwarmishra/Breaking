package com.example.breaking

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(private val listener: Listener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val data: MutableList<News> = mutableListOf()

     class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val newsImage: ImageView = view.findViewById(R.id.news_image)
        val newsTitle: TextView = view.findViewById(R.id.news_title)
        val newsAuthor: TextView = view.findViewById(R.id.news_author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val newsView = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        val newsViewHolder = NewsViewHolder(newsView)
        newsView.setOnClickListener {
            listener.onItemClick(data[newsViewHolder.adapterPosition])
        }
        return newsViewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = data[position]
        holder.newsTitle.text = currentItem.title
        holder.newsAuthor.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.newsImage)
    }

    override fun getItemCount() = data.size

    fun updateData(newData: MutableList<News>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}

interface Listener {
    fun onItemClick(newsItem: News){}
}