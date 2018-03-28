package com.billspillstore.android.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.billspillstore.android.m_DataObject.StoreDetails;
import com.billspillstore.android.m_UI.StoreAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class StoreDataParser extends AsyncTask<Void,Void,Integer> {

    Context ctx;
    String jsonData;
    ListView lvd;

    ProgressDialog pd;
    ArrayList<StoreDetails> storeDetailses = new ArrayList<>();
    public StoreDataParser(Context ctx, String jsonData, ListView lvd) {
        this.ctx = ctx;
        this.jsonData = jsonData;
        this.lvd = lvd;

    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       /* pd = new ProgressDialog(ctx);
        pd.setTitle("Loading");
        pd.setMessage("please wait");
        pd.show();*/

    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
       // pd.dismiss();
       if(!isCancelled()){
         if(result==1){
        StoreAdapter sadapter=new StoreAdapter(ctx,storeDetailses);
        lvd.setAdapter(sadapter);
        }
        }
      /*  if(result==0)
        {

          //  Toast.makeText(ctx,"Unable to parse",Toast.LENGTH_SHORT).show();
        }

        else if(ExpandedPopupActivity.isviewexist) {

                StoreAdapter sadapter=new StoreAdapter(ctx,storeDetailses);
                lvd.setAdapter(sadapter);




        }*/
    }
    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }


    private int parseData(){
        if(!isCancelled()){

        try{

            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo = null;
            storeDetailses.clear();
            StoreDetails storedetails;

            for (int i=0;i<ja.length();i++)
            {

                jo = ja.getJSONObject(i) ;

                String named=jo.getString("name");
                int prate=jo.getInt("price");
                String seller=jo.getString("company");
                String affiliatelink = jo.getString("url");
                String ilink=jo.getString("image");
                String cat=jo.getString("category");









                storedetails=new StoreDetails();

                storedetails.setNamed(named);
                storedetails.setRated(prate);
                storedetails.setSeller(seller);

                storedetails.setIurld(ilink);
                storedetails.setCategory(cat);

                storedetails.setProductlink(affiliatelink);





                storeDetailses.add(storedetails);






            }
            return 1;




        } catch (JSONException e) {
            e.printStackTrace();
        }


        return 0;}
        return 0;
    }

}
