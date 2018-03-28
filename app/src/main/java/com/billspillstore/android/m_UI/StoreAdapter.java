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
import com.billspillstore.android.m_DataObject.StoreDetails;

import java.util.ArrayList;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class StoreAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<StoreDetails> storeDetailses;
    LayoutInflater inflater;

    public StoreAdapter(Context ctx, ArrayList<StoreDetails> storeDetailses) {
        this.ctx = ctx;
        this.storeDetailses = storeDetailses;

        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return storeDetailses.size();
    }

    @Override
    public Object getItem(int position) {
        return storeDetailses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.storemodel,parent,false);
        }

       // TextView im = (TextView)convertView.findViewById(R.id.storename);
        TextView rt = (TextView) convertView.findViewById(R.id.bestprice);
        ImageView image=(ImageView) convertView.findViewById(R.id.storeimage);
        ImageView logo=(ImageView) convertView.findViewById(R.id.storeicon);
        TextView name=(TextView) convertView.findViewById(R.id.productname);




        final StoreDetails storeDetails = storeDetailses.get(position);
        int Price = storeDetails.getRated();
        rt.setText(""+Price);
        //im.setText(storeDetails.getIurld());
        name.setText((storeDetails.getNamed()));

        PicassoClient.downloadimage(ctx,storeDetails.getIurld(),image);

        String company=storeDetails.getSeller();

        if(company.equals("Flipkart") || company.equals("flipkart")){
            logo.setImageResource(R.drawable.flipkart_long);

        }else if(company.equals("Amazon") || company.equals("amazon")){
            logo.setImageResource(R.drawable.amazon_long);

        }else if(company.equals("Snapdeal") || company.equals("snapdeal")){
            logo.setImageResource(R.drawable.snapdeal_long);

        }else if(company.equals("Jabong") || company.equals("jabong")){
            logo.setImageResource(R.drawable.jabong);

        }else if(company.equals("Myntra") || company.equals("myntra")){
            logo.setImageResource(R.drawable.myntra_long);

        }else if(company.equals("Paytm") || company.equals("paytm")){
            logo.setImageResource(R.drawable.paytm_logo);

        }else if(company.equals("Shopclues") || company.equals("shopclues")){

        }else if(company.equals("Ebay") || company.equals("ebay")){
            logo.setImageResource(R.drawable.ebay_logo);

        }


        convertView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
               // Intent loadurl =  new Intent(Intent.ACTION_VIEW, Uri.parse(storeDetails.getProductlink()));
               // ctx.startActivity(loadurl);

        openwebviewactivity(storeDetails.getProductlink());

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
