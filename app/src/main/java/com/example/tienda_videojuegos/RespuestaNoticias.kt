package com.example.tienda_videojuegos

import com.google.gson.annotations.SerializedName

data class RespuestaNoticias(
    @SerializedName("appnews")
    val appNews: AppNewsResponse
)
