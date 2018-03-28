package com.billspillstore.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class CustomizedPopupActivity extends FragmentActivity {

    String title3 ="Top 10 Products";
    String title4 ="Top 10 Offers";
    String title6 ="Locals";

    String title0 = "Compare Price";
    String title5 ="Price Graph";
    String title1 = "Automatic Coupon";
    String title2 ="Notify for Sale";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customized_popup);


        final TextView title = (TextView)findViewById(R.id.cust_titletext);
        ViewPager pager = (ViewPager)findViewById(R.id.cust_viewpager);
        pager.setOffscreenPageLimit(7);
        CustomizedFragmentAdapter fadapter= new CustomizedFragmentAdapter(getSupportFragmentManager());
        pager.setAdapter(fadapter);
        pager.setPageTransformer(true, new BackgroundToForegroundTransformer());
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
            }

            @Override
            public void onPageSelected(int position)
            {
                if(position==0){title.setText(title3);}
                if(position==1){title.setText(title4);}
                if(position==2){title.setText(title6);}
                if(position==3){title.setText(title0);}
                if(position==4){title.setText(title5);}
                if(position==5){title.setText(title1);}
                if(position==6){title.setText(title2);}


            }
            @Override
            public void onPageScrollStateChanged(int state)
            {
            }
        });

    }

    @Override
    protected void onDestroy() {
        Intent serviceIntent = new Intent(this, HeaderService.class);
        startService(serviceIntent);
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        Intent serviceIntent = new Intent(this, HeaderService.class);
        stopService(serviceIntent);
        super.onStart();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }
}
