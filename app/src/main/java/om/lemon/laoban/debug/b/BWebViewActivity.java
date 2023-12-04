package om.lemon.laoban.debug.b;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.attribution.AppsFlyerRequestListener;

import java.util.Map;

//webview1
public class BWebViewActivity extends AppCompatActivity {
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
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.addJavascriptInterface(new JsInterface(), jsBridgeObjName);
    }


    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    /**
     * 充值window.apkClient.appsFlyerEvent(JSON.stringify({"event_type": "af_purchase", "af_currency":"BRL", "af_revenue": item.param.money, "uid": localStorage.getItem('uid')}))
     * <p>
     * 注册window.apkClient.appsFlyerEvent(JSON.stringify({"event_type": "af_complete_registration", "uid": res.uid, "pid": getQueryString('pid')}))
     * <p>
     * 登录window.apkClient.appsFlyerEvent(JSON.stringify({"event_type": "af_login", "uid": res.data.uid}))
     * <p>
     * 首冲
     * af_first_purchase
     * window.apkClient.appsFlyerEvent(JSON.stringify({"event_type": "af_first_purchase", "af_currency":"BRL", "af_revenue": item.param.money, "uid": localStorage.getItem('uid')}))
     */
    private class JsInterface {
        @JavascriptInterface
        public void appsFlyerEvent(String data) {
            Log.d("TAG", "1 data = " + data);
            Map maps = (Map) JSON.parse(data);
            String eventName = (String) maps.get("event_type");
            AppsFlyerLib.getInstance().logEvent(getApplicationContext(), eventName, maps, new AppsFlyerRequestListener() {
                @Override
                public void onSuccess() {
                    Log.d("TAG", "onSuccess data = " + data);
                }

                @Override
                public void onError(int i, @NonNull String s) {
                    Log.d("TAG", "onError s = " + s);

                }
            });
            Log.d("TAG", "2 eventName=" + eventName + ",maps=" + maps);
            Toast.makeText(getApplicationContext(), eventName, Toast.LENGTH_SHORT).show();
        }
    }
}