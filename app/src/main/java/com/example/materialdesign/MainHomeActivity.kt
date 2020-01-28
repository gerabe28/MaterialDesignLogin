package com.example.materialdesign

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.example.arch.BaseActivity
import com.example.arch.navigator.Navigator
import kotlinx.android.synthetic.main.activity_main_home.*


//@Viewable(presenter = HomeActivityPresenter.class, layout = R.layout.activity_home)
class MainHomeActivity : BaseActivity<HomeActivityContract.View, HomeActivityContract.Presenter>(),
    HomeActivityContract.View, View.OnClickListener {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        showToolbarUp("tittle", true)

        action2.setOnClickListener {
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