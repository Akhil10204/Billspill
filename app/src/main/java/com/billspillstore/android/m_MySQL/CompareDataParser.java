package com.billspillstore.android.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.billspillstore.android.m_DataObject.ComparePrice;
import com.billspillstore.android.m_UI.CompareListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class CompareDataParser extends AsyncTask<Void,Void,Integer> {

    Context ctx;
    String jsonData;
    ListView lvcompare;
    String message ="Variants found";

    ProgressDialog pd;
    ArrayList<ComparePrice> comparePrices = new ArrayList<>();
    public CompareDataParser(Context ctx, String jsonData, ListView lvcompare) {
        this.ctx = ctx;
        this.jsonData = jsonData;
        this.lvcompare = lvcompare;

    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(ctx);
        pd.setTitle("Loading...");
        pd.setCancelable(true);
        pd.setMessage("please wait");
        pd.show();


    }

    @Override
    protected void onPostExecute(Integer result) {
       if(pd.isShowing()){
        pd.dismiss();}

        if(!isCancelled()){
            CompareListAdapter adapter = new CompareListAdapter(ctx, comparePrices);
            lvcompare.setAdapter(adapter);
        }
      /*  if(result==0)
        {
            //Intent j = new Intent(ctx, CustomizedPopupActivity.class);
            //ctx.startActivity(j);
          //  Toast.makeText(ctx,"Unable to parse",Toast.LENGTH_SHORT).show();
        }

        else if(PopupListActivity.isviewexist) {

                CompareListAdapter adapter = new CompareListAdapter(ctx, comparePrices);
                lvcompare.setAdapter(adapter);

        }*/
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }


    private int parseData(){
        if(!isCancelled()) {

            try {

                JSONArray ja = new JSONArray(jsonData);
                JSONObject jo = null;
                comparePrices.clear();
                ComparePrice comparePrice;

                for (int i = 0; i < ja.length(); i++) {

                    jo = ja.getJSONObject(i);

                    String id = jo.getString("id");
                    //  int sno=jo.getInt("uid");
                    String pname = jo.getString("name");
                    int prate = jo.getInt("price");
                    String ptype = jo.getString("category");
                    String pseller = jo.getString("company");
                    String plink = jo.getString("url");
                    String ilink = jo.getString("image");
                    // String timestamp=jo.getString("date");

                    comparePrice = new ComparePrice();

                    comparePrice.setIdc(id);
                    //  product.setSno(sno);
                    comparePrice.setNamec(pname);
                    comparePrice.setRatec(prate);
                    comparePrice.setTypec(ptype);
                    comparePrice.setSellerc(pseller);
                    comparePrice.setPurlc(plink);
                    comparePrice.setIurlc(ilink);
                    // comparePrice.setTimesc(timestamp);
                    comparePrices.add(comparePrice);

                }
                return 1;


            } catch (JSONException e) {
                e.printStackTrace();
            }


            return 0;
        }
        return 0;
    }
}
