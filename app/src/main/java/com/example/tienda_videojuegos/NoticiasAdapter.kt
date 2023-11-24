package com.example.tienda_videojuegos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.widget.ImageView
class NoticiasAdapter(private var noticias: List<Noticia>) : RecyclerView.Adapter<NoticiasAdapter.NoticiaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticiaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_juego_destacado, parent, false)
        return NoticiaViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoticiaViewHolder, position: Int) {
        val noticia = noticias[position]
        holder.bind(noticia)
    }

    override fun getItemCount(): Int = noticias.size

    inner class NoticiaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Almacenar las referencias de las vistas en el ViewHolder
        private val imageViewJuego: ImageView = itemView.findViewById(R.id.imageViewJuego)
        private val tituloTextView: TextView = itemView.findViewById(R.id.textViewTitulo)
        private val autorTextView: TextView = itemView.findViewById(R.id.textViewAutor)

        fun bind(noticia: Noticia) {
            tituloTextView.text = noticia.title
            autorTextView.text = noticia.author
        }
    }

    fun updateData(newData: List<Noticia>) {
        noticias = newData.toMutableList()
        notifyDataSetChanged()
        Log.d("Noticias", "Cantidad de noticias después de la actualización: ${noticias.size}")
    }


    fun clear() {
        noticias = emptyList()
        notifyDataSetChanged()
    }
}