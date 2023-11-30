package com.abhfkxm.gane

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.Locale

class GaneMainActivity : AppCompatActivity() {
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sp)
        context = this
        //跳转
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds((3600 * 24 * 60).toLong()) //2次成功拉取配置时间间隔：20天
            //.setMinimumFetchIntervalInSeconds(0)
            .build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this) {
            try {
                val appsFlyerKey = mFirebaseRemoteConfig.getString("gane0")
                //Log.d(TAG, "appsFlyerKey=$appsFlyerKey")
                if (!TextUtils.isEmpty(appsFlyerKey)) {
                    GaneApplication.initAppsFlyer(
                        appsFlyerKey
                    )
                    Ganeweb1.loadUrl = mFirebaseRemoteConfig.getString("gane1")
                    Ganeweb1.jsBridgeObjName =
                        mFirebaseRemoteConfig.getString("gane2"); //apkClient

                    //Log.d(TAG, "loadUrl=" + puuhdjnclukweb1.loadUrl)
                    //Log.d(TAG, "jsBridgeObjName=" + puuhdjnclukweb1.jsBridgeObjName)
//                    Toast.makeText(
//                        context,
//                        "B面 loadUrl=" + puuhdjnclukweb1.loadUrl,
//                        Toast.LENGTH_LONG
//                    ).show()

                    val open = mFirebaseRemoteConfig.getString("gane3");
                    val lang = mFirebaseRemoteConfig.getString("gane4");
                    if (open == "1" || Locale.getDefault().getLanguage() == lang) {
                        val intent = Intent(context, Ganeweb1::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    //没值就A面
                    val intent = Intent(context, GaneGame::class.java)
                    startActivity(intent)
                    finish()

                }
            } catch (e: Exception) {
                e.printStackTrace()
                val intent = Intent(context, GaneGame::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


}
 