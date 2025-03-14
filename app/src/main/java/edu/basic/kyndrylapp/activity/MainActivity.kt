package edu.basic.kyndrylapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import edu.basic.kyndrylapp.util.Constantes
import edu.basic.kyndrylapp.R

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView

    var menuvisible:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawerlayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        //TODO sustituir esto por un Menú NavigationView (Material Design)
        Log.d(Constantes.ETIQUETA_LOG, "en Oncreate de Main Activity")
        val intent = Intent(this, ImcActivity::class.java)
        //val intent = Intent(this, Adivina::class.java)
        //val intent = Intent(this, BusquedaActivity::class.java)
        //val intent = Intent(this, PerrosActivity::class.java)
        //val intent = Intent(this, ProductosActivity::class.java)
        this.drawerLayout = findViewById<DrawerLayout>(R.id.drawerlayout)
        this.navigationView = findViewById<NavigationView>(R.id.navview)
        //this.startActivity(intent)//intent EXPLÍCITO
        this.navigationView.setNavigationItemSelectedListener(this)


        supportActionBar?.setDisplayHomeAsUpEnabled(true)//dibujo el home (ic sup iz)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_menu_24) //le asigno la hamburguesa

    }

    //este es cuando toque el menú/drawer/la hamburguesa
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId)
        {
            //botón superior izquierdo
            android.R.id.home -> {
                //ha tocado el menú
                if (this.menuvisible)
                {
                    this.drawerLayout.closeDrawers()
                } else {
                    this.drawerLayout.openDrawer(GravityCompat.START)//que se abra por la izquierda
                }
                this.menuvisible = !this.menuvisible//mantengo el estado de abierto a cerrado y viceversa
            }
        }
        return super.onOptionsItemSelected(item)
    }

    //este es cuando toque una opción de Nav View
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        var objetoClassActivity: Class<*>? = null

        when(item.order)
        {
            1 -> { objetoClassActivity = AdivinaActivity::class.java }
            2 -> { objetoClassActivity = ImcActivity::class.java }
            3 -> { objetoClassActivity = BusquedaActivity::class.java }
            4 -> { objetoClassActivity = ProductosActivity::class.java }
            5 -> { objetoClassActivity = PerrosActivity::class.java }
            6 -> { objetoClassActivity = FotoActivity::class.java }

        }
        this.startActivity(Intent(this, objetoClassActivity))
        return true
    }


}