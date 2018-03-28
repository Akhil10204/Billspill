package com.billspillstore.android.m_UI;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.billspillstore.android.R;
import com.billspillstore.android.WebviewActivity;
import com.billspillstore.android.m_DataObject.StoreDetails;
import com.billspillstore.android.m_DataObject.TopProduct;

import java.util.ArrayList;

/**
 * Created by Priyam Tyagi on 19-05-2017.
 */

public class TopProductAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<TopProduct> variable;
    LayoutInflater inflater;

    public TopProductAdapter(Context ctx, ArrayList<TopProduct> variable) {
        this.ctx = ctx;
        this.variable = variable;
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return variable.size();
    }

    @Override
    public Object getItem(int position) {
        return variable.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.top_product_model,parent,false);
        }

        TextView name = (TextView)convertView.findViewById(R.id.topname);
        TextView rate = (TextView) convertView.findViewById(R.id.toprate);
        ImageView company = (ImageView) convertView.findViewById(R.id.topseller);
        ImageView image = (ImageView)convertView.findViewById(R.id.topimage);


        final TopProduct producttop = variable.get(position);
        name.setText(producttop.getTopname());
        rate.setText(producttop.getToprate());
       // company.setText(producttop.getTopseller());
        if(producttop.getTopseller().equals("flipkart")){
            company.setImageResource(R.drawable.flipkart_long);
        }
        if(producttop.getTopseller().equals("amazon")){
            company.setImageResource(R.drawable.amazon_long);
        }

        PicassoClient.downloadimage(ctx,producttop.getTopimage(),image);

        convertView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
               openwebviewactivity(producttop.getTopurl());



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
