package com.example.tienda_videojuegos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.TextView

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        val dbHelper = DBHelper(this)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        //Variable de registro exitoso
        val tvRegistroExitoso = findViewById<TextView>(R.id.tvRegistroExitoso)

        btnRegister.setOnClickListener {
            val username = findViewById<EditText>(R.id.editTextUsername).text.toString()
            val password = findViewById<EditText>(R.id.editTextPassword).text.toString()


            val registroExitoso = dbHelper.registerUser(username, password)
            if (registroExitoso) {
                // Limpia los campos de texto
                findViewById<EditText>(R.id.editTextUsername).text.clear()
                findViewById<EditText>(R.id.editTextPassword).text.clear()

                // Muestra el mensaje de éxito
                tvRegistroExitoso.text = "¡Usuario registrado con éxito!"
            } else {
                tvRegistroExitoso.text = "¡Error en el registro!"
            }
            tvRegistroExitoso.text = "¡Completa todos los campos!"



            // Limpia los campos de texto
            findViewById<EditText>(R.id.editTextUsername).text.clear()
            findViewById<EditText>(R.id.editTextPassword).text.clear()


            // Puedes agregar aquí la lógica para mostrar un mensaje de éxito o redirigir a la pantalla de inicio de sesión
        }

        val btnGoToLogin = findViewById<Button>(R.id.btnGoToLogin)
        btnGoToLogin.setOnClickListener {
            val intent = Intent(this, InicioSesionActivity::class.java)
            startActivity(intent)
        }
    }
}
