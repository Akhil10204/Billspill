package com.billspillstore.android.m_MySQL;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */


public class Connector {




    public static HttpURLConnection connect(String urladdress){

        try {
            URL url=new URL(urladdress);


            HttpURLConnection con= (HttpURLConnection)url.openConnection();

            con.setRequestMethod("GET");
            con.setConnectTimeout(80000);
            con.setReadTimeout(80000);

            con.setDoInput(true);
            return con;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;



    }




}
