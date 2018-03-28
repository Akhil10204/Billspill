package com.billspillstore.android;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class BGInstallation extends AsyncTask<String,Void,String> {

    Context context;

    public  static String res="0";


    public BGInstallation(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String login_url="http://billspill.com/installcount.php";
        String reffered=params[0];
        try
        {
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("Reffered","UTF-8")+"="+URLEncoder.encode(reffered,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result = "";
            String line = "";
            while ((line=bufferedReader.readLine())!=null) {

                result +=line;

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        res=result;
        response();
        super.onPostExecute(result);
    }
    public static  String response(){
        return res;
    }
}