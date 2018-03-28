package com.billspillstore.android;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.billspillstore.android.Fragments_category.Amazon;
import com.billspillstore.android.Fragments_category.Ebay;
import com.billspillstore.android.Fragments_category.Faballey;
import com.billspillstore.android.Fragments_category.Flipkart;
import com.billspillstore.android.Fragments_category.Homeshop;
import com.billspillstore.android.Fragments_category.Industrybuying;
import com.billspillstore.android.Fragments_category.Jabong;
import com.billspillstore.android.Fragments_category.MobileAdapter;
import com.billspillstore.android.Fragments_category.Myntra;
import com.billspillstore.android.Fragments_category.Paytm;
import com.billspillstore.android.Fragments_category.PaytmMall;
import com.billspillstore.android.Fragments_category.Printvenue;
import com.billspillstore.android.Fragments_category.Shopclues;
import com.billspillstore.android.Fragments_category.Snapdeal;
import com.billspillstore.android.Fragments_category.Stalkbylove;
import com.billspillstore.android.Fragments_category.TataCliq;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class TabbedStoreResult extends AppCompatActivity {
    String input1,input2,input3,input4;

    SharedPreferences preferences;
    String token,value="StoreChoosen";
    public static String Tokenfile="File";
    FirebaseAnalytics mFirebaseAnalytics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_store_result);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mFirebaseAnalytics= FirebaseAnalytics.getInstance(TabbedStoreResult.this);
        Bundle params=new Bundle();
        params.putInt("ActivityOpenChsn",10);
        String status="StoresChoosen_Activity_Open";
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
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        ViewPager pager= (ViewPager) findViewById(R.id.container);
        if(!isOnline()){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("No Network Connectivity");
            builder.setMessage("Check your internet connection or try again later");
            builder.setCancelable(true);
            builder.show();
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        String type=SearchSetStore.code;

        if(type.equals("0")) {
            Intent i = this.getIntent();
            input1 = i.getExtras().getString("INPUT1");
            input2 = i.getExtras().getString("INPUT2");
            input3 = i.getExtras().getString("INPUT3");
            input4 = i.getExtras().getString("INPUT4");
            tabLayout.setupWithViewPager(pager);
            setupViewpager(pager, input1, input2, input3, input4);
        }


        if(type.equals("1")) {
            Intent in = this.getIntent();

            String input = in.getExtras().getString("INPUT");
            input1=in.getExtras().getString("Input1");
            input2=in.getExtras().getString("Input2");
            input3=in.getExtras().getString("Input3");
            input4=in.getExtras().getString("Input4");
            if(input!=null){
               switch (input1){
                   case "Amazon":
                       Amazon amazon=new Amazon();
                       amazon.getInput(input);
                       break;
                   case "Flipkart":
                       Flipkart flipkart=new Flipkart();
                       flipkart.getInput(input);
                       break;
                   case "Paytm":
                       Paytm paytm=new Paytm();
                       paytm.getInput(input);
                       break;
                   case "Snapdeal":
                       Snapdeal snapdeal=new Snapdeal();
                       snapdeal.getInput(input);
                       break;
                   case "Myntra":
                       Myntra myntra=new Myntra();
                       myntra.getInput(input);
                       break;
                   case "Jabong":
                       Jabong jabong=new Jabong();
                       jabong.getInput(input);
                       break;
                   case "Stalk By Love":
                       Stalkbylove sbl=new Stalkbylove();
                       sbl.getInput(input);
                       break;
                   case "Faballey":
                       Faballey faballey=new Faballey();
                       faballey.getInput(input);
                       break;
                   case "Ebay":
                       Ebay ebay=new Ebay();
                       ebay.getInput(input);
                       break;
                   case "TataCliq":
                       TataCliq tataCliq=new TataCliq();
                       tataCliq.getInput(input);
                       break;
                   case "Paytm Mall":
                       PaytmMall paytmMall=new PaytmMall();
                       paytmMall.getInput(input);
                       break;
                   case "HomeShop18":
                       Homeshop homeshop=new Homeshop();
                       homeshop.getInput(input);
                       break;
                   case "ShopClues":
                       Shopclues shopclues=new Shopclues();
                       shopclues.getInput(input);
                       break;
                   case "PrintVenue":
                       Printvenue printvenue=new Printvenue();
                       printvenue.getInput(input);
                       break;
                   case "Industry Buying":
                       Industrybuying industrybuying=new Industrybuying();
                       industrybuying.getInput(input);
                       break;
                       default: break;
               }

                switch (input2){
                    case "Amazon":
                        Amazon amazon=new Amazon();
                        amazon.getInput(input);
                        break;
                    case "Flipkart":
                        Flipkart flipkart=new Flipkart();
                        flipkart.getInput(input);
                        break;
                    case "Paytm":
                        Paytm paytm=new Paytm();
                        paytm.getInput(input);
                        break;
                    case "Snapdeal":
                        Snapdeal snapdeal=new Snapdeal();
                        snapdeal.getInput(input);
                        break;
                    case "Myntra":
                        Myntra myntra=new Myntra();
                        myntra.getInput(input);
                        break;
                    case "Jabong":
                        Jabong jabong=new Jabong();
                        jabong.getInput(input);
                        break;
                    case "Stalk By Love":
                        Stalkbylove sbl=new Stalkbylove();
                        sbl.getInput(input);
                        break;
                    case "Faballey":
                        Faballey faballey=new Faballey();
                        faballey.getInput(input);
                        break;
                    case "Ebay":
                        Ebay ebay=new Ebay();
                        ebay.getInput(input);
                        break;
                    case "TataCliq":
                        TataCliq tataCliq=new TataCliq();
                        tataCliq.getInput(input);
                        break;
                    case "Paytm Mall":
                        PaytmMall paytmMall=new PaytmMall();
                        paytmMall.getInput(input);
                        break;
                    case "HomeShop18":
                        Homeshop homeshop=new Homeshop();
                        homeshop.getInput(input);
                        break;
                    case "ShopClues":
                        Shopclues shopclues=new Shopclues();
                        shopclues.getInput(input);
                        break;
                    case "PrintVenue":
                        Printvenue printvenue=new Printvenue();
                        printvenue.getInput(input);
                        break;
                    case "Industry Buying":
                        Industrybuying industrybuying=new Industrybuying();
                        industrybuying.getInput(input);
                        break;
                    default: break;
                }
                switch (input3){
                    case "Amazon":
                        Amazon amazon=new Amazon();
                        amazon.getInput(input);
                        break;
                    case "Flipkart":
                        Flipkart flipkart=new Flipkart();
                        flipkart.getInput(input);
                        break;
                    case "Paytm":
                        Paytm paytm=new Paytm();
                        paytm.getInput(input);
                        break;
                    case "Snapdeal":
                        Snapdeal snapdeal=new Snapdeal();
                        snapdeal.getInput(input);
                        break;
                    case "Myntra":
                        Myntra myntra=new Myntra();
                        myntra.getInput(input);
                        break;
                    case "Jabong":
                        Jabong jabong=new Jabong();
                        jabong.getInput(input);
                        break;
                    case "Stalk By Love":
                        Stalkbylove sbl=new Stalkbylove();
                        sbl.getInput(input);
                        break;
                    case "Faballey":
                        Faballey faballey=new Faballey();
                        faballey.getInput(input);
                        break;
                    case "Ebay":
                        Ebay ebay=new Ebay();
                        ebay.getInput(input);
                        break;
                    case "TataCliq":
                        TataCliq tataCliq=new TataCliq();
                        tataCliq.getInput(input);
                        break;
                    case "Paytm Mall":
                        PaytmMall paytmMall=new PaytmMall();
                        paytmMall.getInput(input);
                        break;
                    case "HomeShop18":
                        Homeshop homeshop=new Homeshop();
                        homeshop.getInput(input);
                        break;
                    case "ShopClues":
                        Shopclues shopclues=new Shopclues();
                        shopclues.getInput(input);
                        break;
                    case "PrintVenue":
                        Printvenue printvenue=new Printvenue();
                        printvenue.getInput(input);
                        break;
                    case "Industry Buying":
                        Industrybuying industrybuying=new Industrybuying();
                        industrybuying.getInput(input);
                        break;
                    default: break;
                }
                switch (input4){
                    case "Amazon":
                        Amazon amazon=new Amazon();
                        amazon.getInput(input);
                        break;
                    case "Flipkart":
                        Flipkart flipkart=new Flipkart();
                        flipkart.getInput(input);
                        break;
                    case "Paytm":
                        Paytm paytm=new Paytm();
                        paytm.getInput(input);
                        break;
                    case "Snapdeal":
                        Snapdeal snapdeal=new Snapdeal();
                        snapdeal.getInput(input);
                        break;
                    case "Myntra":
                        Myntra myntra=new Myntra();
                        myntra.getInput(input);
                        break;
                    case "Jabong":
                        Jabong jabong=new Jabong();
                        jabong.getInput(input);
                        break;
                    case "Stalk By Love":
                        Stalkbylove sbl=new Stalkbylove();
                        sbl.getInput(input);
                        break;
                    case "Faballey":
                        Faballey faballey=new Faballey();
                        faballey.getInput(input);
                        break;
                    case "Ebay":
                        Ebay ebay=new Ebay();
                        ebay.getInput(input);
                        break;
                    case "TataCliq":
                        TataCliq tataCliq=new TataCliq();
                        tataCliq.getInput(input);
                        break;
                    case "Paytm Mall":
                        PaytmMall paytmMall=new PaytmMall();
                        paytmMall.getInput(input);
                        break;
                    case "HomeShop18":
                        Homeshop homeshop=new Homeshop();
                        homeshop.getInput(input);
                        break;
                    case "ShopClues":
                        Shopclues shopclues=new Shopclues();
                        shopclues.getInput(input);
                        break;
                    case "PrintVenue":
                        Printvenue printvenue=new Printvenue();
                        printvenue.getInput(input);
                        break;
                    case "Industry Buying":
                        Industrybuying industrybuying=new Industrybuying();
                        industrybuying.getInput(input);
                        break;
                    default: break;
                }
            }

            tabLayout.setupWithViewPager(pager);
            setupViewpager(pager, input1, input2, input3, input4);
        }
       // Toast.makeText(getApplicationContext(),"input ::"+input1+" "+input2+" "+input3+" "+input4,Toast.LENGTH_LONG).show();
    }
    public void setupViewpager(ViewPager viewPager,String st1,String st2,String st3,String st4){
        MobileAdapter adapter=new MobileAdapter(getSupportFragmentManager());
        if(true) {
            if (st1.equals("Amazon")) {
                adapter.addFragment(new Amazon(), "Amazon");
            }
            if (st1.equals("Ebay")) {
                adapter.addFragment(new Ebay(), "Ebay");
            }
            if (st1.equals("Paytm")) {
                adapter.addFragment(new Paytm(), "Paytm");
            }
            if (st1.equals("Snapdeal")) {
                adapter.addFragment(new Snapdeal(), "Snapdeal");
            }
            if (st1.equals("Paytm Mall")) {
                adapter.addFragment(new PaytmMall(), "PaytmMall");
            }
            if (st1.equals("Stalk By Love")) {
                adapter.addFragment(new Stalkbylove(), "SBL");
            }
            if (st1.equals("Faballey")) {
                adapter.addFragment(new Faballey(), "Faballey");
            }
            if (st1.equals("Myntra")) {
                adapter.addFragment(new Myntra(), "Myntra");
            }
            if (st1.equals("Jabong")) {
                adapter.addFragment(new Jabong(), "Jabong");
            }
            if (st1.equals("HomeShop18")) {
                adapter.addFragment(new Homeshop(), "HS18");
            }
            if (st1.equals("ShopClues")) {
                adapter.addFragment(new Shopclues(), "ShopClues");
            }
            if (st1.equals("TataCliq")) {
                adapter.addFragment(new TataCliq(), "TataCliq");
            }
            if (st1.equals("Flipkart")) {
                adapter.addFragment(new Flipkart(), "Flipkart");
            }
            if (st1.equals("Industry Buying")) {
                adapter.addFragment(new Industrybuying(), "IBuying");
            }
            if (st1.equals("PrintVenue")) {
                adapter.addFragment(new Printvenue(), "PVenue");
            }
            if (st2.equals("Amazon")) {
                adapter.addFragment(new Amazon(), "Amazon");
            }
            if (st2.equals("Ebay")) {
                adapter.addFragment(new Ebay(), "Ebay");
            }
            if (st2.equals("Paytm")) {
                adapter.addFragment(new Paytm(), "Paytm");
            }
            if (st2.equals("Snapdeal")) {
                adapter.addFragment(new Snapdeal(), "Snapdeal");
            }
            if (st2.equals("Paytm Mall")) {
                adapter.addFragment(new PaytmMall(), "PaytmMall");
            }
            if (st2.equals("Stalk Buy Love")) {
                adapter.addFragment(new Stalkbylove(), "SBL");
            }
            if (st2.equals("Faballey")) {
                adapter.addFragment(new Faballey(), "Faballey");
            }
            if (st2.equals("Myntra")) {
                adapter.addFragment(new Myntra(), "Myntra");
            }
            if (st2.equals("Jabong")) {
                adapter.addFragment(new Jabong(), "Jabong");
            }
            if (st2.equals("HomeShop18")) {
                adapter.addFragment(new Homeshop(), "HS18");
            }
            if (st2.equals("ShopClues")) {
                adapter.addFragment(new Shopclues(), "ShopClues");
            }
            if (st2.equals("TataCliq")) {
                adapter.addFragment(new TataCliq(), "TataCliq");
            }
            if (st2.equals("Flipkart")) {
                adapter.addFragment(new Flipkart(), "Flipkart");
            }
            if (st2.equals("Industry Buying")) {
                adapter.addFragment(new Industrybuying(), "IBuying");
            }
            if (st2.equals("PrintVenue")) {
                adapter.addFragment(new Printvenue(), "PVenue");
            }
            if (st3.equals("Amazon")) {
                adapter.addFragment(new Amazon(), "Amazon");
            }
            if (st3.equals("Ebay")) {
                adapter.addFragment(new Ebay(), "Ebay");
            }
            if (st3.equals("Paytm")) {
                adapter.addFragment(new Paytm(), "Paytm");
            }
            if (st3.equals("Snapdeal")) {
                adapter.addFragment(new Snapdeal(), "Snapdeal");
            }
            if (st3.equals("Paytm Mall")) {
                adapter.addFragment(new PaytmMall(), "PaytmMall");
            }
            if (st3.equals("Stalk By Love")) {
                adapter.addFragment(new Stalkbylove(), "SBL");
            }
            if (st3.equals("Faballey")) {
                adapter.addFragment(new Faballey(), "Faballey");
            }
            if (st3.equals("Myntra")) {
                adapter.addFragment(new Myntra(), "Myntra");
            }
            if (st3.equals("Jabong")) {
                adapter.addFragment(new Jabong(), "Jabong");
            }
            if (st3.equals("HomeShop18")) {
                adapter.addFragment(new Homeshop(), "HS18");
            }
            if (st3.equals("ShopClues")) {
                adapter.addFragment(new Shopclues(), "ShopClues");
            }
            if (st3.equals("TataCliq")) {
                adapter.addFragment(new TataCliq(), "TataCliq");
            }
            if (st3.equals("Flipkart")) {
                adapter.addFragment(new Flipkart(), "Flipkart");
            }
            if (st3.equals("Industry Buying")) {
                adapter.addFragment(new Industrybuying(), "IBuying");
            }
            if (st3.equals("PrintVenue")) {
                adapter.addFragment(new Printvenue(), "PVenue");
            }
            if (st4.equals("Amazon")) {
                adapter.addFragment(new Amazon(), "Amazon");
            }
            if (st4.equals("Ebay")) {
                adapter.addFragment(new Ebay(), "Ebay");
            }
            if (st4.equals("Paytm")) {
                adapter.addFragment(new Paytm(), "Paytm");
            }
            if (st4.equals("Snapdeal")) {
                adapter.addFragment(new Snapdeal(), "Snapdeal");
            }
            if (st4.equals("Paytm Mall")) {
                adapter.addFragment(new PaytmMall(), "PaytmMall");
            }
            if (st4.equals("Stalk By Love")) {
                adapter.addFragment(new Stalkbylove(), "SBL");
            }
            if (st4.equals("Faballey")) {
                adapter.addFragment(new Faballey(), "Faballey");
            }
            if (st4.equals("Myntra")) {
                adapter.addFragment(new Myntra(), "Myntra");
            }
            if (st4.equals("Jabong")) {
                adapter.addFragment(new Jabong(), "Jabong");
            }
            if (st4.equals("HomeShop18")) {
                adapter.addFragment(new Homeshop(), "HS18");
            }
            if (st4.equals("ShopClues")) {
                adapter.addFragment(new Shopclues(), "ShopClues");
            }
            if (st4.equals("TataCliq")) {
                adapter.addFragment(new TataCliq(), "TataCliq");
            }
            if (st4.equals("Flipkart")) {
                adapter.addFragment(new Flipkart(), "Flipkart");
            }
            if (st4.equals("Industry Buying")) {
                adapter.addFragment(new Industrybuying(), "IBuying");
            }
            if (st4.equals("PrintVenue")) {
                adapter.addFragment(new Printvenue(), "PVenue");
            }
            viewPager.setAdapter(adapter);
            viewPager.setOffscreenPageLimit(4);
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabbed_store_result, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.search) {
            Intent j = new Intent(this,SearchSetStore.class);
            j.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            j.putExtra("I1",input1);
            j.putExtra("I2",input2);
            j.putExtra("I3",input3);
            j.putExtra("I4",input4);
            this.startActivity(j);



            return true;
        }

        if(id==R.id.menu_offer){
            Intent j=new Intent(this,MenuOffers.class);
            startActivity(j);
        }
        if(id==R.id.menu_products){
            Intent k=new Intent(this,MenuProducts.class);
            startActivity(k);
        }
        if(id==R.id.menu_feedback){
            Intent m=new Intent(this,MenuFeedback.class);
            startActivity(m);
        }
        if(id==R.id.menu_rateus){


            try {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("market://details?id=com.billspillstore.android"));
                startActivity(viewIntent);
            }catch(Exception e) {
                Toast.makeText(getApplicationContext(),"Unable to Connect Try Again...",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

        }
        if(id==R.id.menu_support){
            Intent p=new Intent(this,SupportActivity.class);
            startActivity(p);
        }
        if(id==R.id.menu_refer){
            Intent p=new Intent(this,PromoShareActivity.class);
            startActivity(p);
        }

        if(id==R.id.menu_about){
            Intent p=new Intent(this,MenuAboutus.class);
            startActivity(p);
        }
        if(id==R.id.menu_blog){
            Intent loadurl =  new Intent(this,BlogWebActivity.class);
            startActivity(loadurl);
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    public boolean isOnline(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        return networkInfo!=null;
    }
}
