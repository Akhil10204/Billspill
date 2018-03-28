package com.billspillstore.android;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class SupportActivity extends AppCompatActivity {

    EditText name, mail, query;
    Spinner spinner;
    Button submit;
    String nametext, mailtext, querytext, spinnertext;
    ArrayAdapter<CharSequence> adapter;

    public final boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        name = (EditText) findViewById(R.id.name);
        mail = (EditText) findViewById(R.id.mail);
        query = (EditText) findViewById(R.id.query);
        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(SupportActivity.this,R.array.querymenu,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidEmail(mail.getText()) && name.getText().length() >= 3 && !spinner.getSelectedItem().toString().trim().equals("Select your issue")) {
                    nametext = name.getText().toString();
                    mailtext = mail.getText().toString();
                    querytext = query.getText().toString();
                    spinnertext = spinner.getSelectedItem().toString();

                    OkHttpClient client = new OkHttpClient();
                    RequestBody body = new FormBody.Builder().add("name", nametext).add("email", mailtext).add("type", spinnertext).add("query", querytext).build();
                    Request request = new Request.Builder().url("http://billspill.com/support.php").post(body).build();
                    try {
                        client.newCall(request).execute();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mail.setText("");
                    name.setText("");
                    query.setText("");
                    emailadded();


                } else if (!isValidEmail(mail.getText())) {
                    emailnotadded();
                }
                else if(spinner.getSelectedItem().toString().trim().equals("Select your issue")){
                       typenotadded();
                }
                else {
                    namenotadded();
                }
            }
        });

    }

    public void emailnotadded() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SupportActivity.this);
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

    public void namenotadded() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SupportActivity.this);
        builder.setTitle("Name too Short");
        builder.setMessage("Please enter your full name");
        builder.show();
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });


    }
    public void typenotadded() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SupportActivity.this);
        builder.setTitle("No Issue Selected");
        builder.setMessage("Select an issue according to your request");
        builder.show();
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });


    }

    public void emailadded() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SupportActivity.this);
        builder.setTitle("Successfully Submitted");
        builder.setMessage("We will get back to you shortly");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                SupportActivity.this.finish();

            }
        });
        builder.create();
        builder.show();
    }
}