package com.example.tienda_videojuegos
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import android.database.Cursor
import android.content.Intent

class Productos : AppCompatActivity() {

    private lateinit var adaptadorProductos: AdaptadorProductos
    private lateinit var recyclerViewProductos: RecyclerView
    private lateinit var bdRegistro: DBHelper
    private var listaProductos: MutableList<Producto> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        // Inicializa tu base de datos
        bdRegistro = DBHelper(this)

        // Configura y muestra el RecyclerView con los productos
        setupRecyclerView()

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

        // Obtiene los productos desde la base de datos
        obtenerProductosDesdeBD()
    }

    private fun setupRecyclerView() {
        // Configura tu RecyclerView aquí
        // Puedes usar el adaptador y el diseño que ya has creado
        recyclerViewProductos = findViewById(R.id.recyclerViewProductos)
        adaptadorProductos = AdaptadorProductos(listaProductos)
        recyclerViewProductos.layoutManager = LinearLayoutManager(this)
        recyclerViewProductos.adapter = adaptadorProductos


        adaptadorProductos?.onItemClickListener = object : AdaptadorProductos.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val producto = adaptadorProductos?.getProductAtPosition(position)
                producto?.enCarrito = !producto?.enCarrito!!
                adaptadorProductos?.notifyDataSetChanged()
            }
        }
    }

    private fun obtenerProductosDesdeBD() {
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
        adaptadorProductos.notifyDataSetChanged()
    }


}