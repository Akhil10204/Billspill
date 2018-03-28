package com.billspillstore.android.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.GridView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class GridDownloader extends AsyncTask<String,Void,String> {

    Context ctx;
    GridView lv;
    ProgressDialog pd;
    final String urlAddress;

    @Override
    protected String doInBackground(String... params) {

        try {
            HttpURLConnection httpURLConnection = Connector.connect(urlAddress);
            InputStream is = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer jsonData= new StringBuffer();
            while ((line=br.readLine())!=null)
            {

                jsonData.append(line+"/n");
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

    public GridDownloader(Context ctx, GridView lv, String urlAddress) {
        this.ctx = ctx;
        this.lv = lv;
        this.urlAddress = urlAddress;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(ctx);
        pd.setTitle("Retrive");
        pd.setMessage("Retrieving....please wait");
        pd.show();

    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute(jsonData);
        pd.dismiss();
        if (jsonData == null) {

            Toast.makeText(ctx, "Connection Problem, unable to download Products", Toast.LENGTH_SHORT).show();
        } else {


            GridParser parser = new GridParser(ctx,jsonData,lv);
          //  OfferParser parser = new OfferParser(ctx,jsonData,lv);
           parser.execute();

        }
    }







}
