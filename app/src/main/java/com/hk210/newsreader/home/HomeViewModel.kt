package com.hk210.newsreader.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hk210.newsreader.models.news.HeadlinesModel
import com.hk210.newsreader.network.NetworkResult
import com.hk210.newsreader.storage.NewsDao
import com.hk210.newsreader.utils.network.NetworkConnectivityObserver
import com.hk210.newsreader.utils.network.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    val headLinesResponse = MutableLiveData<NetworkResult<HeadlinesModel>>()

    fun getHeadLines(category: String) {
        viewModelScope.launch {
            homeRepository.getHeadlines(category).distinctUntilChanged().flowOn(Dispatchers.IO)
                .collect {
                    headLinesResponse.value = it
                }
        }
    }
}
