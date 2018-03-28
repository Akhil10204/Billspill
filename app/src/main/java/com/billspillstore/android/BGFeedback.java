package com.billspillstore.android;

import android.app.AlertDialog;
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

public class BGFeedback extends AsyncTask<String,Void,String> {

    Context context;
    AlertDialog alertDialog;

    public BGFeedback(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        String login_url="http://billspill.com/appfeedback.php";
        String email=params[0];
        String text=params[2];
        String number=params[1];
        String imei=params[3];
        try
        {
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+URLEncoder.encode("number","UTF-8")+"="+URLEncoder.encode(number,"UTF-8")+"&"+URLEncoder.encode("text","UTF-8")+"="+URLEncoder.encode(text,"UTF-8")+"&"+URLEncoder.encode("imei","UTF-8")+"="+URLEncoder.encode(imei,"UTF-8");
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
        alertDialog=new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Thank you");
        alertDialog.setMessage(result);
        alertDialog.show();
    }
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

}
