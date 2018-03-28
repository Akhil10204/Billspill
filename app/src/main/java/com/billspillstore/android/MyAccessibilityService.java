package com.billspillstore.android;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import static android.content.ContentValues.TAG;


/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class MyAccessibilityService extends AccessibilityService {

    public static String AccessibilityPreference="AccessibilityPreference";
    public static String isServiceBinded="isServiceBinded";
    public static Context context;


    @Override
    public void onServiceConnected() {
        Log.e(TAG, "ACC::onServiceConnected:************************* ");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        ResolveInfo defaultLauncher = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        String pack = defaultLauncher.activityInfo.packageName;
        getServiceInfo().packageNames = new String[]
                {"net.one97.paytm,in.amazon.mShop.android.shopping","com.flipkart.android","com.snapdeal.main","com.shopclues","com.billspillstore.android","com.ebay.mobile",pack};
        SharedPreferences p=getSharedPreferences(AccessibilityPreference,Context.MODE_PRIVATE);
        SharedPreferences.Editor e=p.edit();
        e.putBoolean(isServiceBinded,true);
        e.apply();

    }
    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("onUnbind","onUnbind Called***************************************************");


        SharedPreferences p=getSharedPreferences(AccessibilityPreference,Context.MODE_PRIVATE);
        SharedPreferences.Editor e=p.edit();
        e.putBoolean(isServiceBinded,false);
        e.apply();
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.e("onRebind","onRebind Called***************************************************");
        SharedPreferences p=getSharedPreferences(AccessibilityPreference,Context.MODE_PRIVATE);
        SharedPreferences.Editor e=p.edit();
        e.putBoolean(isServiceBinded,true);
        e.apply();
        super.onRebind(intent);
    }
    public static boolean isServiceBinded(SharedPreferences preferences){
        return preferences.getBoolean(isServiceBinded,true);
    }

    String flag="";
     public static String findproduct="";
    public static String appname="";

    public  boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

        AccessibilityNodeInfo source = event.getSource();



        if(source==null){

          //  Log.d("onAccessibilityEvent", "source was null for: " + event);
        }
        if(source!=null) {
            source.refresh();
            if (source.getViewIdResourceName() != null && !source.getPackageName().equals("com.billspillstore.android")) {

                if (source.getViewIdResourceName().equals("descriptionSeeMore")) {

                    if (!isMyServiceRunning(HeaderService.class)) {
                        Intent i = new Intent(this, HeaderService.class);
                        this.startService(i);
                        findproduct="";
                        appname="";
                    }
                }
                source.refresh();
                //Flipkart Start
                if(source.getViewIdResourceName()!=null && source.getPackageName()!=null) {
                    if (source.getViewIdResourceName().equals("com.flipkart.android:id/product_page_recyclerview")) {
                        if (flag.equals("") || !flag.equals(source.getViewIdResourceName())) {
                            flag = source.getViewIdResourceName();
                            findproduct = "";
                            appname = "";
                            if (source.getChildCount() > 0) {
                                if (source.getChild(0).getChildCount() > 2)
                                    for (int i = 3; i <= source.getChild(0).getChildCount(); i++) {
                                        if (source.getChild(0).getChild(i).getChild(0) != null) {
                                            AccessibilityNodeInfo current = source.getChild(0).getChild(i).getChild(0);
                                            if (current.getClassName().equals("android.widget.TextView")) {
                                                if (current.getText().length() > 15) {
                                                    Log.d(" Product Name: ", current.getText().toString());
                                                    findproduct = current.getText().toString();
                                                    appname = "Flipkart";

                                                    break;
                                                }
                                            }
                                        }
                                    }
                            }
                            if (!isMyServiceRunning(HeaderService.class)) {
                                Intent i = new Intent(this, HeaderService.class);
                                this.startService(i);
                            }
                        }
                    } else if (source.getViewIdResourceName().equals("com.snapdeal.main:id/pdp_recycler_view")) {

                        if (flag.equals("") || !flag.equals(source.getViewIdResourceName())) {
                            flag = source.getViewIdResourceName();
                            findproduct = "";
                            appname = "";
                            if (source.getChildCount() > 1) {
                                AccessibilityNodeInfo node = source.findAccessibilityNodeInfosByViewId("com.snapdeal.main:id/ptitleView").get(0);
                                Log.d(" Production Name: ", node.toString());
                                findproduct = node.getText().toString();
                                appname = "Snapdeal";
                            }
                            if (!isMyServiceRunning(HeaderService.class)) {
                                Intent i = new Intent(this, HeaderService.class);
                                this.startService(i);
                            }
                        }
                    } else if (source.getViewIdResourceName().equals("com.shopclues:id/tv_product_name") && source.getParent().getClassName().toString().equals("android.widget.FrameLayout")) {
                        if (flag.equals("") || !flag.equals(source.getViewIdResourceName())) {
                            flag = source.getViewIdResourceName();
                            findproduct = "";
                            appname = "";
                            if (source.getClassName().toString().equals("android.widget.TextView")) {
                                Log.d(" Product Name:::::", source.getText().toString());
                                findproduct = source.getText().toString();
                                appname = "Shopclues";
                            }

                            if (!isMyServiceRunning(HeaderService.class)) {
                                Intent i = new Intent(this, HeaderService.class);
                                this.startService(i);
                            }
                        }
                    } else if (source.getViewIdResourceName().equals("com.ebay.mobile:id/textview_item_name")) {
                        if (flag.equals("") || !flag.equals(source.getViewIdResourceName())) {
                            flag = source.getViewIdResourceName();
                            findproduct = "";
                            appname = "";
                            if (source.getClassName().equals("android.widget.TextView")) {
                                Log.d(" Product Name", source.getText().toString());
                                findproduct = source.getText().toString();
                                appname = "Ebay";
                            }
                            if (!isMyServiceRunning(HeaderService.class)) {
                                Intent i = new Intent(this, HeaderService.class);
                                this.startService(i);
                            }
                        }
                    } else if (source.getViewIdResourceName().equals("net.one97.paytm:id/product_name")) {
                        if (!isMyServiceRunning(HeaderService.class)) {
                            Intent i = new Intent(this, HeaderService.class);
                            this.startService(i);
                        }
                    }
                }
                source.refresh();
                if(source.getViewIdResourceName()!=null) {
                    if (source.getViewIdResourceName().equals("com.flipkart.android:id/home_recycler_view") || source.getViewIdResourceName().equals("com.flipkart.android:id/title_action_bar") || source.getViewIdResourceName().equals("com.snapdeal.main:id/productDisplayPrice") || source.getViewIdResourceName().equals("com.shopclues:id/mDrawerLayout") || source.getViewIdResourceName().equals("com.shopclues:id/rv_list") || source.getViewIdResourceName().equals("com.ebay.mobile:id/recycler_view_main")) {
                        flag = "";
                        if (isMyServiceRunning(HeaderService.class)) {
                            Intent i = new Intent(this, HeaderService.class);
                            this.stopService(i);
                        }
                    }
                }

                    source.refresh();
                if(source.getPackageName()!=null) {
                    if (source.getPackageName() != null)
                        if (!(source.getPackageName().toString().equals("in.amazon.mShop.android.shopping") || source.getPackageName().toString().equals("com.flipkart.android") || source.getPackageName().toString().equals("com.snapdeal.main") || source.getPackageName().toString().equals("com.shopclues") || source.getPackageName().toString().equals("com.ebay.mobile") || source.getPackageName().toString().equals("net.one97.paytm"))) {
                            flag = "";
                            if (isMyServiceRunning(HeaderService.class)) {
                                Intent i = new Intent(this, HeaderService.class);
                                this.stopService(i);
                            }
                        }
                }
                source.refresh();
                if(source.getPackageName().equals("com.billspillstore.android")){
                    if (!isMyServiceRunning(HeaderService.class)) {
                        Intent i = new Intent(this, HeaderService.class);
                        this.startService(i);
                    }
                }
                //Flipkart end
                //Ebay end
            /* else {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                ResolveInfo defaultLauncher = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
                String pack = defaultLauncher.activityInfo.packageName;
                if (pack != null)
                    if (source.getPackageName().toString().equals(pack)) {
                        flag = "";

                        if (isMyServiceRunning(HeaderService.class)) {
                            Intent i = new Intent(this, HeaderService.class);
                            this.stopService(i);
                        }

                    }
            }*/

           /* if(source.getViewIdResourceName().equals("com.tul.tatacliq:id/productName")) {
                if(flag.equals("") || !flag.equals(source.getViewIdResourceName())) {
                    flag = source.getViewIdResourceName();
                    if (source.getClassName().toString().equals("android.widget.TextView")) {
                        Log.d("onAccessbility:::::", source.getText().toString());
                    }

                    if (!isMyServiceRunning(HeaderService.class)) {
                        Intent i = new Intent(this, HeaderService.class);
                        this.startService(i);
                    }
                }
            }*/
                //  Log.d("onAccess:::", source.getClassName().toString() +" Viewids " + source.getViewIdResourceName());

            }
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onInterrupt() {
        Log.d("!# onInterrupt", "called");

    }
}