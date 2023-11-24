package com.example.tienda_videojuegos

import com.google.gson.annotations.SerializedName

data class AppNewsResponse(
    @SerializedName("appid")
    val appid: Int,
    @SerializedName("newsitems")
    val newsItems: List<Noticia>, // Utilizando la clase Noticia
    @SerializedName("count")
    val count: Int
)