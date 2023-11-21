package com.example.tienda_videojuegos
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Db_Tienda", null, 2) {
    private val TABLE_NAME = "Usuarios"
    private val COL_ID = "id"
    private val COL_USERNAME = "username"
    private val COL_PASSWORD = "password"

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = "CREATE TABLE $TABLE_NAME " +
                "($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COL_USERNAME TEXT, " +
                "$COL_PASSWORD TEXT)"
        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUser(username: String, password: String) {
        val values = ContentValues()
        values.put(COL_USERNAME, username)
        values.put(COL_PASSWORD, password)

        val db = writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun checkUser(username: String, password: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COL_USERNAME = ? AND $COL_PASSWORD = ?"
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
        val query = "SELECT * FROM $TABLE_NAME WHERE $COL_USERNAME = ?"
        val cursor = db.rawQuery(query, arrayOf(username))
        val count = cursor.count
        cursor.close()
        return count > 0
    }
}