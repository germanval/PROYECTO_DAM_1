package com.example.tienda_videojuegos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.content.Intent
import android.widget.TextView
import android.view.View

class InicioSesionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_sesion)

        val dbHelper = DBHelper(this)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener {
            val username = findViewById<EditText>(R.id.editTextLoginUsername).text.toString()
            val password = findViewById<EditText>(R.id.editTextLoginPassword).text.toString()
            val tvMessage = findViewById<TextView>(R.id.tvMessage)
            if (dbHelper.checkUser(username, password)) {
                // Usuario válido, iniciar sesión
                tvMessage.text = "¡Bienvenido, $username!"
                tvMessage.setTextColor(resources.getColor(R.color.colorGreen))
                tvMessage.visibility = View.VISIBLE
                // Puedes redirigir a la siguiente actividad o realizar alguna acción deseada
                val intent = Intent(this, PrincipalActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Usuario inválido, muestra un mensaje de error o realiza alguna acción
                tvMessage.text = "Usuario o contraseña incorrectos"
                tvMessage.setTextColor(resources.getColor(R.color.colorRed))
                tvMessage.visibility = View.VISIBLE
            }
        }
        val btnGoToRegistro = findViewById<Button>(R.id.btnGoToRegistro)
        btnGoToRegistro.setOnClickListener {
            // Cuando se hace clic en el botón "Volver a Registro", abre la actividad de RegistroActivity
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }
}