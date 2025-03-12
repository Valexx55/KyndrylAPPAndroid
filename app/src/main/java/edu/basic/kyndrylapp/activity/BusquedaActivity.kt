package edu.basic.kyndrylapp.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.basic.kyndrylapp.R
import edu.basic.kyndrylapp.databinding.ActivityBusquedaBinding
import edu.basic.kyndrylapp.databinding.ActivityImcBinding

class BusquedaActivity : AppCompatActivity() {

    lateinit var binding: ActivityBusquedaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBusquedaBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        enableEdgeToEdge()
        setContentView(R.layout.activity_busqueda)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun buscar(view: View) {
        //obtener el término de búsqueda
        val busqueda:String = binding.cajaBusqueda.text.toString()
        val url:String = "https://www.google.com/search?q=$busqueda"
        //val url:String = "https://www.realmadrid.com"
        //lanzar la búsqueda en el navegador de goActivityBusquedaBindingogle
        val intentImlicito = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intentImlicito)
    }
}