package com.billspillstore.android;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class FirebaseInstanceService extends FirebaseInstanceIdService {
    public static String promo;
    public static String filename="Mydata";
    public static String Tokenfile="File";
    SharedPreferences preferences,sp;
    @Override
    public void onTokenRefresh() {
        String token= FirebaseInstanceId.getInstance().getToken();
        sp=getSharedPreferences(Tokenfile,0);
        SharedPreferences.Editor edit=sp.edit();
        edit.putString("MyToken",token);
        edit.apply();
        registerToker(token);
        Log.d("firebasetoken: ",token);
    }
    private void registerToker(String token) {
        SecureRandom random=new SecureRandom();
        promo=new BigInteger(40,random).toString(32);
        preferences=getSharedPreferences(filename,0);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("Mypromo",promo);
        editor.apply();
        Log.d("firebasetoken :",promo);
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder().add("Token",token).add("Promo",promo).build();
        Request request = new Request.Builder().url("http://billspill.com/promotoken.php").post(body).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
