package com.example.arch.navigator

import android.app.Activity
import android.content.Intent
import android.os.Bundle

import androidx.localbroadcastmanager.content.LocalBroadcastManager

import com.example.arch.navigator.setup.ParamEvent

class NavigationManager {
    var legacy = "legacy"
    private val id = 8890
    private val code = "NM"
    var currentActivity: Activity? = null
    var navigationLocalBroadcastManager = "navigationLocalBroadcastManager"
    var paramEventBusSerializable = "paramEventBus"

    private object SingletonHolder {
        val navigationManager = NavigationManager()
    }

    fun goModule(nameModule: String, originActivity: Activity, bundle: Bundle) {
        goOption(nameModule, originActivity, bundle, "", false)
    }

    fun goLegacy(nameModule: String, originActivity: Activity, legacy: String) {
        goOption(nameModule, originActivity, null, legacy, true)
    }

    private fun goOption(
        nameModule: String,
        originActivity: Activity,
        bundle: Bundle?,
        legacy: String,
        isComingBack: Boolean
    ) {
        val paramEvent = bundle?.let {
            ParamEvent.Builder()
                .setId(id)
                .setCode(code)
                .setLegacy(legacy)
                .setNameModule(nameModule)
                .setBundle(it)
                .setOriginActivity(originActivity)
                .setIsComingBack(isComingBack)
                .build()
        }
        paramEvent?.let { post(it) }
    }

    private fun post(paramEvent: ParamEvent) {
        val localBroadcastManager =
            LocalBroadcastManager.getInstance(paramEvent.originActivity!!.baseContext)
        val bundle = Bundle()
        bundle.putSerializable(paramEventBusSerializable, paramEvent)
        val intent = Intent(navigationLocalBroadcastManager)
        intent.putExtras(bundle)
        localBroadcastManager.sendBroadcast(intent)
    }

    companion object {

        val instance: NavigationManager
            get() = SingletonHolder.navigationManager
    }
}
