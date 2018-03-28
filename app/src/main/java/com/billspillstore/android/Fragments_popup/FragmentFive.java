package com.billspillstore.android.Fragments_popup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


import com.billspillstore.android.R;
import com.billspillstore.android.m_MySQL.ProductGridAdapter;


public class FragmentFive extends Fragment {

  int image[] ={R.drawable.mobile,R.drawable.laptop,R.drawable.speakers,R.drawable.television,R.drawable.pendrive,R.drawable.mouse,R.drawable.tablet,R.drawable.power_bank,R.drawable.trimmer,R.drawable.headphone,R.drawable.fridge,R.drawable.monitor,R.drawable.hard_disk,R.drawable.water_purifiers,R.drawable.camera};
    String name[]={"Mobile","Laptop","Speaker","Television","Pendrive","Mouse","Tablet","PowerBank","Trimmer","Headphone","Refrigerator","Monitor","Hard Drive","WaterPurifier","Camera"};
    String sending[]={"Mobiles","Laptops","Speakers","Televisions","Storage","Laptop Accessories","Tablets","Mobile Accessories","Personal Care Appliances","Headphones","Home Appliances","Monitors","Hard Disk","water purifier","Cameras"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_five,container,false);
        GridView grid = (GridView)view.findViewById(R.id.gridview);
        ProductGridAdapter adapter = new ProductGridAdapter(image,name,sending,getActivity());
        grid.setAdapter(adapter);
        return view;
    }
}
