package com.billspillstore.android.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.billspillstore.android.m_DataObject.ComparePrice;
import com.billspillstore.android.m_DataObject.Coupon;
import com.billspillstore.android.m_UI.CompareListAdapter;
import com.billspillstore.android.m_UI.CouponListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Priyam Tyagi on 22-05-2017.
 */

public class CouponParser extends AsyncTask<Void,Void,Integer> {
    Context ctx;
    String jsonData;
    ListView lvcompare;

    ProgressDialog pd;
    ArrayList<Coupon> coupon = new ArrayList<>();
    public CouponParser(Context ctx, String jsonData, ListView lvcompare) {
        this.ctx = ctx;
        this.jsonData = jsonData;
        this.lvcompare = lvcompare;

    }




    @Override
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
            CouponListAdapter adapter = new CouponListAdapter(ctx,coupon);
           // CompareListAdapter adapter=new CompareListAdapter(ctx,comparePrices);
           lvcompare.setAdapter(adapter);
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
            coupon.clear();
            Coupon coupons;

            for (int i=0;i<ja.length();i++)
            {

                jo = ja.getJSONObject(i) ;


                String detail=jo.getString("title");
                String code=jo.getString("code");
               // String company=jo.getString("company");

               coupons =new Coupon();

                coupons.setDetail(detail);
                coupons.setCode(code);
               // coupons.setCompany(company);



                coupon.add(coupons);

            }
            return 1;




        } catch (JSONException e) {
            e.printStackTrace();
        }


        return 0;
    }
}
