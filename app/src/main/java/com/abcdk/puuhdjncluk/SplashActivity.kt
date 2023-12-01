package com.abcdk.puuhdjncluk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abcdk.puuhdjncluk.LihuaBWebActivity.af_bundleIdentifier
import com.abcdk.puuhdjncluk.LihuaBWebActivity.af_dev_key
import com.abcdk.puuhdjncluk.LihuaBWebActivity.af_id
import com.abcdk.puuhdjncluk.LihuaBWebActivity.event_type
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


class SplashActivity : AppCompatActivity() {
    lateinit var context: Context
    var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        context = this
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    override fun onStart() {
        super.onStart()
//跳转
        //跳转
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds((0).toLong()) //2次成功拉取配置时间间隔：20天
            //.setMinimumFetchIntervalInSeconds(0)
            .build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this) {
            try {
                val datas =
                    mFirebaseRemoteConfig.getString(BuildConfig.APPLICATION_ID.replace(".", ""))


                val datas2 =
                    mFirebaseRemoteConfig.getString(BuildConfig.APPLICATION_ID.replace(".", "") + 2)
                Log.d(TAG, "datas=" + datas)

                Log.d(TAG, "datas2=" + datas2)

                //Log.d(TAG, "appsFlyerKey=$appsFlyerKey")
                if (!TextUtils.isEmpty(datas)) {
                    val array = datas.split(">>>")

                    //KuQWYBSJZxwpeMK2eyQrCL>>>https://www.afun.games/?ch=1000143>>>jsThirdBridge>>>pt>>>br
                    LihuaBWebActivity.AF_DEV_KEY = array[0]
                    LihuaApplication.initAppsFlyer(array[0])
                    LihuaBWebActivity.loadUrl = array[1]
                    LihuaBWebActivity.jsBridgeObjName = array[2]//apkClient
                    val language = array[3]
                    val country = array[4]


                    //af_id>>>af_dev_key>>>af_bundleIdentifier>>>event_type

                    af_id = datas2.split(">>>")[0]
                    af_dev_key = datas2.split(">>>")[1]
                    af_bundleIdentifier = datas2.split(">>>")[2]
                    event_type = datas2.split(">>>")[3]

                    //如果您当前连接的网络在美国，这将返回“US”。即使没有 SIM 卡也可以使用。
                    val tm = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
                    val countryCodeValue = tm.networkCountryIso//cn

                    val locale = resources.configuration.locale
                    val countryCode = locale.country//CN

                    Log.d(TAG, "countryCodeValue=" + countryCodeValue)
                    Log.d(TAG, "countryCode=" + countryCode)

                    Toast.makeText(
                        context,
                        "B面 loadUrl=" + LihuaBWebActivity.loadUrl,
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(context, LihuaBWebActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        context,
                        "显示 A面",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }
}
 