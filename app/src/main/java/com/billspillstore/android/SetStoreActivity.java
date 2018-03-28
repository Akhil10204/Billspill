package com.billspillstore.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class SetStoreActivity extends Activity {
Spinner spinner1,spinner2,spinner3,spinner4;
    ArrayAdapter<CharSequence> adapter1,adapter2,adapter3,adapter4;
    Button button;
    public static String condition="0";

    SharedPreferences preferences;
    String token,value="ChooseStore";
    public static String Tokenfile="File";
FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_store);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mFirebaseAnalytics= FirebaseAnalytics.getInstance(SetStoreActivity.this);
        Bundle params=new Bundle();
        params.putInt("ActivityOpenSetSt",11);
        String status="ChooseStores_Activity_Open";
        Log.d("Status","user_opened_activity");
        mFirebaseAnalytics.logEvent(status,params);

        preferences=getSharedPreferences(Tokenfile,0);
        token=preferences.getString("MyToken","updated_token");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("Token",token).add("Interest",value).build();
        Request request = new Request.Builder().url("http://billspill.com/interested_token.php").post(body).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        spinner1=(Spinner)findViewById(R.id.spinner_1);
        spinner2=(Spinner)findViewById(R.id.spinner_2);
        spinner3=(Spinner)findViewById(R.id.spinner_3);
        spinner4=(Spinner)findViewById(R.id.spinner_4);
        button=(Button)findViewById(R.id.spinner_button);

        adapter1=ArrayAdapter.createFromResource(this,R.array.storedownone,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);

        adapter2=ArrayAdapter.createFromResource(this,R.array.storedowntwo,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        adapter3=ArrayAdapter.createFromResource(this,R.array.storedownthree,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        adapter4=ArrayAdapter.createFromResource(this,R.array.storedownfour,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner4.setAdapter(adapter4);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                condition="1";
                String input1=spinner1.getSelectedItem().toString();
                String input2=spinner2.getSelectedItem().toString();
                String input3=spinner3.getSelectedItem().toString();
                String input4=spinner4.getSelectedItem().toString();
                Intent intent=new Intent(getApplicationContext(),TabbedStoreResult.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("INPUT1",input1);
                intent.putExtra("INPUT2",input2);
                intent.putExtra("INPUT3",input3);
                intent.putExtra("INPUT4",input4);
                getApplicationContext().startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        this.finish();
        super.onPause();
    }
}
