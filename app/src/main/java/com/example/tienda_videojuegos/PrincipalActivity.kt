package com.example.tienda_videojuegos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.tienda_videojuegos.R
import android.widget.Button
import android.content.Intent


class PrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        // Inicializar el ViewPager2 y la lista de imágenes después de establecer el contenido
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val imageList = listOf(

            R.drawable.imagen1,
            R.drawable.imagen2,
            R.drawable.imagen3
        )

        val imageSliderAdapter = ImageSliderAdapter(this, imageList)
        viewPager.adapter = imageSliderAdapter

        // Agrega el código para manejar el clic del botón "Insertar Producto"
        val btnInsertarProducto = findViewById<Button>(R.id.btnInsertarProducto)
        btnInsertarProducto.setOnClickListener {
            val intent = Intent(this, Inicio::class.java)
            startActivity(intent)
        }

        val btnDestacados = findViewById<Button>(R.id.btnDestacados)
        btnDestacados.setOnClickListener {
            // Al hacer clic en el botón, iniciar la actividad de juegos destacados
            val intent = Intent(this, JuegosDestacadosActivity::class.java)
            startActivity(intent)
        }
    }
}