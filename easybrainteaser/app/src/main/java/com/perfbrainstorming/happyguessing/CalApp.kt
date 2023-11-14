package com.perfbrainstorming.happyguessing

import android.app.Application
import androidx.multidex.MultiDexApplication
import code.sdk.core.VestSDK

/**
 * ConfigurationManager[(ConfigurationManager.java:49)] $ Fail to parse configuration
java.io.FileNotFoundException: HappyRabbitCalculator12111111111111
 */
class CalApp : Application() {
    override fun onCreate() {

        super.onCreate()
//        VestSDK.setLoggable(true)
//        VestSDK.init(baseContext, "HappyRabbitCalculator")
    }
}