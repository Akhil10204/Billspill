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

/**
 * Created by Priyam Tyagi on 05-04-2017.
 */

public class Downloader extends AsyncTask<Void,Void,String> {


    Context ctx;
    ListView lv;
    ProgressDialog pd;

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
        if(jsonData==null)
        {

            Toast.makeText(ctx,"Unsuccessfull,No data Retrieved",Toast.LENGTH_SHORT).show();
        }
        else {

            DataParser parser = new DataParser(ctx,jsonData,lv);

            parser.execute();
        }


    }


    private String downloadData(){

        HttpURLConnection con = Connector.connect(urlAddress);
        if(con==null) {
            Toast.makeText(ctx,"Connection is null",Toast.LENGTH_LONG).show();


        }

        try{
            InputStream is = new BufferedInputStream(con.getInputStream());
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

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



    public Downloader(Context ctx, ListView lv, String urlAddress) {
        this.ctx = ctx;
        this.lv = lv;

        this.urlAddress = urlAddress;
    }

    String urlAddress;

    @Override
    protected String doInBackground(Void... params) {
        return downloadData();
    }
}
