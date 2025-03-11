package edu.basic.kyndrylapp.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.basic.kyndrylapp.IMC
import edu.basic.kyndrylapp.R
import edu.basic.kyndrylapp.databinding.ActivityImcBinding

class ImcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImcBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityImcBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        enableEdgeToEdge()
        //setContentView(R.layout.activity_imc) //porque ahora usamos el BINDING vinculación de vistas moderna JetPack
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calcularImc(view: View) {
        //1 obtener peso
        val peso = this.binding.etpeso.text.toString().toFloat()
        //2 obtener altura
        val altura = this.binding.etAltura.text.toString().toFloat()
        //3 calcular
        val imc = IMC(peso, altura)
        val resultado = imc.calcularIMC()
        //4 resultado
        //this.binding.resultadoImc.setText(resultado.toString())
        //Toast.makeText(this, "SU IMC ES $resultado", Toast.LENGTH_LONG).show()
        val intent = Intent (this, ResultadoImcActivty::class.java)
        //guardamos info en el intent
        intent.putExtra("IMC_RESULTADO" , resultado)

        startActivity(intent)

    }
}