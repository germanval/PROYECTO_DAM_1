package com.example.tienda_videojuegos

import com.google.gson.annotations.SerializedName

data class Noticia(
    val gid: String,
    val title: String,
    val url: String,
    val isExternalUrl: Boolean,
    val author: String,
    val contents: String,
    val feedLabel: String,
    val date: Long,
    val feedName: String,
    val feedType: Int,
    val appid: Int
)