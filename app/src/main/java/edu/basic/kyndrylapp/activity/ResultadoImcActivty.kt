package edu.basic.kyndrylapp.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.basic.kyndrylapp.R

class ResultadoImcActivty : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado_imc_activty)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //intent es el objecto que ha lanzado esta actividad, el intent que hemos creado antes
        val resultado =  intent.getFloatExtra("IMC_RESULTADO", 20f)
        Toast.makeText(this, "SU IMC ES $resultado", Toast.LENGTH_LONG).show()
        //TODO pintar la foto y en el mensaje adecuados

    }
}