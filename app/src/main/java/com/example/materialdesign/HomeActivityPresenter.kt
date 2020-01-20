package com.example.materialdesign

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent

import com.example.arch.BasePresenter


/**
 * Created by spachecs on 14/05/2018.
 */

class HomeActivityPresenter : BasePresenter<HomeActivityContract.View>(),
    HomeActivityContract.Presenter {

    @OnLifecycleEvent(value = Lifecycle.Event.ON_CREATE)
    protected fun onCreate() {
        if (isViewAttached())
            getView()!!.openDetailFragment()
    }

    override fun navigationHome() {
        getView()!!.onBack()
    }


}
