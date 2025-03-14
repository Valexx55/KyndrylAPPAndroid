package edu.basic.kyndrylapp.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.basic.kyndrylapp.dto.IMC
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

    //dibujar el menú
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_imc, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun salir() {
        //mostrar un diálogo típico de estás seguro??=

        var alertDialog = AlertDialog.Builder(this)
            .setTitle("SALIR")
            .setMessage("¿Desea salir?")
            .setNegativeButton("NO"){ dialog: DialogInterface, opcion:Int ->
                dialog.cancel()
            }
            .setPositiveButton("SÍ"){ dialog:DialogInterface, opcion:Int ->
                this.finish()

            }.create()

        alertDialog.show()

    }

    //escuchar las acciones de menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            R.id.opcionLimpiar -> {
                binding.etpeso.setText("")
                binding.etAltura.setText("")
            }

            R.id.opcionSalir -> {
                salir()
            }
        }

        return super.onOptionsItemSelected(item)
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