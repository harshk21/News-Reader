package com.hk210.newsreader.storage

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hk210.newsreader.models.news.Article

class Convertors {
    @TypeConverter
    fun fromString(value: String?): List<Article> {
        val listType = object :
            TypeToken<ArrayList<Article?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Article?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}