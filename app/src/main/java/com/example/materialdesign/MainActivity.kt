package com.example.materialdesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val SPLASH_TIME_OUT = 2000L
        Handler().postDelayed({

            val user = FirebaseAuth.getInstance().currentUser

            if (user != null){
                user?.let {
                    val nombre = user.displayName
                    val email = user.email
                    val intent = Intent(this@MainActivity, HomeActivity::class.java)
                    intent.putExtra("correo",email)
                    intent.putExtra("nombre",nombre)
                    startActivity(intent)
                    finish()
                }

            Log.d("tag","SESION ACTIVA")

            }else{

                val homeIntent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(homeIntent)
                finish()
           }

        }, SPLASH_TIME_OUT.toLong())
    }
}
