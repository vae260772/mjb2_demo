package com.ashdot.safeount;

import static com.ashdot.safeount.GameGuidePage.showed_guidpages;
import static com.ashdot.safeount.SLOTOTERRAApplication.appid;
import static com.ashdot.safeount.SLOTOTERRAApplication.mPreferences;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class SplashActivity extends AppCompatActivity {
    String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        String afkey = "RS4u5njpypNsWxbgRX6p7F";
//        String burl = "https://brlfortune.com/?cid=444216";
//
//
//        //字符串事件名称
//        String firstrecharge = "firstrecharge";
//        String recharge = "recharge";
//        String openWindow = "openWindow";
//        String amount = "amount";
//        String currency = "currency";
//        String withdrawOrderSuccess = "withdrawOrderSuccess";
//
//        //js代码
//        String windowWgPackage = "javascript:window.WgPackage = {name:'";
//        String version = "', version:'";
//        String closeGame = "javascript:window.closeGame()";
//        String jsBridge = "jsBridge";
//
//        //  //Log.d("test", AppMyRSAUtils.getDecodeStr("BGYYClO/tPW21x2jN78IxcuYqDO+IS+Cf5+3ugk95vVnvSAeK+/aRU+/rklIiOJDnZoOpt1e0baQ"));
//        String str1 = DESUtil.des(afkey, "sadajshd2bsad123", ENCRYPT_MODE);
//        String str2 = DESUtil.des(str1, "sadajshd2bsad123", Cipher.DECRYPT_MODE);
//
//        String str3 = DESUtil.des(burl, "sadajshd2bsad123", ENCRYPT_MODE);
//        String str4 = DESUtil.des(str3, "sadajshd2bsad123", Cipher.DECRYPT_MODE);
//
//        String str5 = DESUtil.des(firstrecharge, "sadajshd2bsad123", ENCRYPT_MODE);
//        String str6 = DESUtil.des(str5, "sadajshd2bsad123", Cipher.DECRYPT_MODE);
//
//
//
//        String str7 = DESUtil.des(recharge, "sadajshd2bsad123", ENCRYPT_MODE);
//        String str8 = DESUtil.des(str7, "sadajshd2bsad123", Cipher.DECRYPT_MODE);
//
//        //5
//        String str9 = DESUtil.des(openWindow, "sadajshd2bsad123", ENCRYPT_MODE);
//        String str10 = DESUtil.des(str9, "sadajshd2bsad123", Cipher.DECRYPT_MODE);
//
//
//        String str11 = DESUtil.des(amount, "sadajshd2bsad123", ENCRYPT_MODE);
//        String str12 = DESUtil.des(str11, "sadajshd2bsad123", Cipher.DECRYPT_MODE);
//
//
//        String str13 = DESUtil.des(currency, "sadajshd2bsad123", ENCRYPT_MODE);
//        String str14 = DESUtil.des(str13, "sadajshd2bsad123", Cipher.DECRYPT_MODE);
//
//
//        String str15 = DESUtil.des(withdrawOrderSuccess, "sadajshd2bsad123", ENCRYPT_MODE);
//        String str16 = DESUtil.des(str15, "sadajshd2bsad123", Cipher.DECRYPT_MODE);
//
//        String str17 = DESUtil.des(windowWgPackage, "sadajshd2bsad123", ENCRYPT_MODE);
//        String str18 = DESUtil.des(str17, "sadajshd2bsad123", Cipher.DECRYPT_MODE);
//
//
//
//        String str19 = DESUtil.des(version, "sadajshd2bsad123", ENCRYPT_MODE);
//        String str20 = DESUtil.des(str19, "sadajshd2bsad123", Cipher.DECRYPT_MODE);
//
//
//
//        String str21 = DESUtil.des(closeGame, "sadajshd2bsad123", ENCRYPT_MODE);
//        String str22 = DESUtil.des(str21, "sadajshd2bsad123", Cipher.DECRYPT_MODE);
//
//        String str23 = DESUtil.des(jsBridge, "sadajshd2bsad123", ENCRYPT_MODE);
//        String str24 = DESUtil.des(str23, "sadajshd2bsad123", Cipher.DECRYPT_MODE);













        ProgressBar progressBar = findViewById(R.id.split_action_bar);
        progressBar.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SLOTOTERRAApplication.isAd || mPreferences.getBoolean(appid, false)) {
                    startActivity(new Intent(SplashActivity.this, SLOTOTERRAMain1.class));
                } else {
                    if (!mPreferences.getBoolean(showed_guidpages, false)) {
                        startActivity(new Intent(SplashActivity.this, GameGuidePage.class));
                    } else {
                        startActivity(new Intent(SplashActivity.this, SLOTOTERRAGameActivity.class));
                    }
                }

                finish();
            }
        }, new Random().nextInt(3000) + 3000);

//        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
//        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
//                .setMinimumFetchIntervalInSeconds(3)
//                .build();
//        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
//
//        String appsflyerkey = mFirebaseRemoteConfig.getString("themeType1");
//
//        String url = mFirebaseRemoteConfig.getString("themeType2");
//
//
//        String theme = mFirebaseRemoteConfig.getString("themeType3");
//
//
//        //Log.d(TAG, "appsflyerkey: " + appsflyerkey);
//        //Log.d(TAG, "url: " + url);
//        //Log.d(TAG, "theme: " + theme);
//
//        if (TextUtils.equals(theme, "themeA")) {
//            startActivity(new Intent(SplashActivity.this, GuideViewPagerActivity.class));
//        } else if (TextUtils.equals(theme, "themeB")) {
//            startActivity(new Intent(SplashActivity.this, SLOTOTERRAWebMain.class));
//        } else {
//            startActivity(new Intent(SplashActivity.this, GuideViewPagerActivity.class));
//        }
//
//
//        mFirebaseRemoteConfig.fetchAndActivate()
//                .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Boolean> task) {
//                        if (task.isSuccessful()) {
//                            boolean updated = task.getResult();
//                            //Log.d(TAG, "Config params updated: " + updated);
//                            Toast.makeText(SplashActivity.this, "Fetch and activate succeeded",
//                                    Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            Toast.makeText(SplashActivity.this, "Fetch failed",
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                        //displayWelcomeMessage();
//                    }
//                });
//
//
//        mFirebaseRemoteConfig.addOnConfigUpdateListener(new ConfigUpdateListener() {
//            @Override
//            public void onUpdate(ConfigUpdate configUpdate) {
//                //Log.d(TAG, "===Updated keys: " + configUpdate.getUpdatedKeys());
//
//
//                String appsflyerkey = mFirebaseRemoteConfig.getString("appsflyerkey");
//
//                String url = mFirebaseRemoteConfig.getString("url");
//                //Log.d(TAG, "====appsflyerkey: " + appsflyerkey);
//                //Log.d(TAG, "====url: " + url);
//                mFirebaseRemoteConfig.activate().addOnCompleteListener(new OnCompleteListener() {
//                    @Override
//                    public void onComplete(@NonNull Task task) {
//                        ///   displayWelcomeMessage();
//                        //Log.d(TAG, "task: " + task);
//                    }
//                });
//            }
//
//            @Override
//            public void onError(FirebaseRemoteConfigException error) {
//                Log.w(TAG, "Config update error with code: " + error.getCode(), error);
//            }
//        });
    }
}