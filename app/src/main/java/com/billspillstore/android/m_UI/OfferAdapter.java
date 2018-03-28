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
import com.billspillstore.android.m_DataObject.OfferClass;

import java.util.ArrayList;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class OfferAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<OfferClass> offerclass;
    LayoutInflater inflater;

    public OfferAdapter(Context ctx, ArrayList<OfferClass> offerclass) {
        this.ctx = ctx;
        this.offerclass = offerclass;
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return offerclass.size();
    }

    @Override
    public Object getItem(int position) {
        return offerclass.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.offer_list_model,parent,false);
        }

        ImageView image = (ImageView)convertView.findViewById(R.id.offerimage);
        TextView offer =(TextView)convertView.findViewById(R.id.offertext);

        final OfferClass offers = offerclass.get(position);
        offer.setText(offers.getOffer());
        if(offers.getCompany().equals("flipkart")){ image.setImageResource(R.drawable.flipkart_logo);}
        if(offers.getCompany().equals("snapdeal")){   image.setImageResource(R.drawable.snapdeal_logo);  }
        if(offers.getCompany().equals("amazon")){image.setImageResource(R.drawable.amazon);}
        if(offers.getCompany().equals("paytm")){image.setImageResource(R.drawable.paytm_logo);}
        if(offers.getCompany().equals("ebay")){image.setImageResource(R.drawable.ebay_logo);}
        if(offers.getCompany().equals("tatacliq")){image.setImageResource(R.drawable.tatacliq_logo);}
        if(offers.getCompany().equals("industry buying")){image.setImageResource(R.drawable.industry_logo);}
        if(offers.getCompany().equals("oyo rooms")){image.setImageResource(R.drawable.oyorooms);}
        if(offers.getCompany().equals("make my trip")){image.setImageResource(R.drawable.makemytrip_logo);}
        if(offers.getCompany().equals("dominos")){image.setImageResource(R.drawable.dominos_logo);}


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent loadurl =  new Intent(Intent.ACTION_VIEW, Uri.parse(offers.getLink()));
                //ctx.startActivity(loadurl);
                openwebviewactivity(offers.getLink());
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
