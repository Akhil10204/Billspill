package com.billspillstore.android.m_MySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ListView;

import com.billspillstore.android.CustomizedPopupActivity;
import com.billspillstore.android.PopupListActivity;

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

public class CompareDownloader extends AsyncTask<String,Void,String> {
    Context ctx;
    ListView lvcompare;
  public ProgressDialog pd;

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
            pd.dismiss();
        }
        if(!isCancelled()){
            if (jsonData == null || jsonData.equals("[]/n")) {

                Intent j = new Intent(ctx, CustomizedPopupActivity.class);
                ctx.startActivity(j);
            }
            else{
                PopupListActivity.titlepage = "Variants Found";
                CompareDataParser parser = new CompareDataParser(ctx, jsonData, lvcompare);
                parser.execute();
            }
        }

      /*  if(PopupListActivity.isviewexist) {
            if (jsonData == null || jsonData.equals("[]/n")) {

                Intent j = new Intent(ctx, CustomizedPopupActivity.class);
                ctx.startActivity(j);
                ((Activity) ctx).finish();
                Toast.makeText(ctx,"No Variant Found",Toast.LENGTH_SHORT).show();
            } else {
                PopupListActivity.titlepage = "Variants Found";
                CompareDataParser parser = new CompareDataParser(ctx, jsonData, lvcompare);
                Log.d("LOGTAG", jsonData);
                parser.execute();
            }
        }
        try {
            pd.dismiss();
        }
        catch (IllegalArgumentException e){

        }
        catch(NullPointerException e){

        }*/

    }


    String urlAddressed;
    public CompareDownloader(Context ctx, ListView lvcompare, String urlAddressed) {
        this.ctx = ctx;
        this.lvcompare = lvcompare;

        this.urlAddressed = urlAddressed;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(String... params) {
        if(!isCancelled()) {
            try {

                String product = params[0];
                String app = params[1];
                HttpURLConnection httpURLConnection = Connector.connect(urlAddressed);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("product_name", "UTF-8") + "=" + URLEncoder.encode(product, "UTF-8") + "&" +
                        URLEncoder.encode("app_name", "UTF-8") + "=" + URLEncoder.encode(app, "UTF-8");
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


}
