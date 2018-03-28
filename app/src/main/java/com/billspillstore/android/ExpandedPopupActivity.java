package com.billspillstore.android;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;
import com.billspillstore.android.Fragments_popup.FragmentFour;
import com.billspillstore.android.Fragments_popup.FragmentOne;
import com.billspillstore.android.m_DataObject.StoreDetails;

public class ExpandedPopupActivity extends FragmentActivity
{
    public static boolean isviewexist=false;

    String title0 = "Compare Price";
    String title5 ="Price Graph";
    String title1 = "Automatic Coupon";
    String title2 ="Notify for Sale";
    String title3 ="Top 10 Products";
    String title4 ="Top 10 Offers";
    String title6 ="Locals";

    @Override
    protected void onStop() {
        isviewexist=false;
        super.onStop();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        isviewexist=true;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expanded_popup);
        Intent i = this.getIntent();
        String Nam = i.getExtras().getString("NAME");
        int Rat = i.getExtras().getInt("RATE");
        String Img = i.getExtras().getString("IMAGE");
        String Slr = i.getExtras().getString("SELLER");
        String category=i.getExtras().getString("CATEGORY");
        final String pdt=i.getExtras().getString("PdtLink");

        Button button = (Button)findViewById(R.id.buybutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pdt != null) {
                   // Intent loadurl = new Intent(Intent.ACTION_VIEW, Uri.parse(pdt));
                   // startActivity(loadurl);
                    openwebviewactivity(pdt);
                }

            }
        });





      if((Nam != null) && (Rat != 0) && (Img != null) && (Slr != null) && (category!=null))
      {
           FragmentOne f = new FragmentOne();
           f.getDetail(Nam,Rat,Img,Slr,category);
          FragmentFour ff = new FragmentFour();
          ff.getDetails(Nam,Rat,Img);
      }
        final TextView title = (TextView)findViewById(R.id.pagertitle);
        ViewPager pager = (ViewPager)findViewById(R.id.popuppager);
        pager.setClipToPadding(false);
        pager.setPageMargin(24);
        pager.setPadding(60,8,60,8);
        pager.setOffscreenPageLimit(7);
        FragmentAdapter fadapter= new FragmentAdapter(getSupportFragmentManager());
        pager.setAdapter(fadapter);
        //pager.setPageTransformer(true, new BackgroundToForegroundTransformer());
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
            }

            @Override
            public void onPageSelected(int position)
            {
                if(position==0){title.setText(title0);}
                if(position==1){title.setText(title2);}
                if(position==2){title.setText(title3);}
                if(position==3){title.setText(title4);}


                if(position==4){
                    title.setText(title1);

                }
                if(position==5){title.setText(title5);}
                if(position==6){title.setText(title6);}

            }
            @Override
            public void onPageScrollStateChanged(int state)
            {
            }
        });
    }
    @Override
    protected void onPause()
    {
        isviewexist=false;
       // Intent serviceIntent = new Intent(this, HeaderService.class);
       // startService(serviceIntent);
        super.onPause();
    }
    @Override
    protected void onStart()
    {
        isviewexist=true;
        Intent serviceIntent = new Intent(this, HeaderService.class);
        stopService(serviceIntent);
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        isviewexist=false;
        Intent serviceIntent = new Intent(this, HeaderService.class);
        startService(serviceIntent);
        super.onDestroy();
    }

    public void openwebviewactivity(String url){

        Intent i = new Intent(ExpandedPopupActivity.this, WebviewActivity.class);
        i.putExtra("URL",url);
        ExpandedPopupActivity.this.startActivity(i);


    }
}
