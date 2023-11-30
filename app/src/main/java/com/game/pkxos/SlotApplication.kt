package com.game.pkxos

import android.app.Application
import android.content.Context
import android.util.Log
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.appsflyer.attribution.AppsFlyerRequestListener

class SlotApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }

    companion object {

        /**
         * 包名：com.easygame.gogosupertank
         * KEY：MqxRKVJCjm4poobgqsTtba
         * https://brlbet2.com/?cid=242409
         */
        fun initAppsFlyer(afkey: String?,context: Context) {
            //Log.d(TAG, "initAppsFlyer afkey=" + afkey);
            AppsFlyerLib.getInstance().setMinTimeBetweenSessions(0)
            AppsFlyerLib.getInstance().setDebugLog(true)

            // app flay初始化
            AppsFlyerLib.getInstance().init(afkey!!, object : AppsFlyerConversionListener {
                override fun onConversionDataSuccess(map: Map<String, Any>) {
                    //   map={install_time=2023-09-25 13:27:12.578, af_status=Organic, af_message=organic install, is_first_launch=true}
                    Log.d("TAG", "onConversionDataSuccess map=" + map);
                }

                override fun onConversionDataFail(s: String) {
                    //Log.d(TAG, "onConversionDataFail=" + s);
                }

                override fun onAppOpenAttribution(map: Map<String, String>) {
                    //Log.d(TAG, "onAppOpenAttribution=" + map);
                }

                override fun onAttributionFailure(s: String) {
                    //Log.d(TAG, "onAttributionFailure=" + s);
                }
            },context.applicationContext)
            AppsFlyerLib.getInstance()
                .start(context.applicationContext!!, afkey, object : AppsFlyerRequestListener {
                    override fun onSuccess() {
                        Log.d("TAG", "Launch sent successfully, got 200 response code from server");
                    }

                    override fun onError(i: Int, s: String) {
                        //Log.d((TAG, "Launch failed to be sent:\n" + "Error code: " + i + "\n" + "Error description: " + s);
                    }
                })
        }
    }
}