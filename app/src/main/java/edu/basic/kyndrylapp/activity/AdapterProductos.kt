package edu.basic.kyndrylapp.activity

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.basic.kyndrylapp.ListadoProductos
import edu.basic.kyndrylapp.ListadoProductosItem
import edu.basic.kyndrylapp.ProductosViewHolder

class AdapterProductos (var listaProductos:ListadoProductos) : RecyclerView.Adapter<ProductosViewHolder>() {

    //creame una fila
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductosViewHolder {
        TODO("Not yet implemented")
    }

    ////dime cuantas filas hay que pintar
    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    //relléname una fila
    override fun onBindViewHolder(holder: ProductosViewHolder, position: Int) {
        TODO("Not yet implemented")
    }


}