package com.example.arch.navigator.setup

import android.app.Activity
import android.os.Bundle

import java.io.Serializable

class ParamEvent : Serializable {
    var id: Int ? = null
    var code: String? = null
    var legacy: String? = null
    var nameModule: String? = null
    var bundle: Bundle? = null
    var isComingBack: Boolean? = null
    var originActivity: Activity? = null
    var destinationActivity: Activity? = null

    constructor(builder: Builder) {
        this.id = builder.id
        this.code = builder.code
        this.isComingBack = builder.isComingBack
        this.legacy = builder.legacy
        this.nameModule = builder.nameModule
        this.bundle = builder.bundle
        this.originActivity = builder.originActivity
        this.destinationActivity = builder.destinationActivity
    }

    constructor() {

    }

    class Builder {
        internal var id: Int = 0
        internal var code: String? = null
        internal var isComingBack: Boolean = false
        internal var legacy: String? = null
        internal var nameModule: String? = null
        internal var bundle: Bundle? = null
        internal var originActivity: Activity? = null
        internal var destinationActivity: Activity? = null

        fun setId(id: Int): Builder {
            this.id = id
            return this
        }

        fun setCode(code: String): Builder {
            this.code = code
            return this
        }

        fun setIsComingBack(isComingBack: Boolean): Builder {
            this.isComingBack = isComingBack
            return this
        }

        fun setLegacy(legacy: String): Builder {
            this.legacy = legacy
            return this
        }

        fun setNameModule(nameModule: String): Builder {
            this.nameModule = nameModule
            return this
        }

        fun setBundle(bundle: Bundle): Builder {
            this.bundle = bundle
            return this
        }

        fun setOriginActivity(originActivity: Activity): Builder {
            this.originActivity = originActivity
            return this
        }

        fun setDestinationActivity(destinationActivity: Activity): Builder {
            this.destinationActivity = destinationActivity
            return this
        }

        fun build(): ParamEvent {
            return ParamEvent(this)
        }
    }

    companion object {

        fun Builder(): Builder {
            return Builder()
        }
    }
}
