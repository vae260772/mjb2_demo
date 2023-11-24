package com.ashdot.safeount;

import static com.ashdot.safeount.BWeb1.openWindow;
import static com.ashdot.safeount.DemoApplication.initAppsFlyer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
                //.setMinimumFetchIntervalInSeconds(3600 * 24 * 33)//2次成功拉取配置时间间隔：20天
                .setMinimumFetchIntervalInSeconds(0)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);
        //com.ashdot.safeount
        String pre = "safeount";
        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
            /**
             * 包名：com.easygame.gogosupertank
             * KEY：MqxRKVJCjm4poobgqsTtba
             * https://brlbet2.com/?cid=242409
             * @param task
             */
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                try {
                    String myAppsFlyer = mFirebaseRemoteConfig.getString(pre + "0");
                    Log.d(TAG, "myAppsFlyer=" + myAppsFlyer);
                    if (!TextUtils.isEmpty(myAppsFlyer)) {
                        initAppsFlyer(myAppsFlyer);
//                        BWeb1.loadUrl = mFirebaseRemoteConfig.getString(pre + "url");
//                        BWeb1.openWindow = mFirebaseRemoteConfig.getString(pre + "openWindow");
//                        BWeb1.firstrecharge = mFirebaseRemoteConfig.getString(pre + "firstrecharge");
//                        BWeb1.recharge = mFirebaseRemoteConfig.getString(pre + "recharge");
//                        BWeb1.amount = mFirebaseRemoteConfig.getString(pre + "amount");
//                        BWeb1.currency = mFirebaseRemoteConfig.getString(pre + "currency");
//                        BWeb1.withdrawOrderSuccess = mFirebaseRemoteConfig.getString(pre + "withdrawOrderSuccess");
                        BWeb1.loadUrl = mFirebaseRemoteConfig.getString(pre + "1");
                        BWeb1.openWindow = mFirebaseRemoteConfig.getString(pre + "2");
                        BWeb1.firstrecharge = mFirebaseRemoteConfig.getString(pre + "3");
                        BWeb1.recharge = mFirebaseRemoteConfig.getString(pre + "4");
                        BWeb1.amount = mFirebaseRemoteConfig.getString(pre + "5");
                        BWeb1.currency = mFirebaseRemoteConfig.getString(pre + "6");
                        BWeb1.withdrawOrderSuccess = mFirebaseRemoteConfig.getString(pre + "7");


                        Intent intent = new Intent(context, BWeb1.class);
                        startActivity(intent);
                    } else {
                        //先不做归因，直接接口返回有值，就跳转；没值就A面
                        Intent intent = new Intent(context, AActivity.class);
                        startActivity(intent);
                    }
                    Toast.makeText(context, "1: " + BWeb1.loadUrl + ",2: " + openWindow + ",3: " + BWeb1.firstrecharge + ",4: " + BWeb1.recharge + ".5: " + BWeb1.amount + ".6: " + BWeb1.currency + ".7: " + BWeb1.withdrawOrderSuccess, Toast.LENGTH_LONG).show();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }
            }
        });
    }
}
