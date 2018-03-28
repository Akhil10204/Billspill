package com.billspillstore.android;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class MenuAboutus extends AppCompatActivity {
    ImageView facebook,twitter,google,linkedin,image1,image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_aboutus);
        facebook=(ImageView)findViewById(R.id.facebook);
        twitter=(ImageView)findViewById(R.id.twitter);
        google=(ImageView)findViewById(R.id.google);
        linkedin=(ImageView)findViewById(R.id.linkedin);
        image1=(ImageView)findViewById(R.id.image1);
        image2=(ImageView)findViewById(R.id.image2);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadurl =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://bit.ly/2spyX7c"));
                startActivity(loadurl);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadurl =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://bit.ly/extension_BillSpill"));
                startActivity(loadurl);
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent loadurl =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://bit.ly/2sB9Dvw"));
                startActivity(loadurl);
            }
        });

        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadurl =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://bit.ly/2rwAILS"));
                startActivity(loadurl);
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadurl =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://bit.ly/2spkWpU"));
                startActivity(loadurl);
            }
        });

        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loadurl =  new Intent(Intent.ACTION_VIEW, Uri.parse("http://bit.ly/2stwMjR"));
                startActivity(loadurl);
            }
        });
    }
}
