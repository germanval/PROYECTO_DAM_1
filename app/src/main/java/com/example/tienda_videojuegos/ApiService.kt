package com.example.tienda_videojuegos

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // Cambia la ruta en la anotación @GET para que sea relativa a la base URL
    @GET("ISteamNews/GetNewsForApp/v0002/")
    fun obtenerNoticias(@Query("appid") appid: Int): Call<RespuestaNoticias>
}