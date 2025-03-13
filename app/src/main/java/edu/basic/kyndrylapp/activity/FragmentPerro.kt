package edu.basic.kyndrylapp.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import edu.basic.kyndrylapp.R

class FragmentPerro: Fragment() {

    //en este metodo, preparamos lo que va a mostrar el fragment. parecedio al oncreate de la activity
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)

        var itemCarrusel: View? = inflater.inflate(R.layout.fragment_perro, container, false)
        var url_foto =  arguments?.getString("URL_FOTO") ?: ""
        var imagenPerro = itemCarrusel?.findViewById<ImageView>(R.id.fotoPerro)

        Picasso.get().load(url_foto).into(imagenPerro)

        return itemCarrusel
    }
}