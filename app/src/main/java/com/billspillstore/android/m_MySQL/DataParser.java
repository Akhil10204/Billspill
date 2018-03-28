package com.billspillstore.android.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.billspillstore.android.PopupListActivity;
import com.billspillstore.android.m_DataObject.Product;
import com.billspillstore.android.m_UI.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class DataParser extends AsyncTask<Void,Void,Integer> {

    Context ctx;
    String jsonData;
    ListView lv;

    ProgressDialog pd;
    ArrayList<Product> products = new ArrayList<>();
    public DataParser(Context ctx, String jsonData, ListView lv) {
        this.ctx = ctx;
        this.jsonData = jsonData;
        this.lv = lv;

    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(PopupListActivity.compareactivity) {
            pd = new ProgressDialog(ctx);
            pd.setTitle("Parse");
            pd.setMessage("Parsing....please wait");
            pd.show();

            //Log.d("Data", "Parsed");
        }

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

            CustomAdapter adapter=new CustomAdapter(ctx,products);


            lv.setAdapter(adapter);

        }
    }

    @Override
    protected Integer doInBackground(Void... params) {
        if(PopupListActivity.compareactivity) {
            return this.parseData();
        }
        return null;
    }


    private int parseData(){

        try{

            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo = null;
            products.clear();
            Product product;

            for (int i=0;i<ja.length();i++)
            {

                jo = ja.getJSONObject(i) ;

                int id=jo.getInt("id");
              //  int sno=jo.getInt("uid");
                String pname=jo.getString("name");
                String prate=jo.getString("price");
                String ptype=jo.getString("category");
                String pseller=jo.getString("company");
                String plink=jo.getString("url");
                String ilink=jo.getString("image");
                String timestamp=jo.getString("date");

                product=new Product();

                product.setId(id);
              //  product.setSno(sno);
                product.setName(pname);
                product.setRate(prate);
                product.setType(ptype);
                product.setSeller(pseller);
                product.setPurl(plink);
                product.setIurl(ilink);
                product.setTimes(timestamp);

                products.add(product);

            }
            return 1;




        } catch (JSONException e) {
            e.printStackTrace();
        }


        return 0;
    }
}
