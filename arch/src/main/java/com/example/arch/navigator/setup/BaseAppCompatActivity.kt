package com.example.arch.navigator.setup

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.arch.navigator.NavigationManager

open class BaseAppCompatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        updateCurrentActivity()
    }

    private fun updateCurrentActivity() {
        NavigationManager.instance.currentActivity = this
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
