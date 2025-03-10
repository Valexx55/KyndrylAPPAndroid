package edu.basic.kyndrylapp.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.basic.kyndrylapp.Constantes
import edu.basic.kyndrylapp.R

/**
 * Esta app, piensa un número entre 1 y 100 y le da 5 oportiunidades al usuario para advinarlo
 * por cada intento, si el usuario se pasa o no llega (introduce un número mayor que el que debe adivinar o menor)
 * será informado por la actividad.
 *
 * si acierta antes de los 5 intentos, le damos la enhorabuena
 * y si no, si pierde todas las vidas, le damos el pésame y le informamos del número buscado
 */
class Advina : AppCompatActivity() {

    //var usuario, numero intentos restantes, numero aleatorio
    var numeroIntentos:Int = 5
    var numeroRandom:Int = 0
    lateinit var cajaNumeroUsuario:EditText //declaro la caja del numero a la que tendré que acceder más tarde, pero como no se ha ejecutado el oncreate y no está definida, le pongo el lateinit


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_advina)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun pruebaNumero(view: View) {
        Log.d(Constantes.ETIQUETA_LOG, "BOTÓN TOCADO")
        //acceder al Edit Text
        this.cajaNumeroUsuario = findViewById<EditText>(R.id.numeroUsuario)
        var numeroUsuario = this.cajaNumeroUsuario.text.toString().toInt()
        Log.d(Constantes.ETIQUETA_LOG, "Numero de usuario = $numeroUsuario")

        this.numeroIntentos= this.numeroIntentos-1;
        if (this.numeroIntentos>0)
        {
            Log.d(Constantes.ETIQUETA_LOG, "QUEDAn ${this.numeroIntentos} intentos")
        }else {
            Log.d(Constantes.ETIQUETA_LOG, "NO QUEDAN INTENTOS")
            var toast = Toast.makeText(this, "PERDISTE!!! El número secreto era ${this.numeroRandom}", Toast.LENGTH_LONG);
            toast.show()//muestra
        }

    }
}