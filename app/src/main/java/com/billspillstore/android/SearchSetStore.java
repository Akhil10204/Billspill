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

public class SearchSetStore extends Activity {
    EditText search;
    Button button;
    public static   String code ="0";
    String i1,i2,i3,i4;
    public void showSoftKeyboard(View view){
        if(view.requestFocus()){
            InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_set_store);
        search=(EditText)findViewById(R.id.searchview);
        showSoftKeyboard(search);
        button=(Button)findViewById(R.id.search_btn);
        Intent intent=this.getIntent();
        i1=intent.getExtras().getString("I1");
        i2=intent.getExtras().getString("I2");
        i3=intent.getExtras().getString("I3");
        i4=intent.getExtras().getString("I4");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(search.getText().length()>0){
                    code="1";
                    String input=search.getText().toString();
                    Intent i=new Intent(getApplicationContext(),TabbedStoreResult.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("Input1",i1);
                    i.putExtra("Input2",i2);
                    i.putExtra("Input3",i3);
                    i.putExtra("Input4",i4);
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
