package com.example.arch.navigator

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class Navigator private constructor() {

    fun navigateToFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment, @IdRes containerId: Int
    ) {
        val ft = fragmentManager.beginTransaction()
        ft.add(containerId, fragment, fragment.javaClass.simpleName)
        ft.addToBackStack(fragment.javaClass.simpleName)
        ft.commitAllowingStateLoss()
    }

    fun findFragmentByTag(fragmentManager: FragmentManager, tag: String): Fragment? {

        return fragmentManager.findFragmentByTag(tag)
    }

    fun navigateToFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment, @IdRes containerId: Int,
        bundle: Bundle
    ) {
        val ft = fragmentManager.beginTransaction()
        fragment.arguments = bundle
        ft.add(containerId, fragment, fragment.javaClass.simpleName)
        ft.addToBackStack(fragment.javaClass.simpleName)
        ft.commitAllowingStateLoss()
    }

    fun navigateToFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment, @IdRes containerId: Int,
        bundle: Bundle,
        enterAnim: Int,
        exitAnim: Int
    ) {
        val ft = fragmentManager.beginTransaction()
        fragment.arguments = bundle
        ft.setCustomAnimations(enterAnim, 0, 0, exitAnim)
        ft.add(containerId, fragment, fragment.javaClass.simpleName)
        ft.commitAllowingStateLoss()
    }

    fun navigateReplaceFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment, @IdRes containerId: Int
    ) {
        val ft = fragmentManager.beginTransaction()
        ft.replace(containerId, fragment, fragment.javaClass.simpleName)
        ft.commitAllowingStateLoss()
    }

    fun navigateReplaceFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment, @IdRes containerId: Int,
        bundle: Bundle
    ) {
        val ft = fragmentManager.beginTransaction()
        fragment.arguments = bundle
        ft.replace(containerId, fragment, fragment.javaClass.simpleName)
        ft.commitAllowingStateLoss()
    }

    fun navigateReplaceFragmentBackStack(
        fragmentManager: FragmentManager,
        fragment: Fragment, @IdRes containerId: Int
    ) {
        val ft = fragmentManager.beginTransaction()
        ft.replace(containerId, fragment, fragment.javaClass.simpleName)
        ft.addToBackStack(fragment.javaClass.simpleName)
        ft.commit()
    }

    fun navigateToActivity(
        activity: Activity?,
        destine: Class<*>,
        destroy: Boolean,
        bundle: Bundle?
    ) {
        if (activity != null) {
            val intent = Intent(activity.applicationContext, destine)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            activity.startActivity(intent)
            if (destroy) activity.finish()
        }
    }

    fun navigateToActivity(
        activity: Activity?,
        destine: Class<*>,
        destroy: Boolean,
        bundle: Bundle?,
        requestCode: Int
    ) {
        if (activity != null) {
            val intent = Intent(activity.applicationContext, destine)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            activity.startActivityForResult(intent, requestCode)
            if (destroy) activity.finish()
        }
    }

    fun navigateToFrangmentOnResult(
        fragment: Fragment?,
        destine: Class<*>,
        bundle: Bundle?,
        requestCode: Int
    ) {
        if (fragment != null) {
            val intent = Intent(fragment.context, destine)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            fragment.startActivityForResult(intent, requestCode)
        }
    }

    fun navigateToEnd(activity: Activity?, intent: Intent, resultCode: Int) {
        if (activity != null) {
            activity.setResult(resultCode, intent)
            activity.finish()
        }
    }

    fun backFragment(fragmentManager: FragmentManager) {
        fragmentManager.popBackStack()
    }


    fun navigateReplaceFragmentBackStack(
        fragmentManager: FragmentManager,
        fragment: Fragment, @IdRes containerId: Int,
        bundle: Bundle
    ) {
        val ft = fragmentManager.beginTransaction()
        fragment.arguments = bundle
        ft.replace(containerId, fragment, fragment.javaClass.simpleName)
        fragmentManager.popBackStack()
        ft.commitAllowingStateLoss()
    }

    companion object {

        @Volatile
        private var instance: Navigator? = null

        @Synchronized
        fun getInstance(): Navigator? {
            if (instance == null) {
                instance = Navigator()
            }
            return instance
        }
    }

}
