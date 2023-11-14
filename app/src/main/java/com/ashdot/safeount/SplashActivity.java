package com.ashdot.safeount;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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
//        String appsflyerkey = mFirebaseRemoteConfig.getString("themeType1");
//
//        String url = mFirebaseRemoteConfig.getString("themeType2");
//
//
//        String theme = mFirebaseRemoteConfig.getString("themeType3");
//
//
//        Log.d(TAG, "appsflyerkey: " + appsflyerkey);
//        Log.d(TAG, "url: " + url);
//        Log.d(TAG, "theme: " + theme);
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
    }
}