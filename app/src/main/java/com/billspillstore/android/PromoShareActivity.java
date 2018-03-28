package com.billspillstore.android;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class PromoShareActivity extends Activity {
    TextView installs,coupon,terms;
    Button share,avail;
    String install;
    int counts=0;
    public static String checks="Check";
    public static String filename="Mydata";
    public static String file="Mynewcode";
    public static String IMEIno="NUMBER";
    public static String Oldpromo="OLDPROMO";
    public static String returned;
    String old,returnd,returncode;
    String imei;
    BGInstallation bgInstallation;
    SharedPreferences preferences,sharedPreferences,shared,prefs;
    SwipeRefreshLayout mySwipeRefreshLayout;

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


        if (appInstalledOrNot("com.lbe.parallel.intl") || appInstalledOrNot("com.excelliance.multiaccount") || appInstalledOrNot("com.applisto.appcloner") || appInstalledOrNot("com.cmcm.multiaccount") || appInstalledOrNot("com.jack.appclonemultiaccount") || appInstalledOrNot("com.jiubang.commerce.gomultiple") || appInstalledOrNot("com.in.parallel.accounts") || appInstalledOrNot("com.parallel.space.lite") || appInstalledOrNot("com.excelliance.multiaccounts") || appInstalledOrNot("com.polestar.multiaccount") || appInstalledOrNot("com.phantom.gemini") || appInstalledOrNot("com.in.parallel.accounts") || appInstalledOrNot("com.lbe.parallel.intl.arm64") || appInstalledOrNot("com.parallel.space.lite.arm64") || appInstalledOrNot("parallel.multi.accounts.space") || appInstalledOrNot("com.parallel.multi.account.dualsim") || appInstalledOrNot("dotcode.parallel") || appInstalledOrNot("com.parallel.appclonemultiaccount") || appInstalledOrNot("com.whatsdual.fcuk") || appInstalledOrNot("paralel.spaceguide.para") || appInstalledOrNot("com.reny.securespaceaccount") || appInstalledOrNot("com.applock.fast.switch.multiple.accounts") || appInstalledOrNot("com.hola.multiaccount") || appInstalledOrNot("cam.paralellmulti.accspace") || appInstalledOrNot("com.spaceplus.we") || appInstalledOrNot("com.multimessenger.faaad1.app") || appInstalledOrNot("com.multiaccount.twins") || appInstalledOrNot("com.parallelbox") || appInstalledOrNot("com.trigtech.privateme") || appInstalledOrNot("a2ndappwhats.sdkw.com") || appInstalledOrNot("com.guide.watsappppa") || appInstalledOrNot("com.app.tiki.multimessenger") || appInstalledOrNot("com.nutsmobi.parallel") || appInstalledOrNot("com.multiacc.dualaccou") || appInstalledOrNot("com.hideitpro.vbox") || appInstalledOrNot("com.ParSpaMulAcc1.vapp") || appInstalledOrNot("co.echru.multiple") || appInstalledOrNot("com.finaal.twoaccontws") || appInstalledOrNot("com.shadow.multiaccounts") || appInstalledOrNot("com.saumatech.multiwindow") || appInstalledOrNot("com.whats.dualline.app") || appInstalledOrNot("es.excellentapps.multipleaccounts") || appInstalledOrNot("com.uzone.guide.account.multi.parallel.space.parallelspace.tips.app") || appInstalledOrNot("com.nextapp.twowhatsweb") || appInstalledOrNot("com.ivymobi.multiaccount.free") || appInstalledOrNot("com.multiacc.secureimg") || appInstalledOrNot("com.threesprit.clonemaster") || appInstalledOrNot("es.fastappstudio.accountmanage") || appInstalledOrNot("com.firebreak.messenger") || appInstalledOrNot("cloner.parallel.space.multiple.accounts.twoface") || appInstalledOrNot("com.parallels.access") || appInstalledOrNot("com.multipleaccs.createsecondaccs") || appInstalledOrNot("com.teapot.parallelspacelist") || appInstalledOrNot("com.RagdollTwins.ParallelMusing") || appInstalledOrNot("com.tux.client") || appInstalledOrNot("com.excelliance.multiaccount") || appInstalledOrNot("com.clonar.app.contas.privadas.ritmos") || appInstalledOrNot("info.cloneapp.avatarmaster.intl") || appInstalledOrNot("com.cmcm.multiaccount") || appInstalledOrNot("com.multiacc.twoacc.multiaccount") || appInstalledOrNot("com.rinzz.avatar") || appInstalledOrNot("com.parallel.space.lite") || appInstalledOrNot("de.itgecko.sharedownloader") || appInstalledOrNot("com.vivek.imeichanger") || appInstalledOrNot("com.phoneinfo.changer") || appInstalledOrNot("com.magic.imeichanger") || appInstalledOrNot("com.twilium.IMEI.GeneratorNChanger") || appInstalledOrNot("com.lekeope.universalgenerator") || appInstalledOrNot("com.cryptotel.chamelephon") || appInstalledOrNot("com.gojolo.realimeichanger") || appInstalledOrNot("com.themonsterit.EngineerStarter") || appInstalledOrNot("com.VTechno.androididchanger") || appInstalledOrNot("com.makeinfo.androididchanger") || appInstalledOrNot("org.vndnguyen.imeianalyze") || appInstalledOrNot("com.makeinfo.imeichanger") || appInstalledOrNot("imei_checker.syr_developers.com.imeisupporteddeviceschecker") || appInstalledOrNot("com.tweaker.imei") || appInstalledOrNot("com.cagri.imeirepair") || appInstalledOrNot("com.device.emulator") || appInstalledOrNot("com.vivek.imeichangerpro") || appInstalledOrNot("com.xamtax.imeicontrol") || appInstalledOrNot("com.ansoft.utilitybox") || appInstalledOrNot("com.greatcallie.bbimeigen") || appInstalledOrNot("com.pro.imeichanger")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(PromoShareActivity.this);
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
                    PromoShareActivity.this.finish();
                }
            });
            builder.create();
            builder.show();
        } else {
            setContentView(R.layout.activity_promo_share);
            mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
            installs = (TextView) findViewById(R.id.installation);
            coupon = (TextView) findViewById(R.id.coupon);
            terms = (TextView) findViewById(R.id.tnc);
            share = (Button) findViewById(R.id.share_btn);
            avail = (Button) findViewById(R.id.avail_btn);
            preferences = getSharedPreferences(filename, 0);
            returnd = preferences.getString("Mypromo", "NO_DATA");
            prefs = getSharedPreferences(file, 0);
            returncode = prefs.getString("Mynewpromo", "not_found");
            sharedPreferences = getSharedPreferences(checks, Context.MODE_PRIVATE);
            imei = sharedPreferences.getString("counts", "50");
            if (imei.equals("50")) {
                Intent intent = new Intent(this, PromoActivity.class);
                startActivity(intent);
                this.finish();
            }
            shared = getSharedPreferences(Oldpromo, Context.MODE_PRIVATE);
            old = shared.getString("OLD", "nothing");
            if (old.equals("nothing")) {
                if (returnd.equals("NO_DATA")) {
                    returned = returncode;
                } else {
                    returned = returnd;
                }
            } else {
                returned = old;
            }
            coupon.setText(returned);
            bgInstallation = new BGInstallation(this);
            bgInstallation.execute(returned);
            install = BGInstallation.response();
            if (install != null) {
                counts = Integer.parseInt(install.trim());
                installs.setText("" + counts);
            }
            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    String shareBody = "\nAll In One App\nShopping, Recharge, Flight, Stay and Food\n" + "\n Use this code: " + returned + "\n";
                    shareBody = shareBody + "https://play.google.com/store/apps/details?id=com.billspillstore.android \n\n";
                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BillSpill");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }
            });
            avail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notavailable();
                }
            });
            if (install != null) {
                installs.setText("" + counts);
                if (counts >= 5) {
                    avail.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                    avail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(PromoShareActivity.this, AvailOfferActivity.class);
                            startActivity(intent);
                        }
                    });
                }
            }
            terms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(PromoShareActivity.this);
                    builder.setTitle("Terms & Conditions");
                    builder.setMessage("1.This offer is valid only once per user/device.\n2.Only first 50 users can avail this offer every Tuesday at 9PM.\n3.Keep atleast 5 installs ready to avail the offer\n4.Amount can only be credited to the Paytm linked number.\n5.Amout will be sent to Paytm Wallet within 12hrs.\n6.Offer is valid only on Android platform.\n7.If at any point, BillSpill verifies the usage of the offer code violates the terms & conditions or is abusive to the spirit of the offer, BillSpill reserves the sole right to suspend the user from the offer and deny any further offer on BillSpill platform.\n8.BillSpill will not get involve in any abusive conversation.\n9.In case of any dispute BillSpill's decision will be final.\n10.BillSpill reserves the right to modify, change, add, remove these terms & conditions or discontinue this offer without any further notice.   ");
                    builder.show();
                    builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }
                    });
                }
            });

            mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    coupon.setText(returned);
                    BGInstallation bgInstallation = new BGInstallation(PromoShareActivity.this);
                    bgInstallation.execute(returned);
                    install = BGInstallation.response();
                    if (install != null) {
                        counts = Integer.parseInt(install.trim());
                        installs.setText("" + counts);
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                    if (install != null) {
                        mySwipeRefreshLayout.setRefreshing(false);
                        installs.setText("" + counts);
                        if (counts >= 5) {
                            avail.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
                            avail.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(PromoShareActivity.this, AvailOfferActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }
                    }
                }
            });
        }
        }

        @Override
        protected void onPause () {
            if(bgInstallation!=null){
            bgInstallation.cancel(true);}
            super.onPause();
        }

    public void notavailable() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PromoShareActivity.this);
        builder.setTitle("Attention!!!");
        builder.setMessage("Minimum 5 installs are required to avail the offer");
        builder.show();
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

}
