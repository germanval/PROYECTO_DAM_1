package com.example.tienda_videojuegos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class BDRegistro(context: Context, factory: SQLiteDatabase.CursorFactory?)
    : SQLiteOpenHelper(context, DATABASE_NAME,factory, DATABASE_VERSION) {

    companion object {
        private val DATABASE_NAME = "Db_Tienda"
        private val DATABASE_VERSION = 2
        private val TABLA_JUEGO2 = "JUEGOS"
        private val COLUMN_ID = "ID"
        private val COLUMN_FECHA = "FECHA"
        private val COLUMN_PRECIO = "PRECIO"
        private val COLUMN_CONSOLA = "CONSOLA"
        private val COLUMN_TIPO = "TIPO"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val queryCreateTable = (
                "CREATE TABLE " + TABLA_JUEGO2 + " ( " +
                        COLUMN_ID + " INT PRIMARY KEY, " +
                        COLUMN_FECHA + " TEXT, " +
                        COLUMN_PRECIO + " TEXT, " +
                        COLUMN_CONSOLA + " TEXT, " +
                        COLUMN_TIPO + " TEXT" +
                        " )"
                )
        db.execSQL(queryCreateTable)
    }

    fun crearRegistro(fecha: String, precio: String, consola: String, tipo: String) {

        val values = ContentValues();
        values.put(COLUMN_FECHA, fecha)
        values.put(COLUMN_PRECIO, precio)
        values.put(COLUMN_CONSOLA, consola)
        values.put(COLUMN_TIPO, tipo)

        val db = this.writableDatabase
        db.insert(TABLA_JUEGO2, null, values)
        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLA_JUEGO2")
        onCreate(db)

    }
}