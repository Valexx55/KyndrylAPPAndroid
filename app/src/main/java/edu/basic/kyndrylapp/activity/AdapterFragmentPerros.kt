package edu.basic.kyndrylapp.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import edu.basic.kyndrylapp.ListadoPerros

class AdapterFragmentPerros (var fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity){

    var listadoPerros : ListadoPerros? = null

    override fun getItemCount(): Int {
        //? si no es nulo, accede a la propeidad la función
        //!! accede sin miedo, te juro que no es nulo
        return this.listadoPerros?.message!!.size
    }

    override fun createFragment(position: Int): Fragment {
        var fragmentPerro: FragmentPerro? =null

        fragmentPerro = FragmentPerro()
        var urlFoto = this.listadoPerros?.message?.get(position)

        var bundle = Bundle()
        bundle.putString("URL_FOTO", urlFoto)
        var posicion = position+1;
        var stringLeyenda = "$posicion de ${this.itemCount}"
        bundle.putString("TEXTO_LEYENDA", stringLeyenda)


        fragmentPerro.arguments = bundle


        return fragmentPerro
    }
}