package com.billspillstore.android;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import com.billspillstore.android.m_MySQL.ProductGridAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MenuProducts extends AppCompatActivity {

    SharedPreferences preferences;
    String token,value="TopProducts";
    public static String Tokenfile="File";
    int image[] ={R.drawable.mobile,R.drawable.laptop,R.drawable.speakers,R.drawable.television,R.drawable.pendrive,R.drawable.mouse,R.drawable.tablet,R.drawable.power_bank,R.drawable.trimmer,R.drawable.headphone,R.drawable.fridge,R.drawable.monitor,R.drawable.hard_disk,R.drawable.water_purifiers,R.drawable.camera};
    String name[]={"Mobile","Laptop","Speaker","Television","Pendrive","Mouse","Tablet","PowerBank","Trimmer","Headphone","Refrigerator","Monitor","Hard Drive","WaterPurifier","Camera"};
   FirebaseAnalytics mFirebaseAnalytics;
    String sending[]={"Mobiles","Laptops","Speakers","Televisions","Storage","Laptop Accessories","Tablets","Mobile Accessories","Personal Care Appliances","Headphones","Home Appliances","Monitors","Hard Disk","water purifier","Cameras"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_products);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mFirebaseAnalytics= FirebaseAnalytics.getInstance(MenuProducts.this);
        Bundle params=new Bundle();
        params.putInt("ActivityOpenProd",7);
        String status="TopProducts_Activity_Open";
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
        GridView gridView=(GridView)findViewById(R.id.menu_gridview);
        ProductGridAdapter adapter=new ProductGridAdapter(image,name,sending,this);
        gridView.setAdapter(adapter);
    }
}
