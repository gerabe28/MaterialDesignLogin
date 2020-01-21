package com.example.arch


import android.os.Bundle

import androidx.lifecycle.Lifecycle

import com.example.arch.navigator.Navigator


interface BaseContract {

    interface View {
        fun replaceFragment(n: Navigator)
    }

    interface Presenter<V : View> {

        fun getStateBundle() : Bundle?

        fun getView() : V?

        fun isViewAttached(): Boolean

        fun getNavigation(): Navigator?

        fun attachLifecycle(lifecycle: Lifecycle?)

        fun detachLifecycle(lifecycle: Lifecycle?)

        fun attachView(view: V)

        fun detachView()

        fun onPresenterCreated()

        fun onPresenterDestroy()

        fun onNavigation()


    }
}
