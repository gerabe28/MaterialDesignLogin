package com.example.materialdesign

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
//<<<<<<< HEAD
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
//=======
import androidx.appcompat.app.AppCompatActivity
//>>>>>>> 7c269230b99dfd89530dc70ca3cb96efd1e04e7d
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        toolbar("App")


        //recepcion de objeto USER
///<<<<<<< HEAD
       intent.extras.getString("correo")
       // intent.getShortExtra("correo","user")
//=======
//>>>>>>> 7c269230b99dfd89530dc70ca3cb96efd1e04e7d
        val intent = getIntent();
        val emailReceived = intent.getStringExtra("correo")

        Log.d("tag","Email: "+emailReceived)


//<<<<<<< HEAD
        //Mostrar el correo loggeado
        val navigationView: NavigationView = findViewById(R.id.navigation1)
        val navHeader: View = navigationView.getHeaderView(0)
        val txtWebsite: TextView = navHeader.findViewById(R.id.txtEmailHeader)

        txtWebsite.setText(emailReceived);



        navigation1.setNavigationItemSelectedListener ({item ->
//=======
  //      navigation.setNavigationItemSelectedListener { item ->
//>>>>>>> 7c269230b99dfd89530dc70ca3cb96efd1e04e7d
            when(item.itemId){
                R.id.op1 -> {
                    Log.i("OP1","Opcion 1")
                    val intent = Intent(this,EncuestaActivity::class.java)
                    startActivity(intent)
                }
                R.id.op2 -> {
                    Log.i("OP2","Opcion 2")
                    val intent = Intent(this,Opcion2Activity::class.java)
                    startActivity(intent)
                }
            }
            when(item.itemId){
                R.id.op3->{
                    Log.i("OP3","Cerrar Sesión")

                    val user = FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this, LoginActivity::class.java)
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
//<<<<<<< HEAD

    override fun onBackPressed()
    {
        super.onBackPressed()
        // val returnIntent = Intent(this, MainActivity::class.java)
        //startActivity(returnIntent)
        finish()
        System.exit(0)
    }


}





// https://www.youtube.com/watch?v=0-Xq2ti_HaE

