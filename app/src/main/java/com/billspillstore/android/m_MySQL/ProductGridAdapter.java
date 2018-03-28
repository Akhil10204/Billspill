package com.billspillstore.android.m_MySQL;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.billspillstore.android.R;
import com.billspillstore.android.TopProductActivity;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class ProductGridAdapter extends BaseAdapter {

    private int[] icons;
    private String[] name;
    private  String[] sending;
    private Context ctx;
    private LayoutInflater lf;

    public ProductGridAdapter(int[] icons, String[] name,String[] sending, Context ctx) {
        this.icons = icons;
        this.name = name;
        this.ctx = ctx;
        this.sending=sending;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return name[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View gridview=convertView;
        if(convertView==null){
            lf=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            gridview = lf.inflate(R.layout.product_grid_model,null);
        }

        ImageView image = (ImageView)gridview.findViewById(R.id.gridimage);
        TextView text = (TextView)gridview.findViewById(R.id.gridtext);
        image.setImageResource(icons[position]);
        text.setText(name[position]);

        gridview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTopProductActivity(sending[position]);
            }
        });

        return gridview;
    }

    private void openTopProductActivity(String category){

        Intent i=new Intent(ctx, TopProductActivity.class);
        i.putExtra("Category",category);
        ctx.startActivity(i);
    }
}
