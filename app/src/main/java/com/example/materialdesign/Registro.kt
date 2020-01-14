package com.example.materialdesign

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.tasks.Task
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_registro.*
import kotlinx.android.synthetic.main.activity_tryinh.view.*

class Registro : AppCompatActivity() {


    lateinit var textName:EditText
    lateinit var textApellidos: EditText
    lateinit var textEmail: EditText
    lateinit var textContraseña: EditText
    lateinit var textConf:EditText
    private lateinit var  database: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        textName = findViewById(R.id.textName1)
        textApellidos = findViewById(R.id.textApellidos1)
        textEmail = findViewById(R.id.textEmail1)
        textContraseña = findViewById(R.id.textContraseña1)
        textConf = findViewById(R.id.textConf1)

        BtnRegistrarUser.setOnClickListener {
            saveUser()
        }

    }


    private fun saveUser(){

        val name = textName.text.toString().trim()
        val apellido = textApellidos.text.toString().trim()
        val email = textEmail.text.toString().trim()
        val contraseña = textContraseña.text.toString().trim()
        val confcontra = textConf.text.toString().trim()


        if (name.isEmpty()){
            textName.error = "Porfavor ingrese su nombre"
            return
        }

        if (apellido.isEmpty()){
            textApellidos.error = "Porfavor ingrese sus apellidos"
            return
        }

        if (email.isEmpty()){
            textEmail.error = "Porfavor ingrese un correo"
            return
        }

        if (contraseña.isEmpty()){
            textContraseña.error = "Porfavor ingrese su contraseña"
            return
        }

        if (confcontra.isEmpty()){
            textConf.error = "Porfavor confirmar su contraseña"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("Users")
        val userId = ref.push().key
        val user = User(userId, name, apellido, email, contraseña, confcontra)


        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email,contraseña)
            .addOnCompleteListener(this){
                task ->

                if(task.isComplete){
                    val user: FirebaseUser? = auth.currentUser
                    verifyEmail(user)
                }
            }

        if (userId != null) {
            ref.child(userId).setValue(user).addOnCompleteListener(
             Toast.makeText(applicationContext,"Usuario registrado",Toast.LENGTH_LONG).show()
            )
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("¡Bienvenido!")
        builder.setMessage("Usted está registrado.")
        builder.setPositiveButton("Aceptar",{ dialogInterface: DialogInterface, i: Int ->
            val intent = Intent(this,Home::class.java)
            startActivity(intent)
        })
        builder.show()
    }
    //val name: String, apellido: String, email: String, contraseña: String, conf: String

    /* this is now created*/
    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this){
                    task ->
                if(task.isComplete){
                    Toast.makeText(this,"Email enviado",Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"Error al envío",Toast.LENGTH_LONG).show()
                }
            }
    }
    /*here it finished   */
}

private fun <TResult> Task<TResult>.addOnCompleteListener(show: Unit) {

}


