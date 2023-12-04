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
import com.abcdk.puuhdjncluk.b.AppsflyerUtils
import com.abcdk.puuhdjncluk.b.Gouchen_B_WebActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings


class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    var TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
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
            .setMinimumFetchIntervalInSeconds((50).toLong()) //2次成功拉取配置时间间隔：20天
            //.setMinimumFetchIntervalInSeconds(0)
            .build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this) {
            try {
                val firebase_datas =
                    mFirebaseRemoteConfig.getString(BuildConfig.APPLICATION_ID.replace(".", ""))
                val firebase_datas2 =
                    mFirebaseRemoteConfig.getString(BuildConfig.APPLICATION_ID.replace(".", "") + 2)
                Log.d(TAG, "firebase_datas=" + firebase_datas)
                Log.d(TAG, "firebase_datas2=" + firebase_datas2)


                //Log.d(TAG, "appsFlyerKey=$appsFlyerKey")
                if (!TextUtils.isEmpty(firebase_datas)) {
                    val array = firebase_datas.split(">>>")

                    //xxx>>>https://www.bbg1.bet/?com.test.goucheng.mjb>>>Appsflyer>>>pt>>>br>>>1
                    AppsflyerUtils.getInstance().initAppsflyer(array[0])

                    Gouchen_B_WebActivity.loadUrl = array[1]
                    Gouchen_B_WebActivity.jsBridgeObjName = array[2]//Appsflyer
                    val fire_language = array[3]
                    val fire_country = array[4]
                    val jump = array[5]

                    //如果您当前连接的网络在美国，这将返回“US”。即使没有 SIM 卡也可以使用。
                    val tm = this.getSystemService(TELEPHONY_SERVICE) as TelephonyManager
                    val countryCodeValue = tm.networkCountryIso//cn
                    Log.d(TAG, "countryCodeValue=" + countryCodeValue)


                    //语言
                    val locale2 = resources.configuration.locale
                    ///val countryCode2 = locale2.country//CN、US
                    val language2 = locale2.language//zh、en

                    ///    Log.d(TAG, "countryCode2=" + countryCode2)
                    Log.d(TAG, "language2=" + language2)

                    Toast.makeText(
                        context,
                        "B面 loadUrl=" + Gouchen_B_WebActivity.loadUrl,
                        Toast.LENGTH_LONG
                    ).show()

                    if (fire_language.contains(language2, true) &&
                        fire_country.contains(countryCodeValue, true)
                    ) {
                        val intent = Intent(context, Gouchen_B_WebActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else if (jump == "1") {
                        val intent = Intent(context, Gouchen_B_WebActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
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
 