package com.billspillstore.android;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.billspillstore.android.Fragments_category.ClearTrip;
import com.billspillstore.android.Fragments_category.Goibibo;
import com.billspillstore.android.Fragments_category.Makemytrip;
import com.billspillstore.android.Fragments_category.MobileAdapter;
import com.billspillstore.android.Fragments_category.Yatra;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class TabbedFlightActivity extends AppCompatActivity {

    SharedPreferences preferences;
    String token,value="TravelStay";
    public static String Tokenfile="File";

FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_flight);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mFirebaseAnalytics= FirebaseAnalytics.getInstance(TabbedFlightActivity.this);
        Bundle params=new Bundle();
        params.putInt("ActivityOpenFlig",14);
        String status="TravelStay_Activity_Open";
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
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.fiftyflight);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        ViewPager pager= (ViewPager) findViewById(R.id.container);

        if(!isOnline()){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("No Network Connectivity");
            builder.setMessage("Check your internet connection or try again later");
            builder.setCancelable(true);
            builder.show();
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }

        //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);
        setupViewpager(pager);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed_flight, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            Intent in=new Intent(this,SearchClothing.class);
            startActivity(in);
            return true;
        }

        if(id==R.id.menu_offer){
            Intent j=new Intent(this,MenuOffers.class);
            startActivity(j);
        }
        if(id==R.id.menu_products){
            Intent k=new Intent(this,MenuProducts.class);
            startActivity(k);
        }
        if(id==R.id.menu_feedback){
            Intent m=new Intent(this,MenuFeedback.class);
            startActivity(m);
        }
        if(id==R.id.menu_rateus){


            try {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("market://details?id=com.billspillstore.android"));
                startActivity(viewIntent);
            }catch(Exception e) {
                Toast.makeText(getApplicationContext(),"Unable to Connect Try Again...",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }
        if(id==R.id.menu_refer){
            Intent p=new Intent(this,PromoShareActivity.class);
            startActivity(p);
        }
        if(id==R.id.menu_support){
            Intent p=new Intent(this,SupportActivity.class);
            startActivity(p);
        }
        if(id==R.id.menu_about){
            Intent p=new Intent(this,MenuAboutus.class);
            startActivity(p);
        }
        if(id==R.id.menu_blog){
            Intent loadurl =  new Intent(this,BlogWebActivity.class);
            startActivity(loadurl);
        }


        return super.onOptionsItemSelected(item);
    }
    public void setupViewpager(ViewPager viewPager){
        MobileAdapter adapter=new MobileAdapter(getSupportFragmentManager());
        adapter.addFragment(new Goibibo(),"Goibibo");
        adapter.addFragment(new Makemytrip(),"MMT");
        adapter.addFragment(new Yatra(),"Yatra");
        adapter.addFragment(new ClearTrip(),"ClearTrip");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);


    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }
    public boolean isOnline(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null;
    }
}
