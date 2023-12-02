package com.example.tienda_videojuegos
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import com.example.tienda_videojuegos.DBHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "Db_Tienda"
        const val DATABASE_VERSION = 7

        // Definiciones de la tabla Usuarios
        const val TABLE_NAME_USUARIOS = "Usuarios"
        const val COL_ID_USUARIOS = "id"
        const val COL_USERNAME = "username"
        const val COL_PASSWORD = "password"

        // Definiciones de la tabla Juegos
        const val TABLE_NAME_JUEGOS = "JUEGOS"
        const val COLUMN_ID_JUEGOS = "ID"
        const val COLUMN_FECHA = "FECHA"
        const val COLUMN_PRECIO = "PRECIO"
        const val COLUMN_CONSOLA = "CONSOLA"
        const val COLUMN_TIPO = "TIPO"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Crear la tabla Usuarios
        val createTableUsuariosSQL = "CREATE TABLE $TABLE_NAME_USUARIOS " +
                "($COL_ID_USUARIOS INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_USERNAME TEXT, " +
                "$COL_PASSWORD TEXT)"
        db.execSQL(createTableUsuariosSQL)

        // Crear la tabla Juegos
        val createTableJuegosSQL = (
                "CREATE TABLE $TABLE_NAME_JUEGOS ( " +
                        "$COLUMN_ID_JUEGOS INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "$COLUMN_FECHA TEXT, " +
                        "$COLUMN_PRECIO TEXT, " +
                        "$COLUMN_CONSOLA TEXT, " +
                        "$COLUMN_TIPO TEXT" +
                        " )"
                )
        db.execSQL(createTableJuegosSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Eliminar las tablas si existen
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_USUARIOS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME_JUEGOS")

        // Volver a crear las tablas
        onCreate(db)
    }

    fun addUser(username: String, password: String) {
        val values = ContentValues()
        values.put(COL_USERNAME, username)
        values.put(COL_PASSWORD, password)

        val db = writableDatabase
        db.insert(TABLE_NAME_USUARIOS, null, values)
        db.close()
    }

    fun checkUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME_USUARIOS WHERE $COL_USERNAME = ? AND $COL_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(username, password))
        val count = cursor.count
        cursor.close()
        return count > 0
    }

    fun registerUser(username: String, password: String): Boolean {
        val db = writableDatabase

        // Verifica si el usuario ya existe
        if (checkUserExists(username)) {
            return false // El usuario ya está registrado
        }

        // Agrega el nuevo usuario a la base de datos
        addUser(username, password)

        // Cierra la conexión a la base de datos
        db.close()

        return true // Registro exitoso
    }

    private fun checkUserExists(username: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME_USUARIOS WHERE $COL_USERNAME = ?"
        val cursor = db.rawQuery(query, arrayOf(username))
        val count = cursor.count
        cursor.close()
        return count > 0
    }

    fun crearRegistro(fecha: String, precio: String, consola: String, tipo: String) {
        val values = ContentValues()
        values.put(COLUMN_FECHA, fecha)
        values.put(COLUMN_PRECIO, precio)
        values.put(COLUMN_CONSOLA, consola)
        values.put(COLUMN_TIPO, tipo)

        val db = this.writableDatabase
        db.insert(TABLE_NAME_JUEGOS, null, values)
        db.close()
    }
    // Función para obtener la fecha actual en formato de cadena
    private fun obtenerFechaActual(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = Date()
        return dateFormat.format(date)
    }


}