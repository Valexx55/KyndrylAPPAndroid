package edu.basic.kyndrylapp.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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
 *
 * Ahora queremos, que cuando acabe la partida, darle la opción de jugar una nueva
 * REINICIAR!
 */
class Advina : AppCompatActivity() {

    //var usuario, numero intentos restantes, numero aleatorio
    var numeroIntentos:Int = 5
    var numeroRandom:Int = 0
    lateinit var cajaNumeroUsuario:EditText //declaro la caja del numero a la que tendré que acceder más tarde, pero como no se ha ejecutado el oncreate y no está definida, le pongo el lateinit
    lateinit var botonPrueba:Button //= findViewById(R.id.button)
    lateinit var botonReinicio:Button


    override fun onStart() {
        Log.d(Constantes.ETIQUETA_LOG, "en onStart")
        super.onStart()
    }

    override fun onPause() {
        Log.d(Constantes.ETIQUETA_LOG, "en onPause")
        super.onPause()
    }

    override fun onResume() {
        Log.d(Constantes.ETIQUETA_LOG, "en onResume")
        super.onResume()
    }

    override fun onStop() {
        Log.d(Constantes.ETIQUETA_LOG, "en onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(Constantes.ETIQUETA_LOG, "onDestroy()")
        super.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(Constantes.ETIQUETA_LOG, "onCreate()")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_advina) //HASTA QUE NO SE EJECUTA ESTE MÉTODO, LAS VISTAS NO EXISTEN
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        this.numeroRandom = (0..100).random()
        Log.d(Constantes.ETIQUETA_LOG, "El número secreto es ${this.numeroRandom} ")
        this.cajaNumeroUsuario = findViewById(R.id.numeroUsuario)
        this.cajaNumeroUsuario.setText(" ")



    }

    fun finPartida ():Unit //que la función no devuelve nada, el VOID de JAVA
    {
        this.botonPrueba = findViewById(R.id.button)
        this.botonPrueba.isClickable = false//desactivamos el botón
        this.botonReinicio = findViewById(R.id.button2) //hacemos visible el botón de reinicio
        this.botonReinicio.visibility = View.VISIBLE
    }

    fun pruebaNumero(view: View) {
        var acierto =false
        Log.d(Constantes.ETIQUETA_LOG, "BOTÓN TOCADO")
        //acceder al Edit Text
        this.cajaNumeroUsuario = findViewById<EditText>(R.id.numeroUsuario)
        var numeroUsuarioTxt = this.cajaNumeroUsuario.text.toString();
        val numeroUsuario = if (numeroUsuarioTxt=="")
        {
            0 //si la caja de texto está vacía, le damos el valor cero
        } else {
            numeroUsuarioTxt.toInt()
        }
        Log.d(Constantes.ETIQUETA_LOG, "Numero de usuario = $numeroUsuario")

        this.numeroIntentos= this.numeroIntentos-1;

        if (this.numeroRandom<numeroUsuario)
        {
            //hay que informar menor
            Log.d(Constantes.ETIQUETA_LOG, "El número buscado es menor")
            Toast.makeText(this, "El número buscado es menor", Toast.LENGTH_LONG).show()

        } else if (this.numeroRandom>numeroUsuario)
        {
            //hay que informar mayor
            Log.d(Constantes.ETIQUETA_LOG, "El número buscado es mayor")
            Toast.makeText(this, "El número buscado es mayor", Toast.LENGTH_LONG).show()

        } else {
            //acierto
            Log.d(Constantes.ETIQUETA_LOG, "ENHORABUENA, HAS ACERTADO")
            Toast.makeText(this, "ENHORABUENA, HAS ACERTADO", Toast.LENGTH_LONG).show()
            acierto = true
            finPartida()
        }

        if (!acierto && this.numeroIntentos==0)
        {
            Log.d(Constantes.ETIQUETA_LOG, "NO QUEDAN INTENTOS")
            var toast = Toast.makeText(this, "PERDISTE!!! El número secreto era ${this.numeroRandom}", Toast.LENGTH_LONG);
            toast.show()//muestra
            finPartida()
            //echarle del juego? // reiniciar
            //this.finish()//cierra la actividad actual, si hubiera navegado desde otra pantalla, ser vería la anterior
            //this.finishAffinity()//cierra la aplicación, cierra todo

        }

    }

    fun reiniciarPartida(view: View) {
        Log.d(Constantes.ETIQUETA_LOG, "El usuario quiere volver a empezar")
        this.recreate()
        //this.finish()
        //startActivity(intent)
    }
}