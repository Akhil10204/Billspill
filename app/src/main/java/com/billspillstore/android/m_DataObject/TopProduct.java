package com.billspillstore.android.m_DataObject;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class TopProduct {

    String Topname,Topseller,Topurl,Topimage,Toprate;
     ;

    public String getTopname() {
        return Topname;
    }

    public String getTopurl() {
        return Topurl;
    }

    public void setTopurl(String topurl) {
        Topurl = topurl;
    }

    public void setTopname(String topname) {
        Topname = topname;
    }

    public String getToprate() {
        return Toprate;
    }

    public void setToprate(String toprate) {
        Toprate = toprate;
    }

    public String getTopseller() {
        return Topseller;
    }

    public void setTopseller(String topseller) {
        Topseller = topseller;
    }

    public String getTopimage() {
        return Topimage;
    }

    public void setTopimage(String topimage) {
        Topimage = topimage;
    }
}
