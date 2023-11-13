package com.ashdot.safeount;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gcssloop.encrypt.unsymmetric.RSAUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.ConfigUpdate;
import com.google.firebase.remoteconfig.ConfigUpdateListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigException;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.Map;

public class SplashActivity extends AppCompatActivity {
    String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        System.out.println("rsa");
// des 字符串加密解密测试
        byte[] data = "GcsSloop中文".getBytes();

// 密钥与数字签名获取
        //rsa获取公钥： MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC1UDzErXNcdOL5cxcAd+kjH+9pfQJ4iNrcSoAN
        //rsa获取私钥： MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALVQPMStc1x04vlzFwB36SMf72l9

        Map<String, Object> keyMap = null;
        try {
            keyMap = RSAUtil.getKeyPair();
            String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
                    "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA6SOK1I7M9eRIV5nWSrJ3\n" +
                    "VmLPBOt02dOCHLWoG91zTPJ6VqOUd3aN6UrwZ6wms5/IY76Tsv4rejRmlym7VFc4\n" +
                    "vciQE6zh0+eIzZmWpDyDLB1by2f/PDf5YRzW4+5BL29wrhwO/tPdUswBUycy015S\n" +
                    "vGcNZ3ePRQqebhBcAxx046E3tgAyo3Cn6iVNQ6MINeZB46EO6A4E6zi31ySAZrtn\n" +
                    "f7wXEAIIdEjLFdbtGfhnhofUo23mXxuMRihRgjBQE0CXH+xHH9aFr22dJkGbBYTI\n" +
                    "7UssQG8poEp8eels5lgYQBGLLxMnKyW6XQMC4X7FrzAki4lBvQCtxiIW64uMKfGd\n" +
                    "9IJ79uP/dx9SwoUjye4y2JfTQdFHtz76N+vlSnbFd9m3bbgQwZapE7aiYomPDU6+\n" +
                    "ScYoNW5+Uj+7tMkQBfSKCDgen717LPL7nsUlLa7S30jDkOzUtylb9/XKkvtNo7kB\n" +
                    "WRCar8EEschm1fTCvP9TRb5bYXSi3t0JaRVOeeNbuAD+diyO56voXrKrxUzAh/N/\n" +
                    "lahgTYcn1M3/nhziYNnnFHP2xNFa3w+oPAsAc5NAKdufPriKf2IGq11Q2RQGlP6F\n" +
                    "n78p3GwkJdKZpwTuphShdXRtOAsJ4R7Xbacf6ejleM3CUIWOGF5yYgw/91NgDndE\n" +
                    "ASe/YAqJrFEMLIIVS0QkvEMCAwEAAQ==\n" +
                    "-----END PUBLIC KEY-----\n";


            System.out.println("rsa获取公钥： " + publicKey);
            String privateKey = "-----BEGIN PRIVATE KEY-----\n" +
                    "MIIJQgIBADANBgkqhkiG9w0BAQEFAASCCSwwggkoAgEAAoICAQDpI4rUjsz15EhX\n" +
                    "mdZKsndWYs8E63TZ04Ictagb3XNM8npWo5R3do3pSvBnrCazn8hjvpOy/it6NGaX\n" +
                    "KbtUVzi9yJATrOHT54jNmZakPIMsHVvLZ/88N/lhHNbj7kEvb3CuHA7+091SzAFT\n" +
                    "JzLTXlK8Zw1nd49FCp5uEFwDHHTjoTe2ADKjcKfqJU1Dowg15kHjoQ7oDgTrOLfX\n" +
                    "JIBmu2d/vBcQAgh0SMsV1u0Z+GeGh9SjbeZfG4xGKFGCMFATQJcf7Ecf1oWvbZ0m\n" +
                    "QZsFhMjtSyxAbymgSnx56WzmWBhAEYsvEycrJbpdAwLhfsWvMCSLiUG9AK3GIhbr\n" +
                    "i4wp8Z30gnv24/93H1LChSPJ7jLYl9NB0Ue3Pvo36+VKdsV32bdtuBDBlqkTtqJi\n" +
                    "iY8NTr5Jxig1bn5SP7u0yRAF9IoIOB6fvXss8vuexSUtrtLfSMOQ7NS3KVv39cqS\n" +
                    "+02juQFZEJqvwQSxyGbV9MK8/1NFvlthdKLe3QlpFU5541u4AP52LI7nq+hesqvF\n" +
                    "TMCH83+VqGBNhyfUzf+eHOJg2ecUc/bE0VrfD6g8CwBzk0Ap258+uIp/YgarXVDZ\n" +
                    "FAaU/oWfvyncbCQl0pmnBO6mFKF1dG04CwnhHtdtpx/p6OV4zcJQhY4YXnJiDD/3\n" +
                    "U2AOd0QBJ79gComsUQwsghVLRCS8QwIDAQABAoICAHBS3nVwY5Fore3+wT714wcs\n" +
                    "zd7p7j9wZvScFOVv+TgubwDFCSfRZ6fpog/g4jiSmAJudEy9pobXLkJQKeAFseHF\n" +
                    "+pxwI9FrcP7Vq71vJief8XT7ov4JXc4LDgF87c0D3P8Vp6fgvXZBsbW1YghyHlyF\n" +
                    "LfEWqmEyL4iJuzsIf8yYBPZb/AnAojXuSN54cuE9EauxEjR9ZKNLjU9LV82nJsNl\n" +
                    "BJuCzz3vQGFCTrO/eWlYkfs3XNGfvSKkFDksO7g621TXCqFtQrV1k0w2OZEcG44w\n" +
                    "NJ7Kr/x5RqWZ8Ay+jth3W16P1MQ2Bib/52hrIqU4BbNwqsp7xD18eRx8/EfNEDMt\n" +
                    "Is+2HBCIoi4ReegGqGPGIWBixwTwrQWYJGKaxo5aA9sWwVDYnr1Xcmesu24C2Mx1\n" +
                    "pvjHFwu1zRWOVoCX5eH+OCIMxtdwGExRdNdLs4jKVtXOgkg7V1t/pw3NN0n/EhtK\n" +
                    "ztLMtDdfg6RiokeGNX+5Lriq23Q3qEdsmSY6OKGqTrFKno0JJdeKRpZQtI/YStOx\n" +
                    "Xf55E9JWdhOgXK8wvDq4UuQlirdPLmKavh2cWqVNlheUKk95AnA2OMT4WHiO3SG9\n" +
                    "KQ6VR02SybNuIIVtOBuNol8j/GkmeXArYL7I6xttderDOSGOypstfxZse9fdWGUN\n" +
                    "i019Z98UCAcrMLNLQbGBAoIBAQD5IT0cd2Q6hgt03GEwHM3pbGySEdzfmtPQEOql\n" +
                    "LyJUf/PoPnQbVp8s0U1kErFGLQpHcDoeCrEzsb5FRQxSgQqtXXwQdmh49e1T0syw\n" +
                    "kHXKvGYmP5L4jKjvGWTtEdA0vbWNdkoLotbjrFL/2dwz5MTTejN7AfZcEsINyIHy\n" +
                    "r5vwq+Z+ohsM5ETJCDwooDe2Q19iHLYPz+vZT1q2Yi85ZyVODUxPidNy0EM2DOXB\n" +
                    "Jrx83SyrI24vXX16mwsU5aFD3C1fjbgdVsg7qvfzmJ95BKrMFO7W6aL/vU1I1s+D\n" +
                    "kHQlc/uAMBj7gBO/BDG3P14tKduTAJayBu+6mqwRYYKasWyDAoIBAQDvkWnKKQWy\n" +
                    "Z5clfSSNZ3rYx4E5zJwI+jzI/lugL5OICVef93i0TFFqE8Rs3W0+a42NVVUCT7B6\n" +
                    "KLqMB6EV8l7wUX6SYK65aQRYfqo/8wBgk2mdlNG0yCaiM3d12V96A+jPLuROQJ78\n" +
                    "7gt2pLUbRI4UL0QQWGR8ShiWKDZ0l/BKd8dcRAZG/e8N87tqGsePpPJ35tt5Cyrc\n" +
                    "YNsfvAIB1DvwhqzB8PuTlk9ES+JhR2R3ThCCgsYooeN+BWNa+AyytfXL7iD2+P6E\n" +
                    "p8ndcG2T10pW5U3+CxKJGNs+ZADJUR+q6fL5QVKQVw9tAV1lsMiaVWkkkIQE8zPW\n" +
                    "ElL0Usu8LeVBAoIBAAIYNHJT0qTnUZtW6v5Dj7uhrLwBqHC81YEPU1m2Lry1IrDW\n" +
                    "LZW5QbdXvyXTPmhPoqF9IQ/1mQ3NNcNRVEwxR4yzl4Jv7Grv7mHhCNWHMWZV9Snn\n" +
                    "KO4sCJOSDBYuKyj2W1toHjFA2jupzDzYcRCCoOECSwKCvraBdd41zFnk/hOk7lgs\n" +
                    "NrwCA04JYrAYwL8oi9nAUguJ/Pqjvyh/ZNQ/uMGSPQ0CJKP3ekkvDJN2JumJ1oFN\n" +
                    "1/RP/ekU+p/fWl6+sCb4gw7EFwThaBpFMzoKPwFeP6/Q4QzzY330SGRbx441Dm1C\n" +
                    "ekNLDV9ywpQ+1STeEDmJLLssccGI4V94CWgRi88CggEBALEvFFYfpwX/Fcv1VY57\n" +
                    "5WDllU8BZLpDEaf6cBv2dCAeAL/gUVbQRzq0age2cTDEbUoFvbza4YRIEUi1788C\n" +
                    "lnMZYwqyFSguYrds31Ay0qma/Xc99SfHDMPUWts/rRzaPVrWrKavpqcdDD9wrD4B\n" +
                    "oy+MkcOhDJjcWHjePwBWy7LYCgvUh+wDrcXc6VUdf1FfRrGlfdd1ifq6a/Z5n+Zo\n" +
                    "aPiJMsayC+9pMTErCsC2A7k1dIKCZDrzGQVAyO4kHYgjiiqM8baYC+5jBqsPlwvM\n" +
                    "JEBGkjt9RvU8/BKV9frvVAOdykXNqVn1+gO+CazHZma3Gu+fLvVWRpVishXgEq0Y\n" +
                    "KMECggEAd04hi1XVDHFtKqldJMTavvKVsN3AEjVbEDoMTbqNvMsLSnsBDbW7trDk\n" +
                    "lfmhJMwtTh8CNnb6fjMtZxDACQooKr/DvfB6Q9j4hAgsBvBXWcyv2TRjbGdOt9OK\n" +
                    "OXs67Kr8GczyhrNWkPvleEYVTL0ZNRM/0RGNcF5WJvgl2JsLQW2JgBzbiJo3O4Nu\n" +
                    "kntlw/c8kHlfKZvKFFVhkKal5YIs+5psoi/xZjFP4IH8MFCBOPiwvTwnjtM3SlAk\n" +
                    "+e10N3+oxMVWZK4PIHqmdlkEJDO2puxc3jo5y8c+YwAfEOucdNETk5mL3zJFabbD\n" +
                    "mbBAghK/z2lmPvOhNjaIE0pBCmyvbQ==\n" +
                    "-----END PRIVATE KEY-----\n";
            System.out.println("rsa获取私钥： " + privateKey);

// 公钥加密私钥解密
            byte[] rsaPublic =
                    RSAUtil.rsa(data, publicKey, RSAUtil.RSA_PUBLIC_ENCRYPT);
            System.out.println("rsa公钥加密： " + new String(rsaPublic));


            System.out.println("rsa私钥解密： " + new String(
                    RSAUtil.rsa(rsaPublic, privateKey, RSAUtil.RSA_PRIVATE_DECRYPT)));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }













        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        String appsflyerkey = mFirebaseRemoteConfig.getString("appsflyerkey");

