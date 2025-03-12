package edu.basic.kyndrylapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.basic.kyndrylapp.activity.Adivina
import edu.basic.kyndrylapp.activity.BusquedaActivity
import edu.basic.kyndrylapp.activity.PerrosActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d(Constantes.ETIQUETA_LOG, "en Oncreate de Main Activity")
        //val intent = Intent(this, ImcActivity::class.java)
        //val intent = Intent(this, Adivina::class.java)
        //val intent = Intent(this, BusquedaActivity::class.java)
        val intent = Intent(this, PerrosActivity::class.java)
        //lanzo
        this.startActivity(intent)//intent EXPLÍCITO

    }
}