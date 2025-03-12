package edu.basic.kyndrylapp

import android.content.Context
import android.net.ConnectivityManager

object RedUtil {

    fun hayInternet (contexto:Context):Boolean{
        var hay = false
            //necesito el contexto para obtener el conectivity manager
            //el c.m. es el servicio del sistema que me permite comprobar el estado de la red
            var cm = contexto.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val redEnUso =  cm.activeNetwork
            hay = (redEnUso!=null)

        return hay
    }
}