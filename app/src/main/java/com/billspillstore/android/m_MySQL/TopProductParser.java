package com.billspillstore.android.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.billspillstore.android.m_DataObject.TopProduct;
import com.billspillstore.android.m_UI.TopProductAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class TopProductParser extends AsyncTask<Void,Void,Integer> {

    Context ctx;
    String jsonData;
    ListView lvd;
    ProgressDialog pd;
    ArrayList<TopProduct> topProducts = new ArrayList<>();

    public TopProductParser(Context ctx, String jsonData, ListView lvd) {
        this.ctx = ctx;
        this.jsonData = jsonData;
        this.lvd = lvd;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(ctx);
        pd.setTitle("Parse");
        pd.setMessage("Parsing....please wait");
        pd.show();

    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        pd.dismiss();

        if (result == 0) {

          //  Toast.makeText(ctx, "Unable to parse", Toast.LENGTH_SHORT).show();
        } else {
            TopProductAdapter adapter = new TopProductAdapter(ctx,topProducts);
            lvd.setAdapter(adapter);
            //call adapter
        }
    }
    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }

    private int parseData(){

        try{

            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo = null;
            topProducts.clear();
            TopProduct topproduct;

            for (int i=0;i<ja.length();i++)
            {
                jo = ja.getJSONObject(i) ;
                String name=jo.getString("name");
                String  price=jo.getString("price");
                String company=jo.getString("company");
                String affiliatelink = jo.getString("url");
                String image = jo.getString("image");

                topproduct=new TopProduct();
                topproduct.setTopname(name);
                topproduct.setToprate(price);
                topproduct.setTopseller(company);
                topproduct.setTopurl(affiliatelink);
                topproduct.setTopimage(image);
                topProducts.add(topproduct);
            }
            return 1;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
