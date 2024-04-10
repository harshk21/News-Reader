package com.hk210.newsreader.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hk210.newsreader.databinding.HeadlineLayoutBinding
import com.hk210.newsreader.models.news.Article
import com.hk210.newsreader.utils.loadImage

class HeadlinesAdapter(private val context: Context, private val items: List<Article?>, private val onItemClickListener: (Article) -> Unit) :
    RecyclerView.Adapter<HeadlinesAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: HeadlineLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article?) {
            binding.headlineTitle.text = article?.title
            binding.headlineAuthor.text = article?.author ?: article?.source?.name
            binding.headlineImage.loadImage(context, article?.urlToImage.toString())
            binding.headlineSource.text = article?.source?.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            HeadlineLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            items[position]?.let { article -> onItemClickListener.invoke(article) }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
