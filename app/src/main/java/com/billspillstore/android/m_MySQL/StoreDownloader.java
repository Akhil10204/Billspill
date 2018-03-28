package com.billspillstore.android.m_MySQL;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLEncoder;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class StoreDownloader  extends AsyncTask<String,Void,String>{


    Context ctx;
    ListView lvd;
    ProgressDialog pd;
    String urlAddressd;

    @Override
    protected String doInBackground(String... params) {
        if(!isCancelled()) {

            try {

                String user = params[0];
                String category = params[1];
                HttpURLConnection httpURLConnection = Connector.connect(urlAddressd);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("product_name", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8") + "&" +
                        URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(category, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();


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
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(ctx);
        pd.setCancelable(true);
        pd.setTitle("Loading...");
        pd.setMessage("please wait");
        pd.show();

    }

    @Override
    protected void onPostExecute(String jsonData) {
       if(pd.isShowing()){
        pd.dismiss();}
        if(!isCancelled()){
            if (jsonData == null) {
                Toast.makeText(ctx, "Product not found for other stores", Toast.LENGTH_SHORT).show();
            } else {

                StoreDataParser sparser = new StoreDataParser(ctx, jsonData, lvd);
                sparser.execute();
            }
        }

     /*   if(ExpandedPopupActivity.isviewexist) {
            if (jsonData == null) {
                Toast.makeText(ctx, "Product not found for other stores", Toast.LENGTH_SHORT).show();
            } else {

                StoreDataParser sparser = new StoreDataParser(ctx, jsonData, lvd);

                sparser.execute();
            }
        }*/
    }



    public StoreDownloader(Context ctx, ListView lvd, String urlAddressd) {
        this.ctx = ctx;
        this.lvd = lvd;

        this.urlAddressd = urlAddressd;
    }



}
