package com.hk210.newsreader.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hk210.newsreader.databinding.NewsDetailFragmentBinding
import com.hk210.newsreader.utils.loadImage

class NewsDetailFragment : Fragment() {

    private var _binding: NewsDetailFragmentBinding? = null
    private val binding: NewsDetailFragmentBinding
        get() = _binding!!


    private val args by navArgs<NewsDetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NewsDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setArticleData()
    }

    private fun setArticleData() {
        binding.detailNewsImage.loadImage(requireContext(), args.article.urlToImage ?: "")
        binding.newsDetailTitle.text = args.article.title ?: ""
        binding.newsDetailAuthor.text = args.article.author ?: ""
        binding.newsDetailContent.text = args.article.content ?: ""
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}