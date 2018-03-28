package com.billspillstore.android;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class AvailOfferActivity extends Activity {

    EditText mail;
    String mailtext;
    Button submit;
    String httpresponse;
    public static String IMEIno="NUMBER";
    SharedPreferences preferences;
    String imei;
    String update="available";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avail_offer);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        mail=(EditText)findViewById(R.id.mail);
        submit=(Button)findViewById(R.id.submit);
        preferences=getSharedPreferences(IMEIno, Context.MODE_PRIVATE);
        imei=preferences.getString("IMEI","0");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mail.getText().length()==10){
                    mailtext=mail.getText().toString();
                    OkHttpClient client = new OkHttpClient();
                    RequestBody body = new FormBody.Builder().add("number",mailtext).add("imei",imei).add("version",update).build();
                    Request request = new Request.Builder().url("http://billspill.com/paytmoffer.php").post(body).build();
                    mail.setText("");
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            httpresponse=response.body().string();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AlertDialog.Builder builder= new AlertDialog.Builder(AvailOfferActivity.this);
                                    builder.setTitle("Thank You");
                                    builder.setMessage(httpresponse.trim());
                                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                            AvailOfferActivity.this.finish();
                                        }
                                    });
                                    builder.create();
                                    builder.show();
                                }
                            });



                        }
                    });





                }
                else{
                    emailnotadded();
                }
            }
        });
    }
    public void emailnotadded(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid email address");
        builder.setMessage("Please enter valid email address");
        builder.show();
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });


    }

}
