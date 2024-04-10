package com.hk210.newsreader.models.news

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Source(

    @SerializedName("id")
    var id: String?,

    @SerializedName("name")
    var name: String?
) : Parcelable
