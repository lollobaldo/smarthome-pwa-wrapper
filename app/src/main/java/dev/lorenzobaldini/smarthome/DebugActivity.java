package dev.lorenzobaldini.smarthome;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class DebugActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.hideSystemUI(getWindow().getDecorView());

        WebView  myWebView = new WebView(this);
        setContentView(myWebView);
        myWebView.addJavascriptInterface(new WebAppInterface(this), "AndroidWrapper");
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.loadUrl("http://192.168.1.102:3000/");
    }
}
