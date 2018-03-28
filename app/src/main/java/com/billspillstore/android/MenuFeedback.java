package com.billspillstore.android;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class MenuFeedback extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback{
  EditText mail,text,mobile;
    Button send;
    String IMEI;
    String email,content,number;
    TelephonyManager telephonyManager;
    public static final int REQUEST_READ_PHONE_STATE= 999;
FirebaseAnalytics mFirebaseAnalytics;
    public final  boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_feedback);

        mFirebaseAnalytics= FirebaseAnalytics.getInstance(MenuFeedback.this);
        Bundle params=new Bundle();
        params.putInt("ActivityOpenFeed",4);
        String status="Feedback_Activity_Open";
        Log.d("Status","user_opened_activity");
        mFirebaseAnalytics.logEvent(status,params);


        mail=(EditText)findViewById(R.id.feedback_mail);
        text=(EditText)findViewById(R.id.feedback_text);
        mobile=(EditText)findViewById(R.id.feedback_num);
        send=(Button)findViewById(R.id.feedback_btn);

        int permissionCheckone = ContextCompat.checkSelfPermission(getApplicationContext(),
                android.Manifest.permission.READ_PHONE_STATE);
        if (permissionCheckone != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MenuFeedback.this,
                    new String[]{android.Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
        }
        else{

            telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager != null) {
                IMEI = telephonyManager.getDeviceId();
            }
            if (IMEI == null || IMEI.length() == 0) {
                IMEI = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            if(IMEI.length()>7){
                //post imei and email
                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(isValidEmail(mail.getText())&&text.getText().length()>19 && mobile.getText().length()==10){

                            email=mail.getText().toString();
                            content=text.getText().toString();
                            number=mobile.getText().toString();

                            //Toast.makeText(getApplicationContext(),"details :"+email+" "+number+" "+content,Toast.LENGTH_LONG).show();
                            BGFeedback bgFeedback=new BGFeedback(MenuFeedback.this);
                            bgFeedback.execute(email,number,content,IMEI);

                            mail.setText("");
                            mobile.setText("");
                            text.setText("");


                        }
                        else if(text.getText().length()<=19){
                            textnotadded();
                        }
                        else if(!isValidEmail(mail.getText())){
                            emailnotadded();
                        }
                        else{
                            numbernotadded();
                        }



                    }
                });

            }
            else{

                MenuFeedback.this.finish();
                //close activity
            }
        }



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_READ_PHONE_STATE){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                if (telephonyManager != null) {
                    IMEI = telephonyManager.getDeviceId();
                }
                if (IMEI == null || IMEI.length() == 0) {
                    IMEI = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                }
                if(IMEI.length()>7){
                    //post imei and email
                    send.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(isValidEmail(mail.getText())&&text.getText().length()>19 && mobile.getText().length()==10){

                                email=mail.getText().toString();
                                content=text.getText().toString();
                                number=mobile.getText().toString();

                                //Toast.makeText(getApplicationContext(),"details :"+email+" "+number+" "+content,Toast.LENGTH_LONG).show();
                                BGFeedback bgFeedback=new BGFeedback(MenuFeedback.this);
                                bgFeedback.execute(email,number,content,IMEI);

                                mail.setText("");
                                mobile.setText("");
                                text.setText("");


                            }
                            else if(text.getText().length()<=19){
                                textnotadded();
                            }
                            else if(!isValidEmail(mail.getText())){
                                emailnotadded();
                            }
                            else{
                                numbernotadded();
                            }



                        }
                    });

                }
                else{
                    MenuFeedback.this.finish();
                }
            }





        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
    public void textnotadded(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Attention!!!");
        builder.setMessage("Enter atleast twenty words");
        builder.show();
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });
    }
    public void numbernotadded(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Attention!!!");
        builder.setMessage("Enter valid mobile number");
        builder.show();
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });
    }

    @Override
    protected void onPause() {
        this.finish();
        super.onPause();
    }
}
