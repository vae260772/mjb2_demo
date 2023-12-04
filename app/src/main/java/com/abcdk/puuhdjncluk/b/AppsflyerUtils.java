package com.abcdk.puuhdjncluk.b;

import static com.abcdk.puuhdjncluk.b.Gouchen_Application.myAppContext;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;

import java.util.HashMap;
import java.util.Map;

public class AppsflyerUtils {
    private final String LOG_TAG = "AppsflyerUtils";
    private static AppsflyerUtils _instance = null;

    public static AppsflyerUtils getInstance() {
        if (_instance == null) {
            _instance = new AppsflyerUtils();
        }
        return _instance;
    }

    public void initAppsflyer(String AF_DEV_KEY) {
        Log.d(LOG_TAG, "initAppsflyer");
        AppsFlyerLib.getInstance().init(AF_DEV_KEY, null, myAppContext);
        AppsFlyerLib.getInstance().setMinTimeBetweenSessions(0);
        AppsFlyerLib.getInstance().start(myAppContext, AF_DEV_KEY, new AppsFlyerRequestListener() {
            @Override
            public void onSuccess() {
                Log.d(LOG_TAG, "Launch sent successfully, got 200 response code from server");
                AppsflyerUtils.getInstance().logEventByApp("af_startapp", null);
            }

            @Override
            public void onError(int i, String s) {
                Log.d(LOG_TAG, "Launch failed to be sent:\n" +
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
        Log.d(LOG_TAG, "logEventByApp " + eventName + " = " + eventString);
        if (TextUtils.isEmpty(eventString)) {
            AppsFlyerLib.getInstance().logEvent(myAppContext,
                    eventName, new HashMap<>(), new AppsFlyerRequestListener() {
                        @Override
                        public void onSuccess() {
                            Log.d(LOG_TAG, eventName + " ,empty params Event sent successfully");
                        }

                        @Override
                        public void onError(int i, String s) {
                            Log.d(LOG_TAG, "empty params Event failed to be sent:\n" +
                                    "Error code: " + i + "\n"
                                    + "Error description: " + s);
                        }
                    });
        } else {
            Map eventValues = JSON.parseObject(eventString, Map.class);
            AppsFlyerLib.getInstance().logEvent(myAppContext,
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
    }

    @JavascriptInterface
    public String getAppsFlyerId() {
        String appsFlyerId = AppsFlyerLib.getInstance().getAppsFlyerUID(myAppContext);
        return appsFlyerId;
    }
}