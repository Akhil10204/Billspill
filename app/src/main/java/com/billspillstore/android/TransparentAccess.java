package com.billspillstore.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;



public class TransparentAccess extends Activity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transparent_access);

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



        ViewPager pager=(ViewPager)findViewById(R.id.access_viewpager);
        CustomSwipeAdapter adapter=new CustomSwipeAdapter(TransparentAccess.this);
        pager.setAdapter(adapter);
        pager.setClipToPadding(false);
        pager.setPageMargin(24);
        pager.setPadding(30,8,60,8);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==1){
                    button.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

         button= (Button)findViewById(R.id.btn);
        button.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TransparentAccess.this,TransparentGuide.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(TransparentAccess.this,TabbedMainActivity.class);
        startActivity(intent);
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        this.finish();
        super.onPause();
    }
}
