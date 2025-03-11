package edu.basic.kyndrylapp.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.basic.kyndrylapp.R
import edu.basic.kyndrylapp.databinding.ActivityImcBinding
import edu.basic.kyndrylapp.databinding.ActivityResultadoImcActivtyBinding

class ResultadoImcActivty : AppCompatActivity() {

    lateinit var binding: ActivityResultadoImcActivtyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityResultadoImcActivtyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //setContentView(R.layout.activity_resultado_imc_activty)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //intent es el objecto que ha lanzado esta actividad, el intent que hemos creado antes
        val resultado =  intent.getFloatExtra("IMC_RESULTADO", 20f)
        Toast.makeText(this, "SU IMC ES $resultado", Toast.LENGTH_LONG).show()
        //TODO pintar la foto y en el mensaje adecuados

        when {
            resultado < 16 -> {
                    mostrarResultado(R.drawable.desnutrido, "DESNUTRIDO ($resultado)")
            }
            resultado >= 16 && resultado <18 -> {
                mostrarResultado(R.drawable.delgado, "DELGADO ($resultado)")
            }
            resultado >= 18 && resultado <25 -> {
                mostrarResultado(R.drawable.ideal, "IDEAL ($resultado)")
            }
            resultado >= 25 && resultado <31 -> {
                mostrarResultado(R.drawable.sobrepeso, "SOBREPESO ($resultado)")
            }
            resultado > 31 -> {
                mostrarResultado(R.drawable.obeso, "OBESO ($resultado)")
            }
        }

    }

    fun mostrarResultado (imagen:Int, texto:String):Unit
    {
        binding.imageView.setImageResource(imagen)
        binding.textView2.setText(texto)
    }
}