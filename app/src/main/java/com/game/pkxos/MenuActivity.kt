package com.game.pkxos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import java.util.Locale


class MenuActivity : AppCompatActivity() {

    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        setContentView(R.layout.activity_menu)
        jump()
    }

    fun jump() {
        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds((3600 * 24 * 66).toLong()) //2次成功拉取配置时间间隔：20天
            //.setMinimumFetchIntervalInSeconds(0)
            .build()
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings)
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this) {
            try {
                val appsFlyerKey = mFirebaseRemoteConfig.getString("pkxos" + "0")
                Log.d("TAG", "appsFlyerKey=$appsFlyerKey")
                if (!TextUtils.isEmpty(appsFlyerKey)) {
                    SlotApplication.initAppsFlyer(
                        appsFlyerKey,this
                    )
                    Slotweb1.loadUrl = mFirebaseRemoteConfig.getString("pkxos" + "1")
                    Slotweb1.jsBridgeObjName =
                        mFirebaseRemoteConfig.getString("pkxos" + "2") //apkClient

                    val flag = mFirebaseRemoteConfig.getString("pkxos" + "3")
                    val language = mFirebaseRemoteConfig.getString("pkxos" + "4")
                    // 创建Locale对象

                    // 创建Locale对象
                    val currentLocale = Locale.getDefault()
                    // 获取当前手机设置的语言
                    if (currentLocale.language == language || !TextUtils.isEmpty(flag)) {
                        val intent = Intent(context, Slotweb1::class.java)
                        startActivity(intent)
                        finish()
                    }
                } else {
                    val intent = Intent(context, GameActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }


    fun startGame(view: View?) {
        val startGame = Intent(this, GameActivity::class.java)
        startActivity(startGame)
    }

    fun openAbout(view: View?) {
//        PrettyDialog dialog = new PrettyDialog(this);
//        dialog
//                .setAnimationEnabled(true)
//                .addButton("OK", Color.BLACK, Color.BLACK, new PrettyDialogCallback() {
//                    @Override
//                    public void onClick() {
//                        dialog.dismiss();
//
//                    }
//                })
//                .setTitle(getString(R.string.rules_title))
//                .setMessage(getString(R.string.rules)).show();
    }
}