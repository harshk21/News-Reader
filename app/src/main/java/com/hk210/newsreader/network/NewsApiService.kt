package com.hk210.newsreader.network

import com.hk210.newsreader.models.news.HeadlinesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") countryCode: String,
        @Query("category") category: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): HeadlinesModel
}