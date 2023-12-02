package com.example.tienda_videojuegos

data class Producto(

    val consola: String,
    val precio: Double,
    val tipo: String,
    val fecha: String = "",
    var enCarrito: Boolean = false
)