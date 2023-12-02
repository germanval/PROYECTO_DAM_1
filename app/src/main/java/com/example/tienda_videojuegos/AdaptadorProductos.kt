package com.example.tienda_videojuegos
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorProductos(private val listaProductos: List<Producto>) :
    RecyclerView.Adapter<AdaptadorProductos.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val consolaTextView: TextView = itemView.findViewById(R.id.textViewNombre)
        val precioTextView: TextView = itemView.findViewById(R.id.textViewPrecio)
        val tipoTextView: TextView = itemView.findViewById(R.id.textViewTipo)
        val imagenImageView: ImageView = itemView.findViewById(R.id.imageViewProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_producto, parent, false)
        return ProductoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaProductos[position]
        holder.consolaTextView.text = producto.consola
        holder.precioTextView.text = "Precio S/. : ${producto.precio}"
        holder.tipoTextView.text = "Tipo : ${producto.tipo}"
        holder.imagenImageView.setImageResource(R.mipmap.foreground_destacados)

    }
    override fun getItemCount(): Int = listaProductos.size

}
