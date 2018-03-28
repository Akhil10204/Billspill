package com.billspillstore.android.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.billspillstore.android.m_DataObject.OfferClass;
import com.billspillstore.android.m_UI.OfferAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Akhil Saraswat on 19-05-2017.
 */

public class OfferParser extends AsyncTask<Void,Void,Integer> {


    Context ctx;
    String jsonData;
    ListView lv;
    ProgressDialog pd;




    ArrayList<OfferClass> offerclas = new ArrayList<>();
    public OfferParser(Context ctx, String jsonData, ListView lv) {
        this.ctx = ctx;
        this.jsonData = jsonData;
        this.lv = lv;

    }


    protected void onPreExecute() {
        super.onPreExecute();

     /*   pd = new ProgressDialog(ctx);
        pd.setTitle("Parse");
        pd.setMessage("Parsing....please wait");
        pd.show();*/



    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        //pd.dismiss();
if(!isCancelled()) {
    if (result == 0) {

        // Toast.makeText(ctx,"Unable to parse",Toast.LENGTH_SHORT).show();
    } else {

        OfferAdapter adapter = new OfferAdapter(ctx, offerclas);
        lv.setAdapter(adapter);

    }
}
    }

    private int parseData(){
if(!isCancelled()) {
    try {

        JSONArray ja = new JSONArray(jsonData);
        JSONObject jo = null;
        offerclas.clear();
        OfferClass offerClass;

        for (int i = 0; i < ja.length(); i++) {

            jo = ja.getJSONObject(i);


            String offer = jo.getString("offer");
            String link = jo.getString("link");
            String company = jo.getString("company");


            offerClass = new OfferClass();

            offerClass.setOffer(offer);
            offerClass.setLink(link);
            offerClass.setCompany(company);


            offerclas.add(offerClass);

        }
        return 1;


    } catch (JSONException e) {
        e.printStackTrace();
    }


    return 0;
}
return 0;
    }
    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }
}
