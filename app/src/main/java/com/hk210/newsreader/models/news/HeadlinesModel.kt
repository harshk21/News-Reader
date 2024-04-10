package com.hk210.newsreader.models.news

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
@Parcelize
data class HeadlinesModel(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @SerializedName("articles")
    var articles: List<Article?>?,

    @SerializedName("status")
    var status: String?,

    @SerializedName("totalResults")
    var totalResults: Int?
) : Parcelable
