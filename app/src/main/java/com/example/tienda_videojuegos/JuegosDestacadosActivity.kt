package com.example.tienda_videojuegos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log





class JuegosDestacadosActivity : AppCompatActivity() {

    private lateinit var noticiasAdapter: NoticiasAdapter
    private val apiService: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.steampowered.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }

    private lateinit var recyclerViewJuegosDestacados: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juegos_destacados)

        recyclerViewJuegosDestacados = findViewById(R.id.recyclerViewJuegosDestacados)
        recyclerViewJuegosDestacados.layoutManager = LinearLayoutManager(this)

        // Inicializar noticiasAdapter con una lista vacía
        noticiasAdapter = NoticiasAdapter(emptyList())
        recyclerViewJuegosDestacados.adapter = noticiasAdapter

        obtenerNoticiasDesdeApi()
    }

    private fun obtenerNoticiasDesdeApi() {
        Log.d("JuegosDestacadosActivity", "obtenerNoticiasDesdeApi() llamado")
        val call: Call<RespuestaNoticias> = apiService.obtenerNoticias(appid = 440)
        call.enqueue(object : Callback<RespuestaNoticias> {
            override fun onResponse(call: Call<RespuestaNoticias>, response: Response<RespuestaNoticias>) {
                if (response.isSuccessful) {
                    val noticias = response.body()?.appNews?.newsItems ?: emptyList()
                    Log.d("Noticias", "Lista de noticias: $noticias")
                    runOnUiThread {
                        Log.d("Noticias", "Cantidad de noticias antes de la actualización: ${noticiasAdapter.itemCount}")
                        noticiasAdapter.clear()
                        noticiasAdapter.updateData(noticias)
                        Log.d("Noticias", "Cantidad de noticias después de la actualización: ${noticiasAdapter.itemCount}")
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Error body es nulo"
                    Log.e("Noticias", "Cuerpo de la respuesta: $errorBody")
                }
            }

            override fun onFailure(call: Call<RespuestaNoticias>, t: Throwable) {
                Log.e("Noticias", "Error en la solicitud a la API: ${t.message}")


            }
        })
    }
}