package edu.basic.kyndrylapp.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.basic.kyndrylapp.Constantes
import edu.basic.kyndrylapp.ProductoService
import edu.basic.kyndrylapp.R
import edu.basic.kyndrylapp.databinding.ActivityProductosBinding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductosActivity : AppCompatActivity() {
    lateinit var binding: ActivityProductosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //TODO en 20 mins aprox
        //CONUSMIR EL API https://my-json-server.typicode.com/miseon920/json-api/products
        //CON RETROFIT
        //1 AYUDADO DEL PLUGIN, CREAMOS LA CLASE DE DATOS PARA MAPEAR EL JSON (lISTADOpERROS) x
        //2 CREO EL PRODUCTOSERVICE INTERFAZ CON GET (PERROSERVICE) x
        //3 configuro con el Builder Retrofit y creo el stub x
        //4 dentro de lifecycle.launch, consumo el servicio e imprimio el listado de productos por LOG x

        var retrofit = Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var prodcutoService = retrofit.create(ProductoService::class.java)
        lifecycleScope.launch {
            var listaProductos =  prodcutoService.obtenerListadoProductos()
            binding.barraProgreso.visibility = View.INVISIBLE//una vez completada la descarga, hacemos invisible la barra
            listaProductos.forEach {
                //it variable predefinida que representa al producto en curso
                Log.d(Constantes.ETIQUETA_LOG, "Producto = ${it.toString()}")
            }
            binding.listaReciclada.adapter = AdapterProductos(listaProductos)//
            //hablamos del estilo de la vista (filas, columnas o celdas)
            var layoutLista = LinearLayoutManager(this@ProductosActivity, RecyclerView.VERTICAL, false)

            //var layoutLista = GridLayoutManager(this@ProductosActivity, 2)
            //var layoutLista = LinearLayoutManager(this@ProductosActivity, RecyclerView.HORIZONTAL,true)
            binding.listaReciclada.layoutManager = layoutLista
        }

        //var layoutLista = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }

        //5 REPRESENTAR LA LISTA POR PANTALL
}