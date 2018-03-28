package com.billspillstore.android.Fragments_category;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.billspillstore.android.R;
import com.billspillstore.android.SearchActivity;
import com.billspillstore.android.SearchSetStore;


/**
 * Created by Priyam Tyagi on 07-06-2017.
 */

public class Flipkart extends Fragment {
    View view;
    String url="https://dl.flipkart.com/dl/?affid=billspill";
    String input;
    String type,type2;

    public void getInput(String string){
        this.input=string;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.flipkart, container, false);
        final WebView webView = (WebView) view.findViewById(R.id.flipkart_webview);

        webView.setWebViewClient(new myWebClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
         type = SearchActivity.code;
         type2= SearchSetStore.code;
        if(type.equals("1")&&type2.equals("0")){
            input=getActivity().getIntent().getExtras().getString("INPUT");
            webView.loadUrl("https://www.flipkart.com/search?q="+input+"&otracker=start&as-show=on&as=off?affid=billspill");

        }
        else if(type2.equals("1")){
            input=getActivity().getIntent().getExtras().getString("INPUT");
            webView.loadUrl("https://www.flipkart.com/search?q="+input+"&otracker=start&as-show=on&as=off?affid=billspill");
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


            super.onPageStarted(view, url, favicon);
        }

      @Override
      public void onPageFinished(WebView view, String url) {


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


