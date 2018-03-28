package com.billspillstore.android;
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;
import java.util.Locale;

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

public class PromoActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback
{
    public static String IMEIno="NUMBER";
    public static String checks="Check";
    public static String filename="Mydata";
    public static String file="Mynewcode";
    SharedPreferences preferences,prefs;
    public static String Oldpromo="OLDPROMO";
    SharedPreferences sharedPreferences,shared,sp;
    String HttpResponse;
    SharedPreferences againshared;
    String token;
    public static String Tokenfile="File";
    TelephonyManager telephonyManager;
    public static final int REQUEST_READ_PHONE_STATE= 999;
    public static String IMEI ;
    EditText mail,number,code,name;
    String email,contact,fullname;
    Button register,skip;
    String pro_code;
    String mypromo;
    ProgressDialog pd;
    String im="1000";
    GPSTracker gpsTracker;
    private FirebaseAnalytics mFirebaseAnalytics;
    public final  boolean isValidEmail(CharSequence target)
    {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
        }

        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((Build.VERSION.SDK_INT < 21))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(PromoActivity.this);
            builder.setCancelable(false);
            builder.setTitle(" Sorry !!! Incompatible Device");
            builder.setMessage("Your device is not compatible with this application");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    PromoActivity.this.finish();
                    dialog.dismiss();
                }
            });
            builder.create();
            builder.show();
        }
        else {
            setContentView(R.layout.activity_promo);
            if (appInstalledOrNot("com.lbe.parallel.intl") || appInstalledOrNot("com.excelliance.multiaccount") || appInstalledOrNot("com.applisto.appcloner")|| appInstalledOrNot("com.cmcm.multiaccount")|| appInstalledOrNot("com.jack.appclonemultiaccount")|| appInstalledOrNot("com.jiubang.commerce.gomultiple")|| appInstalledOrNot("com.in.parallel.accounts")|| appInstalledOrNot("com.parallel.space.lite")|| appInstalledOrNot("com.excelliance.multiaccounts")|| appInstalledOrNot("com.polestar.multiaccount")|| appInstalledOrNot("com.phantom.gemini")|| appInstalledOrNot("com.in.parallel.accounts")|| appInstalledOrNot("com.lbe.parallel.intl.arm64")|| appInstalledOrNot("com.parallel.space.lite.arm64")|| appInstalledOrNot("parallel.multi.accounts.space")|| appInstalledOrNot("com.parallel.multi.account.dualsim")|| appInstalledOrNot("dotcode.parallel")|| appInstalledOrNot("com.parallel.appclonemultiaccount")|| appInstalledOrNot("com.whatsdual.fcuk")|| appInstalledOrNot("paralel.spaceguide.para")|| appInstalledOrNot("com.reny.securespaceaccount")|| appInstalledOrNot("com.applock.fast.switch.multiple.accounts")|| appInstalledOrNot("com.hola.multiaccount")|| appInstalledOrNot("cam.paralellmulti.accspace")|| appInstalledOrNot("com.spaceplus.we")|| appInstalledOrNot("com.multimessenger.faaad1.app")|| appInstalledOrNot("com.multiaccount.twins")|| appInstalledOrNot("com.parallelbox")|| appInstalledOrNot("com.trigtech.privateme")|| appInstalledOrNot("a2ndappwhats.sdkw.com")|| appInstalledOrNot("com.guide.watsappppa")|| appInstalledOrNot("com.app.tiki.multimessenger")|| appInstalledOrNot("com.nutsmobi.parallel")|| appInstalledOrNot("com.multiacc.dualaccou")|| appInstalledOrNot("com.hideitpro.vbox")|| appInstalledOrNot("com.ParSpaMulAcc1.vapp")|| appInstalledOrNot("co.echru.multiple")|| appInstalledOrNot("com.finaal.twoaccontws")|| appInstalledOrNot("com.shadow.multiaccounts")|| appInstalledOrNot("com.saumatech.multiwindow")|| appInstalledOrNot("com.whats.dualline.app")|| appInstalledOrNot("es.excellentapps.multipleaccounts")|| appInstalledOrNot("com.uzone.guide.account.multi.parallel.space.parallelspace.tips.app")|| appInstalledOrNot("com.nextapp.twowhatsweb")|| appInstalledOrNot("com.ivymobi.multiaccount.free")|| appInstalledOrNot("com.multiacc.secureimg")|| appInstalledOrNot("com.threesprit.clonemaster")|| appInstalledOrNot("es.fastappstudio.accountmanage")|| appInstalledOrNot("com.firebreak.messenger")|| appInstalledOrNot("cloner.parallel.space.multiple.accounts.twoface")|| appInstalledOrNot("com.parallels.access")|| appInstalledOrNot("com.multipleaccs.createsecondaccs")|| appInstalledOrNot("com.teapot.parallelspacelist")|| appInstalledOrNot("com.RagdollTwins.ParallelMusing")|| appInstalledOrNot("com.tux.client")|| appInstalledOrNot("com.excelliance.multiaccount")|| appInstalledOrNot("com.clonar.app.contas.privadas.ritmos")|| appInstalledOrNot("info.cloneapp.avatarmaster.intl")|| appInstalledOrNot("com.cmcm.multiaccount")|| appInstalledOrNot("com.multiacc.twoacc.multiaccount")|| appInstalledOrNot("com.rinzz.avatar")|| appInstalledOrNot("com.parallel.space.lite")|| appInstalledOrNot("de.itgecko.sharedownloader")|| appInstalledOrNot("com.vivek.imeichanger")|| appInstalledOrNot("com.phoneinfo.changer")|| appInstalledOrNot("com.magic.imeichanger")|| appInstalledOrNot("com.twilium.IMEI.GeneratorNChanger")|| appInstalledOrNot("com.lekeope.universalgenerator")|| appInstalledOrNot("com.cryptotel.chamelephon")|| appInstalledOrNot("com.gojolo.realimeichanger")|| appInstalledOrNot("com.themonsterit.EngineerStarter")|| appInstalledOrNot("com.VTechno.androididchanger")|| appInstalledOrNot("com.makeinfo.androididchanger")|| appInstalledOrNot("org.vndnguyen.imeianalyze")|| appInstalledOrNot("com.makeinfo.imeichanger")|| appInstalledOrNot("imei_checker.syr_developers.com.imeisupporteddeviceschecker")|| appInstalledOrNot("com.tweaker.imei")|| appInstalledOrNot("com.cagri.imeirepair")|| appInstalledOrNot("com.device.emulator")|| appInstalledOrNot("com.vivek.imeichangerpro")|| appInstalledOrNot("com.xamtax.imeicontrol")|| appInstalledOrNot("com.ansoft.utilitybox")|| appInstalledOrNot("com.greatcallie.bbimeigen")|| appInstalledOrNot("com.pro.imeichanger")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PromoActivity.this);
                builder.setCancelable(false);
                builder.setTitle("Virtual Environment Found");
                builder.setMessage("Some of the applications installed in this device create virtual environment which is not acceptable by BillSpill.\nPlease make sure that your device does not have any Container application and try again later.");
                builder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      /*  Intent intent = new Intent(PromoActivity.this, MenuFeedback.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);*/
                      dialog.dismiss();
                        PromoActivity.this.finish();
                    }
                });
                builder.create();
                builder.show();
            }else if(appInstalledOrNot("com.device.emulator") || appInstalledOrNot("com.device.emulator.pro") || appInstalledOrNot("fr.energycube.android.app.com.limbo.emu.main.armv7") || appInstalledOrNot("goldenpspemulator.psp_emulator_new") || appInstalledOrNot("jackpal.androidterm") || appInstalledOrNot("\n" +
                    "com.RobertApp.EmulatorGBA") || appInstalledOrNot("com.acr.shellterminalemulator") || appInstalledOrNot("com.anhhuy.ds4droid") || appInstalledOrNot("com.dc.gbc.emulator") || appInstalledOrNot("com.handscape.emulator") || appInstalledOrNot("com.termux"))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(PromoActivity.this);
                builder.setCancelable(false);
                builder.setTitle("Emulator Detected");
                builder.setMessage("Some of the applications installed in this device create virtual environment which is not acceptable by BillSpill.\nPlease make sure that your device does not have any Emulator application and try again later.");
                builder.setPositiveButton("Got it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      /*  Intent intent = new Intent(PromoActivity.this, MenuFeedback.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);*/
                        dialog.dismiss();
                        PromoActivity.this.finish();
                    }
                });
                builder.create();
                builder.show();
            }

            else {


                FirebaseMessaging.getInstance().subscribeToTopic("test");
                FirebaseInstanceId.getInstance().getToken();

                name = (EditText) findViewById(R.id.username);
                mail = (EditText) findViewById(R.id.mail);
                number = (EditText) findViewById(R.id.number);
                code = (EditText) findViewById(R.id.promo);
                register = (Button) findViewById(R.id.promo_btn);
                skip = (Button) findViewById(R.id.skip_btn);




                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED &&ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(PromoActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.READ_PHONE_STATE}, REQUEST_READ_PHONE_STATE);
                } else {

                    gpsTracker=new GPSTracker(PromoActivity.this);
                    Location location =gpsTracker.getLocation();
                    if(location!=null){
                        double longi=location.getLongitude();
                        double latit=location.getLatitude();
                        Geocoder geocoder= new Geocoder(PromoActivity.this, Locale.getDefault());
                        List<Address> addresses =null;
                        try {
                            addresses=geocoder.getFromLocation(latit,longi,1);
                            String CityName=addresses.get(0).getLocality();
                            mFirebaseAnalytics = FirebaseAnalytics.getInstance(PromoActivity.this);
                            mFirebaseAnalytics.setUserProperty("location",CityName);
                            Log.d("Address ",CityName);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        Log.d("Null Location","Found");
                    }

                    telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                    if (telephonyManager != null) {
                        IMEI = telephonyManager.getDeviceId();
                    }
                    if (IMEI == null || IMEI.length() == 0) {
                        IMEI = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                    }
                    register.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isValidEmail(mail.getText()) && number.getText().length() == 10 && code.getText().length() >= 4 && name.getText().length() >= 4) {
                                pd = new ProgressDialog(PromoActivity.this);
                                pd.setCancelable(true);
                                pd.setTitle("Just a moment");
                                pd.setMessage("checking info...");
                                pd.show();
                                fullname = name.getText().toString();
                                email = mail.getText().toString();
                                contact = number.getText().toString();
                                pro_code = code.getText().toString();
                                preferences = getSharedPreferences(filename, 0);
                                mypromo = preferences.getString("Mypromo", "NO_DATA");
                                againshared=getSharedPreferences(Tokenfile,0);
                                token=againshared.getString("MyToken","NoToken");
                                mail.setText("");
                                number.setText("");
                                code.setText("");
                                name.setText("");
                                if (mypromo.equals("NO_DATA")) {
                                    String hint = "nodata";
                                    SecureRandom random = new SecureRandom();
                                    mypromo = new BigInteger(40, random).toString(32);
                                    prefs = getSharedPreferences(file, 0);
                                    SharedPreferences.Editor editor = prefs.edit();
                                    editor.putString("Mynewpromo", mypromo);
                                    editor.apply();
                                    OkHttpClient client = new OkHttpClient();
                                    RequestBody body = new FormBody.Builder().add("hint", hint).add("name", fullname).add("email", email).add("number", contact).add("promo", pro_code).add("mypromo", mypromo).add("imei", IMEI).add("token",token).build();
                                    Request request = new Request.Builder().url("http://billspill.com/newuserinfo.php").post(body).build();

                                    client.newCall(request).enqueue(new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {

                                        }

                                        @Override
                                        public void onResponse(Call call, final Response response) throws IOException {
                                            HttpResponse = response.body().string();
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (pd.isShowing()) {
                                                        pd.dismiss();
                                                    }
                                                }
                                            });

                                            if (HttpResponse.length() == 26) {
                                                sharedPreferences = getSharedPreferences(IMEIno, Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString("IMEI", IMEI);
                                                editor.apply();
                                                sp = getSharedPreferences(checks, Context.MODE_PRIVATE);
                                                SharedPreferences.Editor edito = sp.edit();
                                                edito.putString("counts", im);
                                                edito.apply();
                                                // sp=getSharedPreferences(IMEIno,Context.MODE_PRIVATE);
                                                // im=sp.getString("IMEI","0");
                                                Intent intent = new Intent(PromoActivity.this, TabbedMainActivity.class);
                                                startActivity(intent);
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_LONG).show();
                                                        PromoActivity.this.finish();
                                                    }
                                                });

                                            } else if (HttpResponse.length() > 6 && HttpResponse.length() < 20) {

                                                sharedPreferences = getSharedPreferences(IMEIno, Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString("IMEI", IMEI);
                                                editor.apply();
                                                sp = getSharedPreferences(checks, Context.MODE_PRIVATE);
                                                SharedPreferences.Editor edito = sp.edit();
                                                edito.putString("counts", im);
                                                edito.apply();
                                                // sp=getSharedPreferences(IMEIno,Context.MODE_PRIVATE);
                                                //im=sp.getString("IMEI","0");
                                                shared = getSharedPreferences(Oldpromo, Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editr = shared.edit();
                                                editr.putString("OLD", HttpResponse.trim());
                                                editr.apply();
                                                Intent intent = new Intent(PromoActivity.this, TabbedMainActivity.class);
                                                startActivity(intent);
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(getApplicationContext(), "Welcome Back", Toast.LENGTH_LONG).show();
                                                        PromoActivity.this.finish();
                                                    }
                                                });

                                            } else {

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        unsuccessfull(HttpResponse);

                                                    }
                                                });


                                            }
                                        }
                                    });


                                } else {
                                    String hint = "gotdata";
                                    OkHttpClient client = new OkHttpClient();
                                    RequestBody body = new FormBody.Builder().add("hint", hint).add("name", fullname).add("email", email).add("number", contact).add("promo", pro_code).add("mypromo", mypromo).add("imei", IMEI).add("token",token).build();
                                    Request request = new Request.Builder().url("http://billspill.com/newuserinfo.php").post(body).build();

                                    client.newCall(request).enqueue(new Callback() {
                                        @Override
                                        public void onFailure(Call call, IOException e) {

                                        }

                                        @Override
                                        public void onResponse(Call call, final Response response) throws IOException {
                                            HttpResponse = response.body().string();
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (pd.isShowing()) {
                                                        pd.dismiss();
                                                    }
                                                }
                                            });

                                            if (HttpResponse.length() == 26) {
                                                sharedPreferences = getSharedPreferences(IMEIno, Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString("IMEI", IMEI);
                                                editor.apply();
                                                sp = getSharedPreferences(checks, Context.MODE_PRIVATE);
                                                SharedPreferences.Editor edito = sp.edit();
                                                edito.putString("counts", im);
                                                edito.apply();
                                                // sp=getSharedPreferences(IMEIno,Context.MODE_PRIVATE);
                                                //im=sp.getString("IMEI","0");
                                                Intent intent = new Intent(PromoActivity.this, TabbedMainActivity.class);
                                                startActivity(intent);
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_LONG).show();
                                                        PromoActivity.this.finish();
                                                    }
                                                });

                                            } else if (HttpResponse.length() > 6 && HttpResponse.length() < 20) {

                                                sharedPreferences = getSharedPreferences(IMEIno, Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                                editor.putString("IMEI", IMEI);
                                                editor.apply();
                                                sp = getSharedPreferences(checks, Context.MODE_PRIVATE);
                                                SharedPreferences.Editor edito = sp.edit();
                                                edito.putString("counts", im);
                                                edito.apply();
                                                //  sp=getSharedPreferences(IMEIno,Context.MODE_PRIVATE);
                                                //im=sp.getString("IMEI","0");
                                                shared = getSharedPreferences(Oldpromo, Context.MODE_PRIVATE);
                                                SharedPreferences.Editor editr = shared.edit();
                                                editr.putString("OLD", HttpResponse.trim());
                                                editr.apply();
                                                Intent intent = new Intent(PromoActivity.this, TabbedMainActivity.class);
                                                startActivity(intent);
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Toast.makeText(getApplicationContext(), "Welcome Back", Toast.LENGTH_LONG).show();
                                                        PromoActivity.this.finish();
                                                    }
                                                });

                                            } else {

                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {

                                                        unsuccessfull(HttpResponse);

                                                    }
                                                });


                                            }
                                        }
                                    });

                                }
                            } else if (name.getText().length() < 4) {
                                namenotadded();
                            } else if (!isValidEmail(mail.getText())) {
                                emailnotadded();
                                ;
                            } else if (!(number.getText().length() == 10)) {
                                numbernotadded();
                            } else {
                                promonotadded();
                            }
                        }
                    });
                }
                skip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PromoActivity.this, TabbedMainActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }
    public void unsuccessfull(String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(PromoActivity.this);
        builder.setTitle("Attention!!");
        builder.setMessage(msg);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Jump to App", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(PromoActivity.this,TabbedMainActivity.class);
                startActivity(intent);
            }
        });
        builder.show();

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
    public void numbernotadded(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid Number");
        builder.setMessage("Enter valid mobile number");
        builder.show();
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }
    public void namenotadded(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name too Short");
        builder.setMessage("Enter your full name");
        builder.show();
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }
    public void promonotadded(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Invalid Code");
        builder.setMessage("Enter valid Promo Code");
        builder.show();
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        if(requestCode==REQUEST_READ_PHONE_STATE)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                gpsTracker=new GPSTracker(PromoActivity.this);
                Location location =gpsTracker.getLocation();
                if(location!=null){
                    double longi=location.getLongitude();
                    double latit=location.getLatitude();
                    Geocoder geocoder= new Geocoder(PromoActivity.this, Locale.getDefault());
                    List<Address> addresses =null;
                    try {
                        addresses=geocoder.getFromLocation(latit,longi,1);
                        String CityName=addresses.get(0).getLocality();
                        mFirebaseAnalytics = FirebaseAnalytics.getInstance(PromoActivity.this);
                        mFirebaseAnalytics.setUserProperty("location",CityName);
                        Log.d("Address ",CityName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    Log.d("Null Location","Found");
                }

                telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                if (telephonyManager != null) {
                    IMEI = telephonyManager.getDeviceId();
                }
                if (IMEI == null || IMEI.length() == 0) {
                    IMEI = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                }
                register.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isValidEmail(mail.getText())&&number.getText().length()==10&&code.getText().length()>=4){
                            pd = new ProgressDialog(PromoActivity.this);
                            pd.setCancelable(true);
                            pd.setTitle("Just a moment");
                            pd.setMessage("checking info...");
                            pd.show();
                            fullname=name.getText().toString();
                            email=mail.getText().toString();
                            contact=number.getText().toString();
                            pro_code=code.getText().toString();
                            preferences=getSharedPreferences(filename,0);
                            mypromo=preferences.getString("Mypromo","NO_DATA");
                            againshared=getSharedPreferences(Tokenfile,0);
                            token=againshared.getString("MyToken","NoToken");
                            mail.setText("");
                            number.setText("");
                            code.setText("");
                            name.setText("");
                            if(mypromo.equals("NO_DATA")) {

                                SecureRandom random=new SecureRandom();
                                mypromo=new BigInteger(40,random).toString(32);
                                prefs=getSharedPreferences(file,0);
                                String hint="nodata";
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("Mynewpromo",mypromo);
                                editor.apply();
                                OkHttpClient client = new OkHttpClient();
                                RequestBody body = new FormBody.Builder().add("hint",hint).add("name", fullname).add("email", email).add("number", contact).add("promo", pro_code).add("mypromo", mypromo).add("imei", IMEI).add("token",token).build();
                                Request request = new Request.Builder().url("http://billspill.com/newuserinfo.php").post(body).build();

                                client.newCall(request).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(Call call, final Response response) throws IOException {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(pd.isShowing()){pd.dismiss();}
                                            }
                                        });
                                        HttpResponse = response.body().string();
                                        if (HttpResponse.length() == 26) {
                                            sharedPreferences = getSharedPreferences(IMEIno, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("IMEI", IMEI);
                                            editor.apply();
                                            sp = getSharedPreferences(checks, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor edito = sp.edit();
                                            edito.putString("counts", im);
                                            edito.apply();
                                            //  sp=getSharedPreferences(IMEIno,Context.MODE_PRIVATE);
                                            // im=sp.getString("IMEI","0");
                                            Intent intent = new Intent(PromoActivity.this, TabbedMainActivity.class);
                                            startActivity(intent);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_LONG).show();
                                                    PromoActivity.this.finish();
                                                }
                                            });

                                        } else if (HttpResponse.length() > 6 && HttpResponse.length() < 20) {

                                            sharedPreferences = getSharedPreferences(IMEIno, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("IMEI", IMEI);
                                            editor.apply();
                                            sp = getSharedPreferences(checks, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor edito = sp.edit();
                                            edito.putString("counts", im);
                                            edito.apply();
                                            //sp=getSharedPreferences(IMEIno,Context.MODE_PRIVATE);
                                            // im=sp.getString("IMEI","0");
                                            shared = getSharedPreferences(Oldpromo, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editr = shared.edit();
                                            editr.putString("OLD", HttpResponse.trim());
                                            editr.apply();
                                            Intent intent = new Intent(PromoActivity.this, TabbedMainActivity.class);
                                            startActivity(intent);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(getApplicationContext(), "Welcome Back", Toast.LENGTH_LONG).show();
                                                    PromoActivity.this.finish();
                                                }
                                            });

                                        } else {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {

                                                    unsuccessfull(HttpResponse);

                                                }
                                            });


                                        }
                                    }
                                });



                            }
                            else{
                                String hint="gotdata";
                                OkHttpClient client = new OkHttpClient();
                                RequestBody body = new FormBody.Builder().add("hint",hint).add("name", fullname).add("email", email).add("number", contact).add("promo", pro_code).add("mypromo", mypromo).add("imei", IMEI).add("token",token).build();
                                Request request = new Request.Builder().url("http://billspill.com/newuserinfo.php").post(body).build();

                                client.newCall(request).enqueue(new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(Call call, final Response response) throws IOException {
                                        HttpResponse = response.body().string();
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(pd.isShowing()){pd.dismiss();}
                                            }
                                        });

                                        if (HttpResponse.length() == 26) {
                                            sharedPreferences = getSharedPreferences(IMEIno, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("IMEI", IMEI);
                                            editor.apply();
                                            sp = getSharedPreferences(checks, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor edito = sp.edit();
                                            edito.putString("counts", im);
                                            edito.apply();
                                            //sp=getSharedPreferences(IMEIno,Context.MODE_PRIVATE);
                                            // im=sp.getString("IMEI","0");
                                            Intent intent = new Intent(PromoActivity.this, TabbedMainActivity.class);
                                            startActivity(intent);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_LONG).show();
                                                    PromoActivity.this.finish();
                                                }
                                            });

                                        } else if (HttpResponse.length() > 6 && HttpResponse.length() < 20) {

                                            sharedPreferences = getSharedPreferences(IMEIno, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("IMEI", IMEI);
                                            editor.apply();
                                            sp = getSharedPreferences(checks, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor edito = sp.edit();
                                            edito.putString("counts", im);
                                            edito.apply();
                                            //sp=getSharedPreferences(IMEIno,Context.MODE_PRIVATE);
                                            // im=sp.getString("IMEI","0");
                                            shared = getSharedPreferences(Oldpromo, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editr = shared.edit();
                                            editr.putString("OLD", HttpResponse.trim());
                                            editr.apply();
                                            Intent intent = new Intent(PromoActivity.this, TabbedMainActivity.class);
                                            startActivity(intent);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(getApplicationContext(), "Welcome Back", Toast.LENGTH_LONG).show();
                                                    PromoActivity.this.finish();
                                                }
                                            });

                                        } else {

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {

                                                    unsuccessfull(HttpResponse);

                                                }
                                            });


                                        }
                                    }
                                });

                            }
                        }
                        else if(name.getText().length()<4){
                            namenotadded();;
                        }
                        else if(!isValidEmail(mail.getText())){
                            emailnotadded();;
                        }
                        else if(!(number.getText().length()==10)){
                            numbernotadded();
                        }
                        else {
                            promonotadded();
                        }
                    }
                });
            }
            else {
                Intent intent=new Intent(PromoActivity.this,TabbedMainActivity.class);
                startActivity(intent);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
