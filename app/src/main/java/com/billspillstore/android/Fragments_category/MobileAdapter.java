package com.billspillstore.android.Fragments_category;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Priyam Tyagi on 07-06-2017.
 */

public class MobileAdapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> fragmenttitlelist=new ArrayList<>();
    public MobileAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmenttitlelist.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    public void addFragment(Fragment fragment,String title){
        fragmentList.add(fragment);
        fragmenttitlelist.add(title);
    }
}
