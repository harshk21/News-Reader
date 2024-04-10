package com.hk210.newsreader.storage

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.hk210.newsreader.models.news.HeadlinesModel

@Dao
interface NewsDao {

    @Upsert
    suspend fun upsertNews(headlinesModel: HeadlinesModel)

    @Query("SELECT * FROM news")
    suspend fun getAllNews(): HeadlinesModel
}
