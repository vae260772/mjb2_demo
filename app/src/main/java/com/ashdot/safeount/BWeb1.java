package com.ashdot.safeount;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.appsflyer.AFInAppEventParameterName;
import com.appsflyer.AppsFlyerLib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//webview1
public class BWeb1 extends Activity {
    //js代码
    private static String windowWgPackage = "javascript:window.WgPackage = {name:'";
    private static String version = "', version:'";
    private static String closeGame = "javascript:window.closeGame()";

    //b面链接
    public static String loadUrl = "";//https://brlfortune.com/?cid=444216
    public static String jsBridgeObjName = "";//apkClient


    //事件埋点动态
    public static String openWindow = "";
    public static String firstrecharge = "";
    public static String recharge = "";
    public static String amount = "";
    public static String currency = "";
    public static String withdrawOrderSuccess = "";


    /////////////////////
    private static final String TAG = "BWeb1";
    private WebView webView;


    private ValueCallback<Uri> mUploadCallBack;
    private ValueCallback<Uri[]> mUploadCallBackAboveL;
    private final int REQUEST_CODE_FILE_CHOOSER = 888;

    Context context;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        webView = new WebView(this);
        setSetting();
        setContentView(webView);
        webView.loadUrl(loadUrl);
    }


    public String getAppVersionName(Context context) {
        String appVersionName = "";
        try {
            PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0);
            appVersionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, e.getMessage());
        }
        return appVersionName;
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
        try {
            Class<?> clazz = setting.getClass();
            Method method = clazz.getMethod("setAllowUniversalAccessFromFileURLs", boolean.class);
            method.invoke(setting, true);
        } catch (IllegalArgumentException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent intent = new Intent();
                // 设置意图动作为打开浏览器
                intent.setAction(Intent.ACTION_VIEW);
                // 声明一个Uri
                Uri uri = Uri.parse(url);
                intent.setData(uri);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            // For Android  >= 5.0
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                BWeb1.this.mUploadCallBackAboveL = filePathCallback;
                openFileChooseProcess();
                return true;
            }
        });


        //2
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (TextUtils.equals(failingUrl, loadUrl)) {
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    });
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String WgPackage = windowWgPackage + getPackageName() + version + getAppVersionName(BWeb1.this) + "'}";
                webView.evaluateJavascript(WgPackage, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {

                    }
                });
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                String WgPackage = windowWgPackage + getPackageName() + version + getAppVersionName(BWeb1.this) + "'}";
                webView.evaluateJavascript(WgPackage, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {

                    }
                });
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d(TAG, "url=" + url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        webView.addJavascriptInterface(new JsInterface(), jsBridgeObjName);
    }

    private void openFileChooseProcess() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Select Picture"), REQUEST_CODE_FILE_CHOOSER);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
    private class JsInterface {
        @JavascriptInterface
        public void postMessage(String name, String data) {
            // {"event_type":"af_complete_registration","uid":"35283135","pid":"1121"
            try {
                Map<String, Object> eventValue = new HashMap<String, Object>();
                if (openWindow.equals(name)) {
                    Intent intent = new Intent(context, BWeb2.class);
                    intent.putExtra("url", data);
                    BWeb1.this.startActivityForResult(intent, 1);
                } else if (firstrecharge.equals(name) || recharge.equals(name)) {
                    Map maps = (Map) JSON.parse(data);
                    for (Object map : maps.entrySet()) {
                        String key = ((Map.Entry) map).getKey().toString();
                        if (amount.equals(key)) {
                            eventValue.put(AFInAppEventParameterName.REVENUE, ((Map.Entry) map).getValue());
                        } else if (currency.equals(key)) {
                            eventValue.put(AFInAppEventParameterName.CURRENCY, ((Map.Entry) map).getValue());
                        }
                    }
                } else if (withdrawOrderSuccess.equals(name)) {
                    // 提现成功
                    Map maps = (Map) JSON.parse(data);
                    for (Object map : maps.entrySet()) {
                        String key = ((Map.Entry) map).getKey().toString();
                        if (amount.equals(key)) {
                            float revenue = 0;
                            String value = ((Map.Entry) map).getValue().toString();
                            if (!TextUtils.isEmpty(value)) {
                                revenue = Float.parseFloat(value);
                                revenue = -revenue;
                            }
                            eventValue.put(AFInAppEventParameterName.REVENUE, revenue);

                        } else if (currency.equals(key)) {
                            eventValue.put(AFInAppEventParameterName.CURRENCY, ((Map.Entry) map).getValue());
                        }
                    }
                } else {
                    eventValue.put(name, data);
                }
                AppsFlyerLib.getInstance().logEvent(context, name, eventValue);
                Log.d(TAG, "name=" + name + ",data=" + data + ",eventValue=" + eventValue);
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "---------requestCode = " + requestCode + "      resultCode = " + resultCode);
        if (requestCode == this.REQUEST_CODE_FILE_CHOOSER) {
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (result != null) {
                if (mUploadCallBackAboveL != null) {
                    mUploadCallBackAboveL.onReceiveValue(WebChromeClient.FileChooserParams.parseResult(resultCode, data));
                    mUploadCallBackAboveL = null;
                    return;
                }
            }
            clearUploadMessage();
        } else if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                if (webView == null) {
                    return;
                }
                Log.e(TAG, "---------下分成功-----");
                /**
                 * 下分回调
                 */
                webView.evaluateJavascript(closeGame, new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {

                    }
                });
            }
        }
    }

    private void clearUploadMessage() {
        if (mUploadCallBackAboveL != null) {
            mUploadCallBackAboveL.onReceiveValue(null);
            mUploadCallBackAboveL = null;
        }
        if (mUploadCallBack != null) {
            mUploadCallBack.onReceiveValue(null);
            mUploadCallBack = null;
        }
    }

}