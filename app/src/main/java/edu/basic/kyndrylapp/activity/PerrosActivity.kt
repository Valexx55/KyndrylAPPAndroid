package edu.basic.kyndrylapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.basic.kyndrylapp.Constantes
import edu.basic.kyndrylapp.R
import edu.basic.kyndrylapp.RedUtil
import edu.basic.kyndrylapp.databinding.ActivityPerrosBinding

class PerrosActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var razaSeleecionada:String=""
    lateinit var binding:ActivityPerrosBinding
    val arrayRazas = arrayOf("affenpinscher", "african", "airedale", "akita", "appenzeller", "australian")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPerrosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapterSpinner = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayRazas)
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapterSpinner//adapter provee los datos al spinner/desplegable

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.spinner.onItemSelectedListener = this
    }

    fun buscarFotos(view: View) {

        //TODO consultar el API De perros con esa RAZA en otro ACTIVIDAD
        //0 comprobar la conexión a internet
        if (RedUtil.hayInternet(this))
        {
            //hay internet, vamos a otra activty, donde mostramos los resultados
            //Toast.makeText(this, "SÍ HAY CONEXIÓN A INTERNET", Toast.LENGTH_LONG).show()
            val intent = Intent (this, GaleriaPerrosActivity::class.java)
            intent.putExtra("RAZA", this.razaSeleecionada)
            startActivity(intent)
        } else {
            Toast.makeText(this, "NO HAY CONEXIÓN A INTERNET", Toast.LENGTH_LONG).show()
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //por el funcionamiento del spinner, sin que toques nada, la primera vez, ser invoca a este método
        this.razaSeleecionada = (view as TextView).text.toString()
        Log.d(Constantes.ETIQUETA_LOG, "raza seleccionada = ${this.razaSeleecionada}")

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //este método se invoca cuando una opción deja de estar disponible en el spinner
    }
}