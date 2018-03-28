package com.billspillstore.android;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

public class BGPromoCode extends AsyncTask<String,Void,String> {

    Context context;
    public   ProgressDialog pd;



    @Override
    protected void onPreExecute() {
        pd=new ProgressDialog(context);
        pd.setCancelable(true);
        pd.setTitle("Checking info...");
        pd.show();
        super.onPreExecute();
    }

    public BGPromoCode(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        String login_url="http://billspill.com/userinfo.php";
        String mail=params[0];
        String number=params[1];
        String promo=params[2];
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
            String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(mail,"UTF-8")+"&"+URLEncoder.encode("number","UTF-8")+"="+URLEncoder.encode(number,"UTF-8")+"&"+URLEncoder.encode("promo","UTF-8")+"="+URLEncoder.encode(promo,"UTF-8")+"&"+URLEncoder.encode("imei","UTF-8")+"="+URLEncoder.encode(imei,"UTF-8");
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

        if(pd.isShowing()){
            pd.dismiss();

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thank You");
            builder.setMessage(result);
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("Jump to App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Attention").setMessage("Your refer count won't be increased untill you Registered Successfully")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(context.getApplicationContext(),TabbedMainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.getApplicationContext().startActivity(intent);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
            });
            builder.show();

        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Thank You");
            builder.setMessage(result);
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setPositiveButton("Jump to App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Attention").setMessage("Your refer count won't be increased untill you Registered Successfully")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(context.getApplicationContext(),TabbedMainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    context.getApplicationContext().startActivity(intent);
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();


                }
            });
            builder.show();

        }


        super.onPostExecute(result);
    }

}