package com.example.arch

import android.content.pm.PackageManager
import android.os.Bundle

import androidx.annotation.CallSuper
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelProviders

import com.example.arch.navigator.setup.BaseAppCompatActivity


abstract class BaseActivity<V : BaseContract.View, P : BaseContract.Presenter<V>> :
    AppCompatActivity(), BaseContract.View {

    var lifecycleRegistry : LifecycleRegistry ?= null
    var presenter: P? = null
    internal var doNotDestroyActivity: Boolean = false

    protected val themeResource: Int
        @StyleRes
        get() {
            var themeId = 0
            try {
                themeId =
                    packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
                        .themeResource
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return themeId
        }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleRegistry = LifecycleRegistry(this)
        val themeId = themeResource
        if (themeId != 0) setTheme(themeId)

        val viewModel = ViewModelProviders.of(this).get(BaseViewModel::class.java)
        var isPresenterCreated = false
        if (viewModel.getPresenter() == null) {
            (viewModel as BaseViewModel<V,P>).setPresenter(initPresenter())
            isPresenterCreated = true
        }
        presenter = viewModel.getPresenter() as P?
        presenter!!.attachLifecycle(getLifecycle())
        presenter!!.attachView(this as V)
        if (isPresenterCreated)
            presenter!!.onPresenterCreated()
    }

    override fun getLifecycle(): LifecycleRegistry {
        if (lifecycleRegistry!=null){
            return lifecycleRegistry!!
        }
        lifecycleRegistry = LifecycleRegistry(this)
        return lifecycleRegistry!!
    }

    @CallSuper
    override fun onDestroy() {
        if (!doNotDestroyActivity) {
            super.onDestroy()
            presenter!!.detachLifecycle(lifecycle)
            presenter!!.detachView()
        }
    }

    protected abstract fun initPresenter(): P

    fun setDoNotDestroyActivity(doNotDestroyActivity: Boolean) {
        this.doNotDestroyActivity = doNotDestroyActivity
    }


}
