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

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertDialog;

    public BackgroundWorker(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String login_url="http://billspill.com/notifyme.php";
        String emailaddress=params[0];
        String pdtname=params[1];
        String pdtrate=params[2];
        String when = params[3];
        String store = params[4];
        try {
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream= httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(emailaddress,"UTF-8")+"&"+URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(pdtname,"UTF-8")+"&"+URLEncoder.encode("rate","UTF-8")+"="+URLEncoder.encode(pdtrate,"UTF-8")+"&"+URLEncoder.encode("when","UTF-8")+"="+URLEncoder.encode(when,"UTF-8")+"&"+URLEncoder.encode("store","UTF-8")+"="+URLEncoder.encode(store,"UTF-8");
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
