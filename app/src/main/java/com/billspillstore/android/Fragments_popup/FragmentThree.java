package com.billspillstore.android.Fragments_popup;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.billspillstore.android.R;
import com.billspillstore.android.m_DataObject.Coupon;
import com.billspillstore.android.m_DataObject.StoreDetails;
import com.billspillstore.android.m_MySQL.CouponDownloader;
import com.billspillstore.android.m_UI.CouponListAdapter;
import com.billspillstore.android.m_UI.StoreAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Priyam Tyagi on 15-05-2017.
 */

public class FragmentThree extends Fragment {

    final static String url= "http://billspill.com/ebaytesting.php";
    TextView textView;
    ProgressDialog pd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_three,container,false);


      /*  final ListView lv=(ListView)view.findViewById(R.id.couponlist);
        Context ctx=getActivity();

        Coupon coupon=new Coupon();
        ArrayList<Coupon> sd=new ArrayList<>();
        sd.add(coupon);
        if(coupon.getCompany() !=null){
            new CouponListAdapter(ctx,sd);

        }else {

            new CouponDownloader(getActivity(), lv, url).execute();
        }*/
        return view;
    }


}
