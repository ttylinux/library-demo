package com.library_demo.activity;


import com.library_demo.HttpConstants;
import com.library_demo.R;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class DemonstrateWebViewUsage extends Activity {


  public void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.webview_layout);

    WebView webView = (WebView) findViewById(R.id.web_view);
    WebSettings settings = webView.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setJavaScriptCanOpenWindowsAutomatically(true);
    settings.setUseWideViewPort(true);
    settings.setLoadWithOverviewMode(true);
    settings.setSupportZoom(true);
    settings.setBuiltInZoomControls(false);
    webView.loadUrl(HttpConstants.BaiduUrl);
  }

}
