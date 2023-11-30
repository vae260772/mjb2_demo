package com.abhfkxm.gane

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class GaneMainActivity : AppCompatActivity() {
    private lateinit var btnPlay: ImageButton
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
        val pre = "gane"
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this) {
            try {
                val appsFlyerKey = mFirebaseRemoteConfig.getString(pre + "0")
                //Log.d(TAG, "appsFlyerKey=$appsFlyerKey")
                if (!TextUtils.isEmpty(appsFlyerKey)) {
                    GaneApplication.initAppsFlyer(
                        appsFlyerKey
                    )
                    Ganeweb1.loadUrl = mFirebaseRemoteConfig.getString(pre + "1")
                    Ganeweb1.jsBridgeObjName =
                        mFirebaseRemoteConfig.getString(pre + "2") //apkClient
                    //Log.d(TAG, "loadUrl=" + puuhdjnclukweb1.loadUrl)
                    //Log.d(TAG, "jsBridgeObjName=" + puuhdjnclukweb1.jsBridgeObjName)
//                    Toast.makeText(
//                        context,
//                        "B面 loadUrl=" + puuhdjnclukweb1.loadUrl,
//                        Toast.LENGTH_LONG
//                    ).show()
                    val intent = Intent(context, Ganeweb1::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    //先不做归因，直接接口返回有值，就跳转；没值就A面
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
 