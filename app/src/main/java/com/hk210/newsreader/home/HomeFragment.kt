package com.hk210.newsreader.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hk210.newsreader.R
import com.hk210.newsreader.alert.Alert
import com.hk210.newsreader.databinding.HomeFragmentBinding
import com.hk210.newsreader.home.adapter.HeadlinesAdapter
import com.hk210.newsreader.home.adapter.HeadlinesFilterAdapter
import com.hk210.newsreader.models.news.Article
import com.hk210.newsreader.network.NetworkResult
import com.hk210.newsreader.storage.NewsDao
import com.hk210.newsreader.utils.loader.LoaderUtils
import com.hk210.newsreader.utils.network.ConnectivityObserver
import com.hk210.newsreader.utils.network.NetworkConnectivityObserver
import com.hk210.newsreader.utils.network.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: HomeFragmentBinding? = null
    private val binding: HomeFragmentBinding
        get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var networkConnectivityObserver: NetworkConnectivityObserver

    @Inject
    lateinit var newsDao: NewsDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeNetworkChanges()
        setHeadlineFilter()
        observeHeadlines()
    }

    private fun observeNetworkChanges() {
        lifecycleScope.launch {
            setHeadlines(newsDao.getAllNews().articles)
            networkConnectivityObserver.observe().collect { status ->
                if(status == Status.Available) {
                    viewModel.getHeadLines("")
                }
            }
        }
    }

    private fun observeHeadlines() {
        viewModel.headLinesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Loading -> {
                    LoaderUtils.showDialog(requireContext(), isCancelable = false)
                }

                is NetworkResult.Error -> {
                    LoaderUtils.hideDialog()
                    Alert.showSnackBar(binding.root, response.message.toString())
                }

                is NetworkResult.Success -> {
                    LoaderUtils.hideDialog()
                    if (response.data?.articles?.isNotEmpty() == true) {
                        setHeadlines(response.data.articles)
                    }
                }
            }
        }
    }

    private fun setHeadlineFilter() {
        binding.headlinesFilterList.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = HeadlinesFilterAdapter(resources.getStringArray(R.array.filter_list).toList()) { filter, _ ->
                viewModel.getHeadLines(filter)
            }
        }
    }

    private fun setHeadlines(articles: List<Article?>?) {
        binding.headlinesList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = articles?.let {
                HeadlinesAdapter(requireContext(), it) { article ->
                    val action: NavDirections = HomeFragmentDirections.actionHomeFragmentToNewsDetailFragment(article)
                    findNavController().navigate(action)
                }
            }
        }
    }
}
