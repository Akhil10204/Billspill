package com.billspillstore.android.m_UI;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.billspillstore.android.R;
import com.billspillstore.android.m_DataObject.Coupon;

import java.util.ArrayList;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class CouponListAdapter extends BaseAdapter {
    Context ctx;
    ArrayList<Coupon> COupon;
String couponcode;
    LayoutInflater inflater;

    public CouponListAdapter(Context ctx, ArrayList<Coupon> COupon) {
        this.ctx = ctx;
        this.COupon = COupon;

        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return COupon.size();
    }

    @Override
    public Object getItem(int position) {
        return COupon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.coupon_list_model,parent,false);
        }

        TextView detail =(TextView)convertView.findViewById(R.id.coupondetail);

        final Coupon COUPON = COupon.get(position);
        detail.setText(COUPON.getDetail());
        couponcode = COUPON.getCode();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             couponadded();

            }
        });


        return convertView;
    }
public void couponadded(){

    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
    builder.setIcon(R.drawable.tick);
    builder.setTitle("Successfully added");
    builder.setMessage("Your coupon will be automatically applied once you tap the coupon box at product page");
    builder.show();
    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {

            dialog.dismiss();

        }
    });
}


}
