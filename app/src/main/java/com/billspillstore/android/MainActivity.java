package com.billspillstore.android;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.billspillstore.android.m_MySQL.GridDownloader;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener
         {
             private static final String LOGTAG ="Service providing log" ;
             private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected( MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_flight:

                   return true;
            }
            return false;
        }

    };

    final static String urladdress = "http://billspill.com/homepage.php";
             Context context;



             @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);

        /*Intent bat=new Intent(android.provider.Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
        startActivityForResult(bat,0);*/


    ///////////////////////////////////////////////////
            Intent inten = new Intent(android.provider.Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivityForResult(inten, 0);





        String manufacturer = "xiaomi";
        if(manufacturer.equalsIgnoreCase(android.os.Build.MANUFACTURER)) {
            //this will open auto start screen where user can enable permission for your app
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            startActivity(intent);
        }

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);

        } else {
            //initializeView();
        }*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.nav_mobile){


        }
        if(id==R.id.nav_home_appliances){

        }
        if(id==R.id.nav_camera){

        }
        if(id==R.id.nav_sports){

        }
        if(id==R.id.nav_laptop){

        }
        if(id==R.id.nav_television){

        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }




          /*   public boolean isAccessibilityEnabled(){
                 int accessibilityEnabled = 0;
                 final String LIGHTFLOW_ACCESSIBILITY_SERVICE = "com.example.test/com.example.text.accessibilityService";
                 boolean accessibilityFound = false;
                 try {
                     accessibilityEnabled = Settings.Secure.getInt(this.getContentResolver(),android.provider.Settings.Secure.ACCESSIBILITY_ENABLED);
                     Log.d(LOGTAG, "ACCESSIBILITY: " + accessibilityEnabled);
                 } catch (Settings.SettingNotFoundException e) {
                     Log.d(LOGTAG, "Error finding setting, default accessibility to not found: " + e.getMessage());
                 }

                 TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

                 if (accessibilityEnabled==1){
                     Log.d(LOGTAG, "***ACCESSIBILIY IS ENABLED***: ");


                     String settingValue = Settings.Secure.getString(getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
                     Log.d(LOGTAG, "Setting: " + settingValue);
                     if (settingValue != null) {
                         TextUtils.SimpleStringSplitter splitter = mStringColonSplitter;
                         splitter.setString(settingValue);
                         while (splitter.hasNext()) {
                             String accessabilityService = splitter.next();
                             Log.d(LOGTAG, "Setting: " + accessabilityService);
                             if (accessabilityService.equalsIgnoreCase(com.billspillstore.android.MyAccessibilityService.ACCESSIBILITY_SERVICE)){
                                 Log.d(LOGTAG, "We've found the correct setting - accessibility is switched on!");
                                 return true;
                             }
                         }
                     }

                     Log.d(LOGTAG, "***END***");
                 }
                 else{
                     Log.d(LOGTAG, "***ACCESSIBILIY IS DISABLED***");
                 }
                 return accessibilityFound;
             }*/

             public boolean isOnline(){
                 ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                 NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
                 return networkInfo != null;
             }

    @Override
    protected void onStart() {

        if(isOnline()) {

            final GridView view = (GridView) findViewById(R.id.maingridview);

            new GridDownloader(MainActivity.this, view, urladdress).execute();
        }

        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.close_icon);
            builder.setTitle("No network Connectivity");
            builder.setMessage("Check your internet connection and try again later");
            builder.show();
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();

                }
            });

        }

        super.onStart();
}

             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

             }


         }
