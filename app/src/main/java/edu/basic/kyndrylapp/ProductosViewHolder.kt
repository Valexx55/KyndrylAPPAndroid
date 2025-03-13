package edu.basic.kyndrylapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//esta clase es una estructura que contiene la fila
//es como un contenedor de la fila
class ProductosViewHolder(item: View): RecyclerView.ViewHolder(item) {

    val id: TextView = item.findViewById<TextView>(R.id.textViewIdProducto)
    val nombre: TextView = item.findViewById<TextView>(R.id.textViewNombreProducto)
    val precio: TextView = item.findViewById<TextView>(R.id.textViewPrecio)
    val imagen: ImageView = item.findViewById<ImageView>(R.id.imagenProducto)


    fun rellenarProductoViewHolder (producto: ListadoProductosItem)
    {
        id.text = producto.id.toString()
        nombre.text = producto.name
        precio.text = producto.price
        //TODO volcar foto
    }
}