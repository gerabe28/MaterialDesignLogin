package com.example.arch

import android.os.Bundle
import android.view.View

import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModelProviders


abstract class BaseFragment<V : BaseContract.View, P : BaseContract.Presenter<V>> : Fragment(),
    BaseContract.View {

    private val lifecycleRegistry = LifecycleRegistry(this)
    protected var presenter: P? = null

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var viewModel = ViewModelProviders.of(this).get(BaseViewModel::class.java)
        var isPresenterCreated = false
        if (viewModel.getPresenter() == null) {
            (viewModel as BaseViewModel<V,P>).setPresenter(initPresenter())
            isPresenterCreated = true
        }
        presenter = viewModel.getPresenter() as P?
        presenter!!.attachLifecycle(lifecycle)
        presenter!!.attachView(this as V)
        if (isPresenterCreated)
            presenter!!.onPresenterCreated()
    }

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

    @CallSuper
    override fun onDestroyView() {
        super.onDestroyView()
        presenter!!.detachLifecycle(lifecycle)
        presenter!!.detachView()
    }

    protected abstract fun initPresenter(): P

}
