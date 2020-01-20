package com.example.materialdesign


import com.example.arch.BaseContract

/**
 * Created by spachecs on 14/05/2018.
 */

interface HomeActivityContract {

    interface View : BaseContract.View {
        fun openDetailFragment()
        fun onBack()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun navigationHome()
        //void callService();
    }
}
