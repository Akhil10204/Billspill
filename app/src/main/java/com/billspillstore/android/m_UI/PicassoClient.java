package com.billspillstore.android.m_UI;

import android.content.Context;
import android.widget.ImageView;

import com.billspillstore.android.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class PicassoClient {


    public static void downloadimage(Context ctx, String imageurl, ImageView img) {


        if(imageurl.length()>0 ){

            Picasso.with(ctx).load(imageurl).placeholder(R.drawable.image_placeholder).into(img);

        }
        else if (imageurl==null){

            Picasso.with(ctx).load(R.drawable.close_icon).into(img);

        }
        else
        {
           Picasso.with(ctx).load(R.drawable.flipkart).into(img);
        }



    }
}