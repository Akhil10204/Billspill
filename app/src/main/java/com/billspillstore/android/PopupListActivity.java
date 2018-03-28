package com.billspillstore.android;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.billspillstore.android.m_MySQL.CompareDownloader;
import com.google.firebase.analytics.FirebaseAnalytics;

public class PopupListActivity extends Activity {

    public static boolean isviewexist=false;
    public static boolean compareactivity=false;
    public static String titlepage="Variants found";
    FirebaseAnalytics mFirebaseAnalytics;
    CompareDownloader compareDownloader;

    final static String urladdress = "http://billspill.com/appspecificsearch.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_list);

        mFirebaseAnalytics= FirebaseAnalytics.getInstance(PopupListActivity.this);
        Bundle params=new Bundle();
        params.putInt("ActivityOpenPopu",20);
        String status="PopUpList_Activity_Open";
        Log.d("Status","user_opened_activity");
        mFirebaseAnalytics.logEvent(status,params);


        TextView tv=(TextView) findViewById(R.id.titletext);
        tv.setText(titlepage);
        isviewexist=true;
        ListView lv = (ListView)findViewById(R.id.listview);
        isviewexist=true;
        String Nam=MyAccessibilityService.findproduct;
        String company=MyAccessibilityService.appname;
        if(!(company.equals("Flipkart")|| company.equals("Ebay")||company.equals("Shopclues")||company.equals("Snapdeal"))){
            Intent dialogIntent = new Intent(this, CustomizedPopupActivity.class);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(dialogIntent);
            this.finish();
        }
        else{
           compareDownloader= new CompareDownloader(this, lv, urladdress);
            compareDownloader.execute(Nam,company);
        }
    }

    @Override
    protected void onStop() {
        isviewexist=false;
        super.onStop();

    }

    @Override
    protected void onStart() {
        if (isMyServiceRunning(HeaderService.class)) {
            Intent i = new Intent(this, HeaderService.class);
            this.stopService(i);}
     // Toast.makeText(getApplicationContext(),"App :"+Nam,Toast.LENGTH_LONG).show();
       // String app=MyAccessibilityService.store();
       // ComparePrice comparePrice=new ComparePrice();
       // ArrayList<ComparePrice> sd=new ArrayList<>();
      //  sd.add(comparePrice);
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        if (!isMyServiceRunning(HeaderService.class)) {
            Intent i = new Intent(this, HeaderService.class);
            this.startService(i);
        }
        this.finish();
        isviewexist=false;

    }

    @Override
    protected void onPause() {
        if (!isMyServiceRunning(HeaderService.class)) {
            Intent i = new Intent(this, HeaderService.class);
            this.startService(i);
        }
        if(!compareDownloader.isCancelled()){
        compareDownloader.cancel(true);}
        isviewexist=false;
        this.finish();
        super.onPause();
    }

    public boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        isviewexist=false;
        super.onDestroy();
    }
}
