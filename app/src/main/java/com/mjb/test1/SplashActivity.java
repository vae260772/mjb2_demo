package com.mjb.test1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.ConfigUpdate;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.mjb.test1.mj_b_mian.BWebMain;

public class SplashActivity extends AppCompatActivity {
    String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
//        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
//                .setMinimumFetchIntervalInSeconds(3)
//                .build();
//        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
//
//        String appsflyerkey = mFirebaseRemoteConfig.getString("appsflyerkey");
//
//        String url = mFirebaseRemoteConfig.getString("url");
//        Log.d(TAG, "appsflyerkey: " + appsflyerkey);
//        Log.d(TAG, "url: " + url);

//        mFirebaseRemoteConfig.fetchAndActivate()
//                .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Boolean> task) {
//                        if (task.isSuccessful()) {
//                            boolean updated = task.getResult();
//                            Log.d(TAG, "Config params updated: " + updated);
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
//                Log.d(TAG, "===Updated keys: " + configUpdate.getUpdatedKeys());
//
//
//
//                String appsflyerkey = mFirebaseRemoteConfig.getString("appsflyerkey");
//
//                String url = mFirebaseRemoteConfig.getString("url");
//                Log.d(TAG, "====appsflyerkey: " + appsflyerkey);
//                Log.d(TAG, "====url: " + url);
//                mFirebaseRemoteConfig.activate().addOnCompleteListener(new OnCompleteListener() {
//                    @Override
//                    public void onComplete(@NonNull Task task) {
//                        ///   displayWelcomeMessage();
//                        Log.d(TAG, "task: " + task);
//                    }
//                });
//            }
//
//            @Override
//            public void onError(FirebaseRemoteConfigException error) {
//                Log.w(TAG, "Config update error with code: " + error.getCode(), error);
//            }
//        });


        //////////////
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (DemoApplication.isAd) {
                    startActivity(new Intent(SplashActivity.this, BWebMain.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));

                }
                finish();
            }
        }, 3000);


    }
}