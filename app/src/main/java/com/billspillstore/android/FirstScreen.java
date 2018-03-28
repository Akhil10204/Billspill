package com.billspillstore.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class FirstScreen extends Activity {

   // ViewPager Pager;
    //PagerSwipeAdapter Adapter;


    public Button b1;



    public void init() {
        b1 = (Button) findViewById(R.id.skipbutton);
        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent toy = new Intent(FirstScreen.this, TransparentAccess.class);
                startActivity(toy);
            }
        });


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);


        ViewPager  Pager = (ViewPager) findViewById(R.id.viewpager);
        PagerSwipeAdapter Adapter = new PagerSwipeAdapter(this);
        Pager.setClipToPadding(false);
        Pager.setPageMargin(24);
        Pager.setPadding(40,8,60,8);
        Pager.setAdapter(Adapter);
       // Pager.setPageTransformer(true, new RotateUpTransformer());


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



        init();


    }

    @Override
    protected void onPause() {
        this.finish();
        super.onPause();
    }
}