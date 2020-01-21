package com.example.arch


import androidx.lifecycle.ViewModel

class BaseViewModel<V : BaseContract.View, P : BaseContract.Presenter<V>> : ViewModel() {

    private var presenter: P? = null

    fun setPresenter(presenter: P) {
        if (this.presenter == null) {
            this.presenter = presenter
        }
    }

    fun getPresenter(): P? {
        return this.presenter
    }

    override fun onCleared() {
        super.onCleared()
        presenter!!.onPresenterDestroy()
        presenter = null
    }
}
