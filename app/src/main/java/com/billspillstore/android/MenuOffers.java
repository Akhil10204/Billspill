package com.billspillstore.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.billspillstore.android.m_MySQL.OfferDownloader;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class MenuOffers extends AppCompatActivity {
    final static String urls= "http://billspill.com/topoffer.php";

    SharedPreferences preferences;
    String token,value="TopOffers";
    public static String Tokenfile="File";
    private FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_offers);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mFirebaseAnalytics=FirebaseAnalytics.getInstance(MenuOffers.this);
        Bundle params=new Bundle();
        params.putInt("ActivityOpenOffe",3);
        String status="TopOffers_Activity_Open";
        Log.d("Status","user_opened_activity");
        mFirebaseAnalytics.logEvent(status,params);

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
        final ListView listView=(ListView)findViewById(R.id.menu_offerlist);
        new OfferDownloader(this,listView,urls).execute();
    }
}
