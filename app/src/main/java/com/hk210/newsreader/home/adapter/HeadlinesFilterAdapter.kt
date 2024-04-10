package com.hk210.newsreader.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.hk210.newsreader.R
import com.hk210.newsreader.databinding.HeadlineFilterLayoutBinding

class HeadlinesFilterAdapter(
    private val items: List<String?>,
    private val onItemClickListener: (String, Int) -> Unit
) : RecyclerView.Adapter<HeadlinesFilterAdapter.ViewHolder>() {

    private lateinit var binding: HeadlineFilterLayoutBinding

    inner class ViewHolder(private val binding: HeadlineFilterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(filter: String?, position: Int) {
            binding.headlineFilter.text = filter
            binding.root.setOnClickListener {
                onItemClickListener.invoke(filter?.lowercase() ?: "", position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            HeadlineFilterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}
