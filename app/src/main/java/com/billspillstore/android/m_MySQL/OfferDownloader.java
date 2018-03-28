package com.billspillstore.android.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/**
 * Created by Akhil Saraswat on 19-05-2017.
 */

public class OfferDownloader extends AsyncTask<String,Void,String> {


    Context ctx;
    ListView lv;
    ProgressDialog pd;
   final String urlAddress;

    @Override
    protected String doInBackground(String... params) {
        if(!isCancelled()) {

            try {
                HttpURLConnection httpURLConnection = Connector.connect(urlAddress);
                InputStream is = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer jsonData = new StringBuffer();
                while ((line = br.readLine()) != null) {

                    jsonData.append(line + "/n");
                }
                br.close();
                is.close();
                return jsonData.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return  null;
    }

    public OfferDownloader(Context ctx, ListView lv, String urlAddress) {
        this.ctx = ctx;
        this.lv = lv;
        this.urlAddress = urlAddress;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!isCancelled()) {
            pd = new ProgressDialog(ctx);
            pd.setTitle("Loading...");
            pd.setMessage("please wait");
            pd.show();
        }

    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);
        if(pd.isShowing()){
        pd.dismiss();}
        if(!isCancelled()){
            if (jsonData == null) {

                Toast.makeText(ctx, "Connection Problem, Unable to download offers", Toast.LENGTH_SHORT).show();
            } else {
                OfferParser parser = new OfferParser(ctx,jsonData,lv);
                parser.execute();

            }
        }

    }

}
