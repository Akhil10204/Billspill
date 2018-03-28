package com.billspillstore.android;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class BlogWebActivity extends AppCompatActivity {
WebView webView;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_web);
        webView =(WebView)findViewById(R.id.web);
        webView.setWebViewClient(new myWebClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("http://bit.ly/2sbE8Fx");
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                    webView.goBack();
                    return true;
                }
                return false;
            }
        });
    }

    public   class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressDialog = new ProgressDialog(BlogWebActivity.this);
            progressDialog.setTitle("Loading...");
            progressDialog.show();
            progressDialog.setCancelable(true);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if(progressDialog.isShowing()){
            progressDialog.dismiss();}
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
