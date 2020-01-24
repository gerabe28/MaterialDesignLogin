package com.example.materialdesign

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var gso: GoogleSignInOptions
    val RC_SIGN_IN: Int = 1

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("TAG", "LOGIIINN")

        auth = FirebaseAuth.getInstance()
        auth.currentUser

        // Boton registrar
        btnRegistrar.setOnClickListener {
            val intent = Intent(this, Registro::class.java)
            startActivity(intent)
        }

        // Boton Ingresar
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


        //Login con Gmail
        val signIn = findViewById<View>(R.id.signInBtn) as SignInButton
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)
        signIn.setOnClickListener {
            view: View? -> signInGoogle()
        }



    }
    private fun signInGoogle(){
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent,RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==RC_SIGN_IN){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }
    private fun handleResult(completedTask: Task<GoogleSignInAccount>){
        try{
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            if (account != null) {
     //           updateUI(account)
            }
        }catch (e: ApiException){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show()
        }
    }
    /*private fun updateUI(account: GoogleSignInAccount){
        val dispTxt = findViewById<View>(R.id.) as TextView
        dispTxt.text = account.displayName

    }*/
    override fun onBackPressed()
    {
       // super.onBackPressed()
        finish()
    }
}
