package com.example.tienda_videojuegos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.database.Cursor


class Productos : AppCompatActivity() {

    private var adaptadorProductos: AdaptadorProductos? = null
    private lateinit var recyclerViewProductos: RecyclerView
    private lateinit var bdRegistro: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        // Inicializa tu base de datos
        bdRegistro = DBHelper(this)

        // Agrega un producto (esto es solo un ejemplo, deberías hacerlo según tu lógica de la aplicación)
        val producto = Producto(
            consola = "PlayStation 5",
            precio = 499.99,
            tipo = "Consola",
            fecha = "2023-12-02 00:35:37"
        )
        bdRegistro.crearRegistro(
            producto.fecha,
            producto.precio.toString(),
            producto.consola,
            producto.tipo
        )

        // Configura y muestra el RecyclerView con los productos
        setupRecyclerView()


    }

    private fun setupRecyclerView() {
        // Configura tu RecyclerView aquí
        // Puedes usar el adaptador y el diseño que ya has creado
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewProductos)



        val productosAdapter = AdaptadorProductos(obtenerProductosDesdeBD())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productosAdapter
    }

    private fun obtenerProductosDesdeBD(): List<Producto> {
        val listaProductos = mutableListOf<Producto>()

        // Obtener la base de datos en modo lectura
        val db = bdRegistro.readableDatabase

        // Definir las columnas que deseas recuperar
        val columnas = arrayOf(
            DBHelper.COLUMN_CONSOLA,
            DBHelper.COLUMN_PRECIO,
            DBHelper.COLUMN_TIPO
        )

        // Realizar la consulta a la base de datos
        val cursor: Cursor = db.query(
            DBHelper.TABLE_NAME_JUEGOS,
            columnas,
            null,
            null,
            null,
            null,
            null
        )

        // Iterar a través del cursor y agregar productos a la lista
        if (cursor != null && cursor.moveToFirst()) {
            // Accede a las columnas solo si el cursor contiene datos
            val consolaIndex = cursor.getColumnIndex(DBHelper.COLUMN_CONSOLA)
            val precioIndex = cursor.getColumnIndex(DBHelper.COLUMN_PRECIO)
            val tipoIndex = cursor.getColumnIndex(DBHelper.COLUMN_TIPO)

            // Iterar a través del cursor y agregar productos a la lista
            do {
                val consola = cursor.getString(consolaIndex)
                val precio = cursor.getString(precioIndex)
                val tipo = cursor.getString(tipoIndex)

                val producto =
                    Producto(consola = consola, precio = precio.toDouble(), tipo = tipo, fecha = "")
                listaProductos.add(producto)
            } while (cursor.moveToNext())
        }

        // Cerrar el cursor y la conexión a la base de datos
        cursor.close()
        db.close()

        // Notificar al adaptador que los datos han cambiado
        adaptadorProductos?.notifyDataSetChanged()

        return listaProductos
    }


}