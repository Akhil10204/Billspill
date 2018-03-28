package com.billspillstore.android.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.GridView;

import com.billspillstore.android.m_DataObject.AppGridObject;
import com.billspillstore.android.m_UI.AppGridAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class GridParser extends AsyncTask<Void,Void,Integer> {

    Context ctx;
    String jsonData;
    GridView lv;
    ProgressDialog pd;




    ArrayList<AppGridObject> object = new ArrayList<>();
    public GridParser(Context ctx, String jsonData, GridView lv) {
        this.ctx = ctx;
        this.jsonData = jsonData;
        this.lv = lv;

    }


    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(ctx);
        pd.setTitle("Parse");
        pd.setMessage("Parsing....please wait");
        pd.show();

        Log.d("Data","Parsed");

    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        pd.dismiss();

        if(result==0)
        {

           // Toast.makeText(ctx,"Unable to parse",Toast.LENGTH_SHORT).show();
        }

        else {
            AppGridAdapter adapter= new AppGridAdapter(ctx,object);
           // OfferAdapter adapter=new OfferAdapter(ctx,offerclas);
           lv.setAdapter(adapter);

        }
    }

    private int parseData(){

        try{

            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo = null;
            object.clear();
            AppGridObject Object;

            for (int i=0;i<ja.length();i++)
            {

                jo = ja.getJSONObject(i) ;


                String appname=jo.getString("appname");
                String link=jo.getString("link");



                Object=new AppGridObject();

                Object.setAppname(appname);
                Object.setApplink(link);
                object.add(Object);

            }
            return 1;




        } catch (JSONException e) {
            e.printStackTrace();
        }


        return 0;
    }
    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }
}


