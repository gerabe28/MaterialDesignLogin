package com.example.materialdesign

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var gso: GoogleSignInOptions
    val RC_SIGN_IN: Int = 1
    var callbackManager: CallbackManager?=null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.d("TAG", "LOGIIINN")

        auth = FirebaseAuth.getInstance()
        auth.currentUser
        callbackManager = CallbackManager.Factory.create()

        //Inicio facebook
        login_fb.setReadPermissions("email")
        login_fb.setOnClickListener {
            signInFb()
        }
        //Fin facebook

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

        printKeyHash()
//------------------------------------------------------------------------------------------------------------
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
        callbackManager!!.onActivityResult(requestCode,resultCode,data)
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
//------------------------------------------------------------------------------------------------------------
    //Login con Facebook

    private fun printKeyHash() {
     try{
         val info = packageManager.getPackageInfo("com.example.materialdesign",PackageManager.GET_SIGNATURES)
         for(signature in info.signatures){
             val md = MessageDigest.getInstance("SHA")
             md.update(signature.toByteArray())
             Log.e("KEYHASH", Base64.encodeToString(md.digest(),Base64.DEFAULT))
         }
     }catch(e:PackageManager.NameNotFoundException){

     }catch (e: NoSuchAlgorithmException){

     }
      }
    private fun signInFb(){
        login_fb.registerCallback(callbackManager,object :FacebookCallback<LoginResult>{
            override fun onSuccess(result: LoginResult?) {
                handleFacebookAccessToken(result!!.accessToken)
             }

            override fun onCancel() {

             }

            override fun onError(error: FacebookException?) {

             }

        })
    }

    private fun handleFacebookAccessToken(accessToken: AccessToken?) {
        //Get credential
        val credential = FacebookAuthProvider.getCredential(accessToken!!.token)
        auth!!.signInWithCredential(credential)
            .addOnFailureListener() { e->
                Toast.makeText(this,e.message,Toast.LENGTH_LONG).show()
            }
            .addOnSuccessListener { result ->
                //get email

                val email = result.user?.email
                Toast.makeText(this,"You logged with email: "+email, Toast.LENGTH_LONG).show()

            }
    }



    //------------------------------------------------------------------------------------------------------------
    override fun onBackPressed()
    {
       // super.onBackPressed()
        finish()
    }
}
