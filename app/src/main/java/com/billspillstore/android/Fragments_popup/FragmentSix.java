package com.billspillstore.android.Fragments_popup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.billspillstore.android.R;
import com.billspillstore.android.m_MySQL.OfferDownloader;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class FragmentSix extends Fragment {
    final static String urls= "http://billspill.com/topoffer.php";
    OfferDownloader offerDownloader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_six,container,false);
        final ListView lv = (ListView)view.findViewById(R.id.offerlist);

        offerDownloader= new OfferDownloader(getActivity(), lv, urls);
       offerDownloader.execute();
        return view;
    }
}
