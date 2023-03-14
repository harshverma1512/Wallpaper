package com.manager.downloadmanager.dtos

import com.google.gson.annotations.SerializedName

data class Wallpaper(
    @SerializedName("hits")
    val hits: List<Hit>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int
) {
    data class Hit(
        @SerializedName("largeImageURL")
        val largeImageURL: String,
        @SerializedName("previewURL")
        val previewURL: String,
        @SerializedName("userImageURL")
        val userImageURL: String,
    )
}