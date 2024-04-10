package com.hk210.newsreader.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.hk210.newsreader.models.news.HeadlinesModel

@TypeConverters(Convertors::class)
@Database(entities = [HeadlinesModel::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
}
