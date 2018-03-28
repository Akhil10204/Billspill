package com.billspillstore.android;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class CustomSwipeAdapter extends PagerAdapter {

    private   int[] image_rec={R.drawable.access_oneone,R.drawable.accessonetwo};

    private Context ctx;
    private LayoutInflater layi;

    public CustomSwipeAdapter (Context ctx){
        this.ctx=ctx;
    }




    @Override
    public int getCount() {
        return image_rec.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layi=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view=layi.inflate(R.layout.swipe_layout,container,false);
        ImageView imageView =(ImageView) item_view.findViewById(R.id.image_view);
        imageView.setImageResource(image_rec[position]);
        container.addView(item_view);
        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
