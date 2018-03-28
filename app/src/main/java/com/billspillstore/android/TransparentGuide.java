package com.billspillstore.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class TransparentGuide extends Activity {
    String TAG="MY TAG::::";

    ViewPager viewPager;
    Button button;
    TextView textView1,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent_guide);

        SharedPreferences pref = getSharedPreferences("ActivityPREF", Context.MODE_PRIVATE);
        if (pref.getBoolean("activity_executed", false)) {
            Intent intent = new Intent(this, TabbedMainActivity.class);
            startActivity(intent);
            finish();
        } else {
            SharedPreferences.Editor ed = pref.edit();
            ed.putBoolean("activity_executed", true);
            ed.apply();
        }

       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivity(intent);

        } else {
            //initializeView();
        }

        if (!isAccessibilitySettingsOn(this.getApplicationContext())) {
            startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
        }*/



        viewPager=(ViewPager)findViewById(R.id.guide_viewpager);
        button=(Button)findViewById(R.id.guide_btn);
        textView1=(TextView)findViewById(R.id.desc1);
        textView2=(TextView)findViewById(R.id.desc2);
        PagerSwipeAdapter adapter=new PagerSwipeAdapter(TransparentGuide.this);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(position==0){
                        textView2.setText("");
                        textView1.setText("Select Category Here");
                }
                if(position==1){
                    textView2.setText("BillSpill Search at Multiple Stores");
                    textView1.setText("");
                }

                if(position==2)
                {   textView1.setText("");
                    textView2.setText("Select Stores for Comparison");
                    button.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        button.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TransparentGuide.this,PromoActivity.class);
                startActivity(intent);
            }
        });
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

    @Override
    protected void onPause() {
        this.finish();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, PromoActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }
}
