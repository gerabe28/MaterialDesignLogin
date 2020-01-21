package com.example.materialdesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("TAG", "LOGIIINN")

        auth = FirebaseAuth.getInstance()
        auth.currentUser

        btnRegistrar.setOnClickListener {

            val intent = Intent(this, Registro::class.java)
            startActivity(intent)

        }

        btnIngresar.setOnClickListener {


            val user: String = txtEmailLogin.text.toString()
            val password: String = txtPassLogin.text.toString()

            if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
                auth.signInWithEmailAndPassword(user,password)
                    .addOnCompleteListener(this){
                        task ->
                        if(task.isSuccessful){


                            task.result
                            val intent = Intent(this, HomeActivity::class.java)

                            //viaje ida de objeto USER
                            intent.putExtra("correo",user)
                             startActivity(intent)


                        }else{
                            Toast.makeText(this, "Error en la autenticación",Toast.LENGTH_LONG).show()
                        }
                    }
            }



        }



    }
}
