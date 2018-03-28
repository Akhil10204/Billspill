package com.billspillstore.android.Fragments_category;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.billspillstore.android.R;
import com.billspillstore.android.SearchOthers;
import com.billspillstore.android.SearchSetStore;

/**
 * Created by Priyam Tyagi on 07-06-2017.
 */

public class Ebay extends Fragment {
    View view;
    ProgressDialog progressDialog;
    String url= "http://ebayindia.go2cloud.org/aff_c?offer_id=19&aff_id=2334";
    String input;
    String type,type2;
    WebView webView;
    SwipeRefreshLayout mySwipeRefreshLayout;
    public void getInput(String string){
        this.input=string;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.ebay,container,false);
         webView=(WebView)view.findViewById(R.id.ebay_webview);
        mySwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipeContainer);

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        webView.reload();
                    }
                }
        );
        webView.setWebViewClient(new myWebClient());
        webView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 4.0.4; Galaxy Nexus Build/IMM76B) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
         type = SearchOthers.code;
         type2= SearchSetStore.code;
        if(type.equals("1")&&type2.equals("0")){
            input=getActivity().getIntent().getExtras().getString("INPUT");
            webView.loadUrl("http://www.ebay.in/deals/sch?_from=R40&_trksid=p2380779.m570.l1313.TR2.TRC0.A0.H0.X"+input+".TRS0&_nkw="+input+"&_sacat=-1&_sop=");
        }
        else if(type2.equals("1")){
            input=getActivity().getIntent().getExtras().getString("INPUT");
            webView.loadUrl("http://www.ebay.in/deals/sch?_from=R40&_trksid=p2380779.m570.l1313.TR2.TRC0.A0.H0.X"+input+".TRS0&_nkw="+input+"&_sacat=-1&_sop=");
        }
        else {
            webView.loadUrl(url);
        }
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
                    webView.goBack();
                    return true;
                }
                return false;
            }
        });
        return view;
    }

    public   class myWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

mySwipeRefreshLayout.setRefreshing(true);
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            mySwipeRefreshLayout.setRefreshing(false);
             if(type2.equals("1")){
             SearchSetStore.code="0";
}
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
