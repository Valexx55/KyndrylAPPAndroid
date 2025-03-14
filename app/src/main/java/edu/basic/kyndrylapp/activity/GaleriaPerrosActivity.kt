package edu.basic.kyndrylapp.activity

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import edu.basic.kyndrylapp.Constantes
import edu.basic.kyndrylapp.R
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//aquí nos comunicamos por HTTp y mostaremos los datos

class GaleriaPerrosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_galeria_perros)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val razaPerro =  intent.getStringExtra("RAZA") ?: ""
        Toast.makeText(this, "GALERIA $razaPerro", Toast.LENGTH_LONG).show()
        val tvraza = findViewById<TextView>(R.id.razaPerro)
        tvraza.setText(razaPerro.uppercase())
        obtenerListadoRaza(razaPerro)
    }


    fun obtenerListadoRaza (raza:String):Unit
    {
        var retrofit = Retrofit.Builder() //iniciamos la cofiguración de retrofit
            .baseUrl("https://dog.ceo/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        var clientePerroService =  retrofit.create(PerroService::class.java)//creamos el stub/cliente

        //creamos un hilo, para que en un proceso independiente, se produzca la com HTTP
        //su ámbito depende del de la actividad. Si la activida finaliza, este hilo muere
        lifecycleScope.launch {
            var listadoPerros = clientePerroService.listadoDePerrosPorRaza(raza)
            Log.d(Constantes.ETIQUETA_LOG, "Respuesta obtenida")
            listadoPerros.message.forEach{ fotoperro ->
                Log.d(Constantes.ETIQUETA_LOG, "FOTO perro = $fotoperro")
            }
            //TODO mostrar la galería de perros FRAGMENTS
            //creo el adapter
            var adapterFragmentPerros = AdapterFragmentPerros(this@GaleriaPerrosActivity)
            adapterFragmentPerros.listadoPerros = listadoPerros
            //lo asocio al viewpager
            var viewPager = this@GaleriaPerrosActivity.findViewById<ViewPager2>(R.id.viewPager)
            viewPager.adapter = adapterFragmentPerros
        }
        Log.d(Constantes.ETIQUETA_LOG, "Petición realizada")//UI hilo principal



    }
}