        String url = mFirebaseRemoteConfig.getString("url");


        String theme = mFirebaseRemoteConfig.getString("theme");


        Log.d(TAG, "appsflyerkey: " + appsflyerkey);
        Log.d(TAG, "url: " + url);
        Log.d(TAG, "theme: " + theme);

        if (TextUtils.equals(theme, "themeA")) {
            startActivity(new Intent(SplashActivity.this, GuideViewPagerActivity.class));
        } else if (TextUtils.equals(theme, "themeB")) {
            startActivity(new Intent(SplashActivity.this, BWebMain.class));
        } else {
            startActivity(new Intent(SplashActivity.this, GuideViewPagerActivity.class));
        }


        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            boolean updated = task.getResult();
                            Log.d(TAG, "Config params updated: " + updated);
                            Toast.makeText(SplashActivity.this, "Fetch and activate succeeded",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(SplashActivity.this, "Fetch failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                        //displayWelcomeMessage();
                    }
                });


        mFirebaseRemoteConfig.addOnConfigUpdateListener(new ConfigUpdateListener() {
            @Override
            public void onUpdate(ConfigUpdate configUpdate) {
                Log.d(TAG, "===Updated keys: " + configUpdate.getUpdatedKeys());


                String appsflyerkey = mFirebaseRemoteConfig.getString("appsflyerkey");

                String url = mFirebaseRemoteConfig.getString("url");
                Log.d(TAG, "====appsflyerkey: " + appsflyerkey);
                Log.d(TAG, "====url: " + url);
                mFirebaseRemoteConfig.activate().addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        ///   displayWelcomeMessage();
                        Log.d(TAG, "task: " + task);
                    }
                });
            }

            @Override
            public void onError(FirebaseRemoteConfigException error) {
                Log.w(TAG, "Config update error with code: " + error.getCode(), error);
            }
        });


        //////////////
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                if (DemoApplication.isAd || true) {
//                    startActivity(new Intent(SplashActivity.this, BWebMain.class));
//                } else {
//                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
//
//                }
//                finish();
//            }
//        }, 3000);


    }
}