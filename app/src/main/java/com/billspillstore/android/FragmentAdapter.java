package com.billspillstore.android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.billspillstore.android.Fragments_popup.FragmentFive;
import com.billspillstore.android.Fragments_popup.FragmentFour;
import com.billspillstore.android.Fragments_popup.FragmentOne;
import com.billspillstore.android.Fragments_popup.FragmentSeven;
import com.billspillstore.android.Fragments_popup.FragmentSix;
import com.billspillstore.android.Fragments_popup.FragmentThree;
import com.billspillstore.android.Fragments_popup.FragmentTwo;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new FragmentOne();
            case 1:
                return new FragmentFour();
            case 2:
            return new FragmentFive();
            case 3:
            return new FragmentSix();
            case 4:
                return new FragmentThree();
            case 5:
                return new FragmentTwo();
            case 6:
                return new FragmentSeven();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 7;
    }
}
