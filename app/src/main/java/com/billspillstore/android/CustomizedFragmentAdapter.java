package com.billspillstore.android;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.billspillstore.android.Fragments_popup.BlankCompare;
import com.billspillstore.android.Fragments_popup.BlankCoupon;
import com.billspillstore.android.Fragments_popup.BlankGraph;
import com.billspillstore.android.Fragments_popup.BlankSale;
import com.billspillstore.android.Fragments_popup.FragmentFive;
import com.billspillstore.android.Fragments_popup.FragmentSeven;
import com.billspillstore.android.Fragments_popup.FragmentSix;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class CustomizedFragmentAdapter extends FragmentPagerAdapter{
    public CustomizedFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new FragmentFive();
            case 1:
                return new FragmentSix();
            case 2:
                return new FragmentSeven();
            case 3:
                return new BlankCompare();
            case 4:
                return new BlankGraph();
            case 5:
                return  new BlankCoupon();
            case 6:
                return new BlankSale();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 7;
    }
}
