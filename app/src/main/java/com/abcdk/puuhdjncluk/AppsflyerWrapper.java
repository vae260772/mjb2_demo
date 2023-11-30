package com.abcdk.puuhdjncluk;

import static com.abcdk.puuhdjncluk.Lihuaweb1.AF_DEV_KEY;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;

import java.util.HashMap;
import java.util.Map;

public class AppsflyerWrapper {

    private static AppsflyerWrapper _instance = null;

    public static AppsflyerWrapper getInstance() {
        if (_instance == null) {
            _instance = new AppsflyerWrapper();
        }
        return _instance;
    }

    ///private final String AF_DEV_KEY = "thp3BuYKypmZXSZAhjctpM";
    private final String LOG_TAG = "AppsflyerWrapper";
    private Context sContext;
    private String preEventname = "";

    public void initAppsflyer(Context context) {
        Log.d(LOG_TAG, "initAppsflyer");
        sContext = context;
        AppsFlyerLib.getInstance().init(AF_DEV_KEY, null, context);
        AppsFlyerLib.getInstance().setMinTimeBetweenSessions(0);

//        appsflyer.setDebugLog(true);
//        appsflyer.start(this);

        AppsFlyerLib.getInstance().start(context, AF_DEV_KEY, new AppsFlyerRequestListener() {
            @Override
            public void onSuccess() {
                Log.d(LOG_TAG, "Launch sent successfully, got 200 response code from server");
                AppsflyerWrapper.getInstance().logEventByApp("af_startapp", null);
            }

            @Override
            public void onError(int i, String s) {
                Log.d(LOG_TAG, "Launch failed to be sent:\n" +
                        "Error code: " + i + "\n"
                        + "Error description: " + s);
            }
        });
    }

    // 发送应用内事件
    // 已定义的事件类型在 AFInAppEventType 中可查看
    public void logEvent(String eventName, Map<String, Object> eventValues) {
//        Map<String, Object> eventValues = new HashMap<String, Object>();
        //eventValues.put(AFInAppEventParameterName.PRICE, 1234.56);
        //eventValues.put(AFInAppEventParameterName.CONTENT_ID,"1234567");
        //AFInAppEventType.LOGIN

        AppsFlyerLib.getInstance().logEvent(sContext,
                eventName, eventValues, new AppsFlyerRequestListener() {
                    @Override
                    public void onSuccess() {
                        Log.d(LOG_TAG, eventName + " Event sent successfully");
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.d(LOG_TAG, "Event failed to be sent:\n" +
                                "Error code: " + i + "\n"
                                + "Error description: " + s);
                    }
                });
    }

    public void logEvent(String eventName) {
        Map<String, Object> eventValues = new HashMap<String, Object>();

        AppsFlyerLib.getInstance().logEvent(sContext,
                eventName, eventValues, new AppsFlyerRequestListener() {
                    @Override
                    public void onSuccess() {
                        Log.d(LOG_TAG, eventName + " empty params Event sent successfully");
                    }

                    @Override
                    public void onError(int i, String s) {
                        Log.d(LOG_TAG, "empty params Event failed to be sent:\n" +
                                "Error code: " + i + "\n"
                                + "Error description: " + s);
                    }
                });
    }

//    H5端 var eventParams = {"af_country":"Kenya", "af_region": "bj", "af_currency": "ksh", "af_description": "just for local test", "af_purchase_currency":"kes", "af_custom": "login test"}
//  js
//      window.Appsflyer &&  window.Appsflyer.logEventByApp("af_login", JSON.stringify(eventParams))

    @JavascriptInterface
    public void logEventByApp(String eventName, String eventString) {

        if ((preEventname.equals("af_closeapp") && eventName.equals("af_closeapp")) || (eventName.equals("af_startapp") && preEventname.equals("af_startapp"))) {

            Log.d(LOG_TAG, "eventName " + eventName + " has send twice..");
            return;

        }

        Log.d(LOG_TAG, "logEventByApp " + eventName + " = " + eventString);
        if (null == eventString || eventString.isEmpty()) {
            this.logEvent(eventName);
        } else {

            Map<String, Object> eventValues = JSON.parseObject(eventString, Map.class);

            this.logEvent(eventName, eventValues);
        }

        preEventname = eventName;

    }

    @JavascriptInterface
    public String getAppsFlyerId() {
        String appsFlyerId = AppsFlyerLib.getInstance().getAppsFlyerUID(sContext);
        return appsFlyerId;
    }

}


//
//
//    implementation 'com.appsflyer:af-android-sdk:6.9.0'
//            implementation "com.android.installreferrer:installreferrer:2.2"