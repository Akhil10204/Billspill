package com.billspillstore.android.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.billspillstore.android.R;
import com.billspillstore.android.WebviewActivity;
import com.billspillstore.android.m_DataObject.AppGridObject;

import java.util.ArrayList;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class AppGridAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<AppGridObject> object;
    LayoutInflater inflater;

    public AppGridAdapter(Context ctx, ArrayList<AppGridObject> object) {
        this.ctx = ctx;
        this.object = object;
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return object.size();
    }

    @Override
    public Object getItem(int position) {
        return object.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.app_gridview,parent,false);
        }

        ImageView image = (ImageView)convertView.findViewById(R.id.gridimage);
        TextView appname =(TextView)convertView.findViewById(R.id.gridtext);



        final AppGridObject Object = object.get(position);

        appname.setText(Object.getAppname());
        if(Object.getAppname().equals("Flipkart")){image.setImageResource(R.drawable.flipkart_logo);}
        if(Object.getAppname().equals("Snapdeal")){image.setImageResource(R.drawable.snapdeal_logo);}
        if(Object.getAppname().equals("Amazon")){image.setImageResource(R.drawable.amazon);}
        if(Object.getAppname().equals("Paytm")){image.setImageResource(R.drawable.paytm_logo);}
        if(Object.getAppname().equals("Ebay")){image.setImageResource(R.drawable.ebay_logo);}
      if(Object.getAppname().equals("Jabbong")){image.setImageResource(R.drawable.jabong);}
        if(Object.getAppname().equals("IndustryBuying")){image.setImageResource(R.drawable.industry_logo);}
        if(Object.getAppname().equals("Oyo Rooms")){image.setImageResource(R.drawable.oyorooms);}
        if(Object.getAppname().equals("Makemytrip")){image.setImageResource(R.drawable.makemytrip_logo);}
        if(Object.getAppname().equals("Homeshop18")){image.setImageResource(R.drawable.hs18);}
        if(Object.getAppname().equals("Ask me bazar")){image.setImageResource(R.drawable.ask);}
        if(Object.getAppname().equals("Cleartrip")){image.setImageResource(R.drawable.ct);}
        if(Object.getAppname().equals("Mobikwik")){image.setImageResource(R.drawable.mo);}
        if(Object.getAppname().equals("Freecharge")){image.setImageResource(R.drawable.free);}
        if(Object.getAppname().equals("Infibeam")){image.setImageResource(R.drawable.infi);}
        if(Object.getAppname().equals("Ferns n petals")){image.setImageResource(R.drawable.fern);}
        if(Object.getAppname().equals("Fab n furnish")){image.setImageResource(R.drawable.fdytf);}
        if(Object.getAppname().equals("First cry")){image.setImageResource(R.drawable.fc);}

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openwebviewactivity(Object.getApplink());
            }
        });


        return convertView;
    }

    public void openwebviewactivity(String url){

        Intent i = new Intent(ctx, WebviewActivity.class);
        i.putExtra("URL",url);
        ctx.startActivity(i);


    }
}

