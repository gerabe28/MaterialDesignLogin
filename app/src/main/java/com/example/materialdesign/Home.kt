package com.example.materialdesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.nav_header.*

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar("App")

        //recepcion de objeto USER
        intent.extras.getString("correo")

       // intent.getShortExtra("correo","user")

        val intent = getIntent();
        val emailReceived = intent.getStringExtra("correo")

        Log.d("tag","Email: "+emailReceived)

        navigation.setNavigationItemSelectedListener ({item ->
            when(item.itemId){
                R.id.op1 -> {
                    Log.i("OP1","Opcion 1")
                }
                R.id.op2 -> {
                    Log.i("OP2","Opcion 2")
                }
            }
            when(item.itemId){
                R.id.op3->{
                    Log.i("OP3","Cerrar Sesión")

                    val intent = Intent(this,Tryinh::class.java)
                    startActivity(intent)
            }

            }
            false
        })
    }


    fun toolbar(title: String){
        setSupportActionBar(toolbar)

        var ab = supportActionBar
        if (ab != null){
            ab.setHomeAsUpIndicator(R.drawable.ic_action_name)
            ab.setDisplayHomeAsUpEnabled(true)
            ab.title = title

        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

// https://www.youtube.com/watch?v=0-Xq2ti_HaE
