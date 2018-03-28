package com.billspillstore.android.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.billspillstore.android.DetailActivity;
import com.billspillstore.android.R;
import com.billspillstore.android.m_DataObject.Product;

import java.util.ArrayList;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class CustomAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<Product> products;

    LayoutInflater inflater;


    public CustomAdapter(Context ctx, ArrayList<Product> products) {
        this.ctx = ctx;
        this.products = products;

        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.model,parent,false);
        }

        ImageView im = (ImageView)convertView.findViewById(R.id.movieimage);
        TextView nm = (TextView) convertView.findViewById(R.id.textname);
        TextView rt = (TextView) convertView.findViewById(R.id.textrate);
        TextView sl = (TextView) convertView.findViewById(R.id.textseller);

        final Product product = products.get(position);

        nm.setText(product.getName());
        rt.setText(product.getRate());
        sl.setText(product.getSeller());


        PicassoClient.downloadimage(ctx,product.getIurl(),im);



        convertView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openDetailActivity(product.getName(),product.getRate(),product.getIurl(),product.getSeller(),product.getPurl());
            }
        });


        return convertView;
    }


    private void openDetailActivity(String name,String rate,String imageurl,String seller,String purl){
        Intent i=new Intent(ctx, DetailActivity.class);

        i.putExtra("NAME",name);
        i.putExtra("RATE",rate);
        i.putExtra("IMAGE",imageurl);
        i.putExtra("SELLER",seller);
        i.putExtra("PRODUCT",purl);


        ctx.startActivity(i);
    }
}
