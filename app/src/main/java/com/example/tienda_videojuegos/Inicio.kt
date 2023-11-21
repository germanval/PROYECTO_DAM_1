package com.example.tienda_videojuegos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)

        val btnGrabar: Button = findViewById(R.id.btnGrabar)

        btnGrabar.setOnClickListener {
            val inputfecha: EditText = findViewById(R.id.txtFechaLanzamientoDato)
            val inputprecio: EditText = findViewById(R.id.txtPrecioDato)
            val inputconsola: EditText = findViewById(R.id.txtConsolaDato)
            val inputtipo: EditText = findViewById(R.id.txtTipoJuegoDato)

            val fecha = inputfecha.text.toString()
            val precio = inputprecio.text.toString()
            val consola = inputconsola.text.toString()
            val tipo = inputtipo.text.toString()

            if (fecha.isEmpty() || precio.isEmpty() || consola.isEmpty() || tipo.isEmpty()) {

                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {

                val db = BDRegistro(this, null)
                db.crearRegistro(fecha, precio, consola, tipo)
                Toast.makeText(this, "Se registro el producto exitosamente", Toast.LENGTH_LONG)
                    .show()

                inputfecha.text.clear()
                inputprecio.text.clear()
                inputconsola.text.clear()
                inputtipo.text.clear()


            }
            val btnVolver = findViewById<Button>(R.id.btnVolver)

            btnVolver.setOnClickListener {
                val intent = Intent(this, PrincipalActivity::class.java)
                startActivity(intent)
            }
        }
    }
}