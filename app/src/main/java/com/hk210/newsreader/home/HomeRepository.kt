package com.hk210.newsreader.home

import com.hk210.newsreader.models.news.HeadlinesModel
import com.hk210.newsreader.network.NetworkResult
import com.hk210.newsreader.network.NewsApiService
import com.hk210.newsreader.storage.NewsDao
import com.hk210.newsreader.utils.network.NetworkConnectivityObserver
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val newsApiService: NewsApiService,
    private val newsDao: NewsDao
) {

    fun getHeadlines(category: String) = flow<NetworkResult<HeadlinesModel>> {
        val response = newsApiService.getHeadlines("in", category)
        newsDao.upsertNews(response)
        emit(NetworkResult.Success(response))
    }.onStart {
        emit(NetworkResult.Loading())
    }.catch {
        emit(NetworkResult.Error(it.message))
    }
}