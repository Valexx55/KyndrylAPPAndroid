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
class Adivina : AppCompatActivity() {

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
        setContentView(R.layout.activity_advina) //HASTA QUE NO SE EJECUTA ESTE MÉTODO, LAS VISTAS NO EXISTEN

        //TODO depuramos esto y vemos el Elvis operator
        //vamos a programar lo mismo, pero al estilo Kotlin
        //?. precediendo con el ?, accede a la propiedad o la función sólo si es != null
        //?: Elvis operator, toma el valor en caso de que sea nulo (else)
        this.numeroIntentos = savedInstanceState?.getInt("NUM_INTENTOS") ?: 5
        this.numeroRandom = savedInstanceState?.getInt("NUM_SECRETO") ?: (0..100).random()
        //ESTILO JAVA, COMPRBACIÓN DE NULOS
        /*if (savedInstanceState!=null)
        {
            //viene de girar el dispositivo
            this.numeroRandom = savedInstanceState.getInt("NUM_SECRETO")
            this.numeroIntentos = savedInstanceState.getInt("NUM_INTENTOS")
        } else {
            //entra por primera vez
            this.numeroRandom = (0..100).random()
        }*/

        Log.d(Constantes.ETIQUETA_LOG, "El número secreto es ${this.numeroRandom} ")
        this.cajaNumeroUsuario = findViewById(R.id.numeroUsuario)
        this.cajaNumeroUsuario.setText("") //pero para asignar un nuevo valor a la cajam con el operador de Java set
        //this.cajaNumeroUsuario.text = "" //por debajo hace el get cuando accedo a lapropiedad




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
            val mensaje_menor = resources.getString(R.string.mensaje_menor)
            Toast.makeText(this, mensaje_menor, Toast.LENGTH_LONG).show()

        } else if (this.numeroRandom>numeroUsuario)
        {
            //hay que informar mayor
            Log.d(Constantes.ETIQUETA_LOG, "El número buscado es mayor")
            val mensaje_mayor = resources.getString(R.string.mensaje_mayor)
            Toast.makeText(this, mensaje_mayor, Toast.LENGTH_LONG).show()

        } else {
            //acierto
            Log.d(Constantes.ETIQUETA_LOG, "ENHORABUENA, HAS ACERTADO")
            val mensaje_victoria = resources.getString(R.string.mensaje_victoria)
            Toast.makeText(this, mensaje_victoria, Toast.LENGTH_LONG).show()
            acierto = true
            finPartida()
        }

        if (!acierto && this.numeroIntentos==0)
        {
            Log.d(Constantes.ETIQUETA_LOG, "NO QUEDAN INTENTOS")
            val mensaje_derrota = resources.getString(R.string.mensaje_derrota, this.numeroRandom)
            var toast = Toast.makeText(this, mensaje_derrota, Toast.LENGTH_LONG);
            toast.show()//muestra
            finPartida()
            //echarle del juego? // reiniciar
            //this.finish()//cierra la actividad actual, si hubiera navegado desde otra pantalla, ser vería la anterior
            //this.finishAffinity()//cierra la aplicación, cierra todo

        }

    }

    fun reiniciarPartida(view: View) {
        Log.d(Constantes.ETIQUETA_LOG, "El usuario quiere volver a empezar")
        //this.recreate() //OJO que se llama onSaveInstanceState
        this.finish()
        startActivity(intent)
    }

    //ESTE MÉTODO, ES INVOCADO ANTES QUE LA ACTIVDAD SE DESTRUYA EN UN
    //CAMBIO DE ORIENTACIÓN
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("NUM_SECRETO", this.numeroRandom)
        outState.putInt("NUM_INTENTOS", this.numeroIntentos)
    }
}