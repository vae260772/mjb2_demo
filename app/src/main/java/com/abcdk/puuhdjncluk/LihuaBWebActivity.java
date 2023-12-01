package com.abcdk.puuhdjncluk;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//webview1
public class LihuaBWebActivity extends Activity {
    //b面链接
    public static String loadUrl = "";//https://brlfortune.com/?cid=444216
    public static String jsBridgeObjName = "";//apkClient
    private WebView webView;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setSetting();
        setContentView(webView);
        Log.d(TAG, "loadUrl=" + loadUrl);
        webView.loadUrl(loadUrl);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setSetting() {
        WebSettings setting = webView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setSupportMultipleWindows(true);
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        setting.setDomStorageEnabled(true);
        setting.setCacheMode(WebSettings.LOAD_DEFAULT);
        setting.setAllowContentAccess(true);
        setting.setDatabaseEnabled(true);
        setting.setGeolocationEnabled(true);
        setting.setUseWideViewPort(true);
        /// setting.setAppCacheEnabled(true);
        setting.setUserAgentString(setting.getUserAgentString().replaceAll("; wv", ""));
        // 视频播放需要使用
        setting.setMediaPlaybackRequiresUserGesture(false);
        setting.setSupportZoom(false);// 支持缩放


        //2
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                Log.d(TAG, "onProgressChanged newProgress = " + newProgress);

            }
        });
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "url=" + url);
                Uri uri = Uri.parse(url);
                String host = uri.getHost();
                /*****************三方马甲包需要实现此拦截，否则可能会出来【点击打开牌照后无法返回游戏】的状况***********/
                //马甲包打开牌照： 拦截后跳浏览器打开
                assert host != null;
                if (host.contains("validator.antillephone.com")) {
                    //牌照地址
                    openAppBrowser(url);
                    return true;
                }
                return false;
            }
        });
        webView.addJavascriptInterface(this, jsBridgeObjName);
    }

    String TAG = "web1";

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    /*******三方马甲包需要实现此接口***************/
    /*******打开浏览器跳转url**********************/
    @JavascriptInterface
    public void openAppBrowser(final String url) {
        Log.d(TAG, "openAppBrowser url = " + url);
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public static String AF_DEV_KEY = "";

/*******三方马甲包需要实现此接口************/
    /*******获取AppsFlyer的上报id，由后端上报【充值成功】【提现成功】**/
    @JavascriptInterface
    public String getSdkData() {
        String AppsFlyer_ID = AppsFlyerLib.getInstance().getAppsFlyerUID(getApplicationContext());
        JSONObject jsonData = JSON.parseObject("{}");
        jsonData.put(af_id, AppsFlyer_ID);
        jsonData.put(af_dev_key, AF_DEV_KEY);
        jsonData.put(af_bundleIdentifier, "com.abcdk.lihua8889");

        String result = JSON.toJSONString(jsonData);
        Log.d("本地test", "getSdkData: " + result);
        return result;
    }

    public static String af_id = "";
    public static String af_dev_key = "";
    public static String af_bundleIdentifier = "";
    public static String event_type = "";

    @JavascriptInterface
    public void appsFlyerEvent(String data) {
        Map<String, Object> hashMap = new HashMap<String, Object>();
        JSONObject jsonData = JSON.parseObject(data);
        Iterator it = jsonData.entrySet().iterator();
        String eventType = "";
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String key = entry.getKey().toString();
            Object value = entry.getValue();
            if (key.equals(event_type)) {
                eventType = value.toString();
            }
            hashMap.put(key, value);
        }
        if (!eventType.equals("")) {
            //appsflyer事件
            String finalEventType = eventType;
            AppsFlyerLib.getInstance().logEvent(getApplicationContext(), eventType, hashMap, new AppsFlyerRequestListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(getApplicationContext(), finalEventType + "上报完成", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(int i, @NonNull String s) {

                }
            });
        }
    }

}