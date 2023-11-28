package com.example.videojuegostienda

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pag_inicio.*

class PagInicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pag_inicio)

        // Agregar un clic al imageView8
        imageView8.setOnClickListener {
            mostrarModal()
        }
    }

    private fun mostrarModal() {
        // Inflar el diseño del modal_ask.xml
        val modalView = layoutInflater.inflate(R.layout.modal_ask, null)

        // Crear un AlertDialog
        val builder = AlertDialog.Builder(this)
            .setView(modalView)

        // Crear y mostrar el AlertDialog
        val dialog = builder.create()
        dialog.show()

        // Configurar clics de botones dentro del modal
        modalView.btnJuegos.setOnClickListener {
            // Redirigir a la actividad PaginaJuegos
            startActivity(Intent(this, PaginaJuegos::class.java))
            dialog.dismiss()  // Cerrar el modal después de redirigir
        }

        modalView.btnConsolas.setOnClickListener {
            // Redirigir a la actividad PaginaConsolas
            startActivity(Intent(this, PaginaConsolas::class.java))
            dialog.dismiss()
        }

        modalView.btnAccesorios.setOnClickListener {
            // Redirigir a la actividad PaginaAccesorios
            startActivity(Intent(this, PaginaAccesorios::class.java))
            dialog.dismiss()
        }
    }
}