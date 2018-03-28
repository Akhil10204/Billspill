package com.billspillstore.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.billspillstore.android.m_MySQL.TopProductDownloader;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class TopProductActivity extends Activity {
    String category;

    final static String urladdress = "http://billspill.com/topten.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_product);

        TextView title = (TextView)findViewById(R.id.titleproducts);
        Intent i = this.getIntent();
         category =i.getExtras().getString("Category");
        if(category!=null) {
            if (category.equals("Mobiles")) {
                title.setText("Top 10 Mobiles");
            }
            if (category.equals("Laptops")) {
                title.setText("Top 10 Laptops");
            }
            if (category.equals("Speakers")) {
                title.setText("Top 10 Speakers");
            }
            if (category.equals("Televisions")) {
                title.setText("Top 10 Televisions");
            }
            if (category.equals("Storage")) {
                title.setText("Top 10 Pendrives");
            }
            if (category.equals("Laptop Accessories")) {
                title.setText("Top 10 Mouse");
            }
            if (category.equals("Tablets")) {
                title.setText("Top 10 Tablets");
            }
            if (category.equals("Mobile Accessories")) {
                title.setText("Top 10 Powerbanks");
            }
            if (category.equals("Personal Care Appliances")) {
                title.setText("Top 10 Trimmers");
            }
            if (category.equals("Headphones")) {
                title.setText("Top 10 Headphones");
            }
            if (category.equals("Home Appliances")) {
                title.setText("Top 10 Refrigerators");
            }
            if (category.equals("Monitors")) {
                title.setText("Top 10 Monitors");
            }
            if (category.equals("Hard Disk")) {
                title.setText("Top 10 Hard drive");
            }
            if (category.equals("water purifier")) {
                title.setText("Top 10 Water purifier");
            }
            if (category.equals("Cameras")) {
                title.setText("Top 10 Cameras");
            }



        }

    }


    @Override
    protected void onStart() {

        //Toast.makeText(getApplicationContext(),"Category is :"+category,Toast.LENGTH_LONG).show();
        final ListView lv = (ListView)findViewById(R.id.topproductlist);
        new TopProductDownloader(TopProductActivity.this,lv,urladdress).execute(category);
        super.onStart();
    }
}
