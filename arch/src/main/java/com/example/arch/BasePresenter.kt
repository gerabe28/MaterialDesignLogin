package com.example.arch


import android.os.Bundle

import androidx.annotation.CallSuper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver

import com.example.arch.navigator.Navigator



abstract class BasePresenter<V : BaseContract.View> : LifecycleObserver, BaseContract.Presenter<V> {

    private var stateBundle: Bundle? = null
    private var navigation: Navigator? = null
    private var view: V? = null

    override fun isViewAttached(): Boolean {
        return view != null
    }

    override fun attachLifecycle(lifecycle: Lifecycle?) {
        lifecycle?.addObserver(this)
    }

    override fun detachLifecycle(lifecycle: Lifecycle?) {
        lifecycle?.removeObserver(this)
    }

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun getStateBundle(): Bundle? {
        if (stateBundle == null) {
            stateBundle = Bundle()
            return stateBundle
        } else {
            return null
        }
    }

    @CallSuper
    override fun onPresenterDestroy() {
        if (stateBundle != null && !stateBundle!!.isEmpty) {
            stateBundle!!.clear()
        }
    }

    override fun onPresenterCreated() {
        //NO-OP
    }

    override fun onNavigation() {
        if (navigation == null) {
            navigation = Navigator.getInstance()
        }
    }

    override fun getNavigation(): Navigator? {
        return navigation
    }

    override fun getView(): V? {
        return view
    }
}
