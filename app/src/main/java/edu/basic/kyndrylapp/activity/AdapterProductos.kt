package edu.basic.kyndrylapp.activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.basic.kyndrylapp.ListadoProductos
import edu.basic.kyndrylapp.ListadoProductosItem
import edu.basic.kyndrylapp.ProductosViewHolder
import edu.basic.kyndrylapp.R

class AdapterProductos (var listaProductos:ListadoProductos) : RecyclerView.Adapter<ProductosViewHolder>() {

    //creame una fila
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        //INFLAR XML->VISUAL
        var productoVH: ProductosViewHolder //es el conentedor la fila

        var layoutInflater = LayoutInflater.from(parent.context)//este objeto, infla vistas
        var filaProducto = layoutInflater.inflate(R.layout.fila_producto, parent, false)
        productoVH = ProductosViewHolder(filaProducto)


        return  productoVH
    }

    //relléname una fila
    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        var producto = this.listaProductos.get(position)
        holder.rellenarProductoViewHolder(producto)
    }

    ////dime cuantas filas hay que pintar
    override fun getItemCount(): Int {
        return this.listaProductos.size
    }


}