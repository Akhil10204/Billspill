package com.billspillstore.android;

import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.billspillstore.android.Fragments_category.Amazon;
import com.billspillstore.android.Fragments_category.Flipkart;
import com.billspillstore.android.Fragments_category.MobileAdapter;
import com.billspillstore.android.Fragments_category.Paytm;
import com.billspillstore.android.Fragments_category.Snapdeal;

import java.util.List;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class TabbedMainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    String TAG="MY TAG::::";
    public  boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    private boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (String activeProcess : processInfo.pkgList) {
                    if (activeProcess.equals(context.getPackageName())) {
                        isInBackground = false;
                    }
                }
            }
        }

        return isInBackground;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected( MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_clothing:
                    Intent intent=new Intent(TabbedMainActivity.this,ClothingTabbedActivity.class);
                    startActivity(intent);
                    return false;
                case R.id.navigation_flight:
                    Intent intents=new Intent(TabbedMainActivity.this,TabbedFlightActivity.class);
                    startActivity(intents);
                    return false;
                case R.id.navigation_global:
                    Intent in=new Intent(TabbedMainActivity.this,GlobalTabbed.class);
                    startActivity(in);
                    return false;
                case R.id.navigation_others:
                    Intent inti=new Intent(TabbedMainActivity.this,OtherTabbedActivity.class);
                    startActivity(inti);
                    return false;
            }
            return false;
        }

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT<21){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setTitle("Incompatible Device");
            builder.setMessage("Your device is incompatible with this application");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    TabbedMainActivity.this.finish();
                    dialog.dismiss();
                }
            });
            builder.create();
            builder.show();
        }
        else {
            setContentView(R.layout.activity_tabbed_main);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.long_appicon);
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            ViewPager pager = (ViewPager) findViewById(R.id.container);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(pager);
            setupViewpager(pager);
            if (!isOnline()) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

            String type = SearchActivity.code;
            if (type.equals("1")) {

                Intent i = this.getIntent();

                String input = i.getExtras().getString("INPUT");
                if (input != null) {
                    Amazon amazon = new Amazon();
                    amazon.getInput(input);
                    Flipkart flipkart = new Flipkart();
                    flipkart.getInput(input);
                    Paytm paytm = new Paytm();
                    paytm.getInput(input);
                    Snapdeal snapdeal = new Snapdeal();
                    snapdeal.getInput(input);

                }
                //Toast.makeText(getApplicationContext(),input,Toast.LENGTH_LONG).show();
            }


        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed_main, menu);
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
            Intent in=new Intent(this,SearchActivity.class);
            startActivity(in);
            return true;
        }
        if (id == R.id.setstore) {
            Intent inte=new Intent(this,SetStoreActivity.class);
            startActivity(inte);
            return true;
        }
        if(id==R.id.menu_support){
            Intent p=new Intent(this,SupportActivity.class);
            startActivity(p);
        }
        if(id==R.id.share){
            Intent p=new Intent(this,PromoShareActivity.class);
            startActivity(p);
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
        adapter.addFragment(new Amazon(),"Amazon");
        adapter.addFragment(new Flipkart(),"Flipkart");
        adapter.addFragment(new Snapdeal(),"Snapdeal");
        adapter.addFragment(new Paytm(),"Paytm Mall");
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }





    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        if(isMyServiceRunning(HeaderService.class)&&isAppIsInBackground(TabbedMainActivity.this)){
            this.finish();
        }
        super.onPause();
    }

    private boolean isAccessibilitySettingsOn(Context mContext) {
        int accessibilityEnabled = 0;
        final String service = getPackageName() + "/" + MyAccessibilityService.class.getCanonicalName();
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    mContext.getApplicationContext().getContentResolver(),
                    android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
            Log.v(TAG, "accessibilityEnabled = " + accessibilityEnabled);
        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "Error finding setting, default accessibility to not found: "
                    + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            Log.v(TAG, "***ACCESSIBILITY IS ENABLED*** -----------------");
            String settingValue = Settings.Secure.getString(
                    mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();

                    Log.v(TAG, "-------------- > accessibilityService :: " + accessibilityService + " " + service);
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        Log.v(TAG, "We've found the correct setting - accessibility is switched on!");
                        return true;
                    }
                }
            }
        } else {
            Log.v(TAG, "***ACCESSIBILITY IS DISABLED***");
        }

        return false;
    }
    public boolean isOnline(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null;
    }



}
