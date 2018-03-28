package com.billspillstore.android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.billspillstore.android.m_MySQL.StoreDownloader;
import com.billspillstore.android.m_UI.PicassoClient;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class DetailActivity extends AppCompatActivity{

    TextView dname,drate,dseller;
    ImageView dim;

    final static String urladdressd = "http://billspill.com/datafetch.php";
    @Override
    protected void onStart() {

       final ListView lvd = (ListView) findViewById(R.id.storelv);
        Intent i = this.getIntent();
        String Nam = i.getExtras().getString("NAME");
       new StoreDownloader(DetailActivity.this, lvd, urladdressd).execute(Nam);
        super.onStart();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        dname = (TextView)findViewById(R.id.detailname);
        drate = (TextView)findViewById(R.id.detailrate);
        dim = (ImageView)findViewById(R.id.detailimage);
        dseller =(TextView)findViewById(R.id.detailseller);

        Intent i = this.getIntent();
        String Nam = i.getExtras().getString("NAME");
        String Rat = i.getExtras().getString("RATE");
        String Img = i.getExtras().getString("IMAGE");
        String Slr = i.getExtras().getString("SELLER");
        final String dpdt =i.getExtras().getString("PRODUCT");

        dname.setText(Nam);
        drate.setText(Rat);
        dseller.setText(Slr);
        PicassoClient.downloadimage(this,Img,dim);

    }
}