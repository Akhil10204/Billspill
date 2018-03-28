package com.billspillstore.android.Fragments_popup;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.billspillstore.android.R;
import com.billspillstore.android.m_MySQL.StoreDownloader;


/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class FragmentOne extends Fragment {

    final static String url= "http://billspill.com/datafetch.php";
    String name,image,seller,category;
    int rate;
    TextView vname,vrate,vseller;
    ImageView vim;
    StoreDownloader storeDownloader;

   public void getDetail(String varname,int varrate,String varimage,String varseller,String varcategory){
        this.name = varname;
        this.rate=varrate;
        this.image=varimage;
        this.seller=varseller;
        this.category=varcategory;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one,container,false);

       /* vname = (TextView)view.findViewById(R.id.variantname);
        vrate = (TextView)view.findViewById(R.id.variantrate);
        vseller = (TextView)view.findViewById(R.id.variantseller);
        vim = (ImageView)view.findViewById(R.id.variantimage);*/
        final  ListView lv = (ListView)view.findViewById(R.id.variantlistview);

         name =  getActivity().getIntent().getExtras().getString("NAME");
      //  rate =  getActivity().getIntent().getExtras().getInt("RATE");
      //  image =  getActivity().getIntent().getExtras().getString("IMAGE");
       // seller =  getActivity().getIntent().getExtras().getString("SELLER");
        category=getActivity().getIntent().getExtras().getString("CATEGORY");

        Context ctx=getActivity();

       /* vname.setText(name);
        vrate.setText(rate);
        vseller.setText(seller);
        PicassoClient.downloadimage(getActivity(),image,vim);*/

           storeDownloader = new StoreDownloader(getActivity(), lv, url);
           storeDownloader.execute(name, category);

        return view;
    }
}
