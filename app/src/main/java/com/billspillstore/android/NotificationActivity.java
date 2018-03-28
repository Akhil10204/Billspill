package com.billspillstore.android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class NotificationActivity extends AppCompatActivity {

    String url="";
    WebView web;
    public static String Tokenfile="File";
    SharedPreferences preferences;
    String token,value="WebLink";
    private FirebaseAnalytics mFirebaseAnalytics;
    SwipeRefreshLayout mySwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        preferences=getSharedPreferences(Tokenfile,0);
        token=preferences.getString("MyToken","updated_token");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("Token",token).add("Interest",value).build();
        Request request = new Request.Builder().url("http://billspill.com/interested_token.php").post(body).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mFirebaseAnalytics=FirebaseAnalytics.getInstance(NotificationActivity.this);
        Bundle params=new Bundle();
        params.putInt("ActivityOpenNoti",2);
        String status="Notification_Activity_Open";
        Log.d("Status","user_opened_activity");
        mFirebaseAnalytics.logEvent(status,params);
        mySwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeContainer);
        web=(WebView)findViewById(R.id.notification_webview);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        web.reload();
                    }
                }
        );
        if(this.getIntent()!=null){
            Intent i = this.getIntent();
            url = i.getExtras().getString("URL");
        }

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
            mySwipeRefreshLayout.setRefreshing(true);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mySwipeRefreshLayout.setRefreshing(false);
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
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