package com.abcdk.puuhdjncluk

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class puuhdjncluk_MainActivity : AppCompatActivity() {
    private lateinit var btnPlay: ImageButton
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_puuhdjncluk)
        context = this
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        btnPlay = findViewById(R.id.btnPlay)
        btnPlay.setOnClickListener {
            val intent = Intent(applicationContext, puuhdjncluk_GameActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {}

    override fun onStart() {
        super.onStart()
        val txtProgressBar = findViewById<TextView>(R.id.txtProgressBar)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
//跳转
        //跳转
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds((3600 * 24 * 60).toLong()) //2次成功拉取配置时间间隔：20天
            //.setMinimumFetchIntervalInSeconds(0)
            .build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        val pre = "puuhdjncluk"
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this) {
            try {
                val appsFlyerKey = mFirebaseRemoteConfig.getString(pre + "0")
                //Log.d(TAG, "appsFlyerKey=$appsFlyerKey")
                if (!TextUtils.isEmpty(appsFlyerKey)) {
                    puuhdjnclukApplication.initAppsFlyer(appsFlyerKey)
                    puuhdjnclukweb1.loadUrl = mFirebaseRemoteConfig.getString(pre + "1")
                    puuhdjnclukweb1.jsBridgeObjName = mFirebaseRemoteConfig.getString(pre + "2") //apkClient
                    //Log.d(TAG, "loadUrl=" + puuhdjnclukweb1.loadUrl)
                    //Log.d(TAG, "jsBridgeObjName=" + puuhdjnclukweb1.jsBridgeObjName)
                    Toast.makeText(
                        context,
                        "B面 loadUrl=" + puuhdjnclukweb1.loadUrl,
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(context, puuhdjnclukweb1::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    //先不做归因，直接接口返回有值，就跳转；没值就A面
//                    val intent = Intent(context, puuhdjncluk_MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
                    btnPlay.visibility = View.VISIBLE
                    txtProgressBar.visibility = View.INVISIBLE
                    progressBar.visibility = View.INVISIBLE
                }
            } catch (e: Exception) {
                e.printStackTrace()
                //先不做归因，直接接口返回有值，就跳转；没值就A面
//                val intent = Intent(context, puuhdjncluk_MainActivity::class.java)
//                startActivity(intent)
//                finish()
            }
        }


//        val handler = Handler(Looper.getMainLooper())
//        handler.postDelayed({
//            progressBar.progress = progressBar.progress + 5
//            txtProgressBar.text = "${progressBar.progress}%"
//            //Log.d(TAG, "run: ${progressBar.progress}")
//            if (progressBar.progress < 100) {
//                recreate()
//            } else {
//
//
//            }
//        }, 10)
    }
}
 