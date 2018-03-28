package com.billspillstore.android.m_UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.billspillstore.android.ExpandedPopupActivity;
import com.billspillstore.android.R;
import com.billspillstore.android.m_DataObject.ComparePrice;

import java.util.ArrayList;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class CompareListAdapter extends BaseAdapter {

    Context ctx;
    ArrayList<ComparePrice> comparePrices;

    LayoutInflater inflater;

    public CompareListAdapter(Context ctx, ArrayList<ComparePrice> comparePrices) {
        this.ctx = ctx;
        this.comparePrices = comparePrices;
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return comparePrices.size();
    }

    @Override
    public Object getItem(int position) {
        return comparePrices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.compare_list_model,parent,false);
        }

        ImageView im = (ImageView)convertView.findViewById(R.id.compareimage);
        TextView nm = (TextView) convertView.findViewById(R.id.comparename);
        TextView rt = (TextView) convertView.findViewById(R.id.comparerate);
        TextView sl = (TextView) convertView.findViewById(R.id.compareseller);

        final ComparePrice comparePrice = comparePrices.get(position);
        int Price=comparePrice.getRatec();
        nm.setText(comparePrice.getNamec());
        rt.setText(""+Price);
        sl.setText(comparePrice.getSellerc());
        PicassoClient.downloadimage(ctx,comparePrice.getIurlc(),im);


        convertView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                openExpanderActivity(comparePrice.getNamec(),comparePrice.getRatec(),comparePrice.getIurlc(),comparePrice.getSellerc(),comparePrice.getPurlc(),comparePrice.getTypec());


            }
        });
        return convertView;
    }

   private void openExpanderActivity(String name,int rate,String imageurl,String seller,String pdtlink,String category){

        Intent i=new Intent(ctx, ExpandedPopupActivity.class);

        i.putExtra("NAME",name);
        i.putExtra("RATE",rate);
        i.putExtra("IMAGE",imageurl);
        i.putExtra("SELLER",seller);
       i.putExtra("PdtLink",pdtlink);
       i.putExtra("CATEGORY",category);

        ctx.startActivity(i);
    }

}
