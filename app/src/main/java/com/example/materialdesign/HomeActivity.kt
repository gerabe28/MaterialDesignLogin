<<<<<<< HEAD
package com.example.materialdesign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.nav_header.*

class HomeActivity : AppCompatActivity() {

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

                    val user = FirebaseAuth.getInstance().signOut()
                    val intent = Intent(this,LoginActivity::class.java)
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
=======
package com.example.materialdesign

import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast

import com.example.arch.BaseActivity
import com.example.arch.navigator.Navigator
import kotlinx.android.synthetic.main.activity_home.*


//@Viewable(presenter = HomeActivityPresenter.class, layout = R.layout.activity_home)
class HomeActivity : BaseActivity<HomeActivityContract.View, HomeActivityContract.Presenter>(),
    HomeActivityContract.View, View.OnClickListener {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        showToolbarUp("tittle", true)

        action.setOnClickListener {
            presenter?.navigationHome()
        }

    }

    override fun openDetailFragment() {
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(this);*/
        /*if (getSupportFragmentManager().findFragmentById(R.id.pageLayout) == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.pageLayout, new HomeFirstFragment())
                    .commit();
        }*/
    }

    override fun replaceFragment(n: Navigator) {

    }

    fun showLoading() {

    }

    fun hideLoading() {

    }

    fun showError(errorMessage: String) {

    }

    override fun onBack() {
        Toast.makeText(this,"Hola",Toast.LENGTH_LONG).show()
    }


    override fun onClick(view: View) {
        presenter!!.navigationHome()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

        }
        return super.onKeyDown(keyCode, event)
    }

    override fun initPresenter(): HomeActivityContract.Presenter {
        return HomeActivityPresenter()
    }

    fun showToolbarUp(tittle: String, upButton: Boolean) {
        //super.showToolbarUp(tittle, upButton);
    }
}
>>>>>>> 2b0c12480bc77ddd64f3a668654e240ccf9a68de
