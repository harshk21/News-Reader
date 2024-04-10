package com.hk210.newsreader.models.news

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Article(

    @SerializedName("author")
    var author: String?,

    @SerializedName("content")
    var content: String?,

    @SerializedName("description")
    var description: String?,

    @SerializedName("publishedAt")
    var publishedAt: String?,

    @SerializedName("source")
    var source: Source?,

    @SerializedName("title")
    var title: String?,

    @SerializedName("url")
    var url: String?,

    @SerializedName("urlToImage")
    var urlToImage: String?
) : Parcelable
