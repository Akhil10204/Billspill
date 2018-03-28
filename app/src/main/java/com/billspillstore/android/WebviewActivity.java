package com.billspillstore.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class WebviewActivity extends Activity {

    WebView web;
FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        mFirebaseAnalytics= FirebaseAnalytics.getInstance(WebviewActivity.this);
        Bundle params=new Bundle();
        params.putInt("ActivityOpenWebv",18);
        String status="ProductPage_Activity_Open";
        Log.d("Status","user_opened_activity");
        mFirebaseAnalytics.logEvent(status,params);

        Intent i = this.getIntent();
        String url = i.getExtras().getString("URL");
        web =(WebView)findViewById(R.id.webview);
        web.setWebViewClient(new myWebClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setAllowFileAccess(true);
        web.getSettings().setAllowContentAccess(true);
        web.getSettings().setDatabaseEnabled(true);
        web.getSettings().setDomStorageEnabled(true);
        web.loadUrl(url);
    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub

            view.loadUrl(url);
            return true;

        }
    }

    // To handle &quot;Back&quot; key press event for WebView to go back to previous screen.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
            web.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
