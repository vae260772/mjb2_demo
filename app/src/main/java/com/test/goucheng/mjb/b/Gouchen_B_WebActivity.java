package com.test.goucheng.mjb.b;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

//webview1
public class Gouchen_B_WebActivity extends Activity {
    //b面链接
    public static String loadUrl = "";//https://brlfortune.com/?cid=444216
    public static String jsBridgeObjName = "";//apkClient
    private WebView webView;
    private static String TAG = "Gouchen_B_WebActivity";

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
        setting.setUserAgentString(setting.getUserAgentString() + "app/bbggame");

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
                return false;
            }
        });
        webView.addJavascriptInterface(AppsflyerUtils.getInstance(), jsBridgeObjName);
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}