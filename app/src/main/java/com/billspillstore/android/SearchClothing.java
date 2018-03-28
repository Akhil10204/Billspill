package com.billspillstore.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Akhil Saraswat on 25-05-2017.
 */

public class SearchClothing extends Activity {

    EditText search;
    Button button;
    public static   String code ="0";

    public void showSoftKeyboard(View view){
        if(view.requestFocus()){
            InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        search=(EditText)findViewById(R.id.searchview);
        showSoftKeyboard(search);
        button=(Button)findViewById(R.id.search_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search.getText().length()>0){
                    code="1";
                    String input=search.getText().toString();
                    Intent i=new Intent(getApplicationContext(),ClothingTabbedActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("INPUT",input);
                    getApplicationContext().startActivity(i);

                }
                else {
                    Toast.makeText(getApplicationContext(),"Please enter a valid search",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onPause() {
        this.finish();
        super.onPause();
    }
}
