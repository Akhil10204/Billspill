package com.billspillstore.android.m_DataObject;

/**
 * Created by Priyam Tyagi on 22-04-2017.
 */

public class StoreDetails {


    String named;
    int rated;
    String iurld;
    String Productlink;
    String seller;
    String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getNamed() {
        return named;
    }

    public String getProductlink() {
        return Productlink;
    }

    public void setProductlink(String productlink) {
        Productlink = productlink;
    }

    public void setNamed(String named) {
        this.named = named;
    }

    public int getRated() {
        return rated;
    }

    public void setRated(int rated) {
        this.rated = rated;
    }

    public String getIurld() {
        return iurld;
    }

    public void setIurld(String iurld) {
        this.iurld = iurld;
    }
}
