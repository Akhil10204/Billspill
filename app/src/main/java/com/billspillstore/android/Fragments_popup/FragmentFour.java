package com.billspillstore.android.Fragments_popup;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.billspillstore.android.BackgroundWorker;
import com.billspillstore.android.MyAccessibilityService;
import com.billspillstore.android.R;
import com.billspillstore.android.m_UI.PicassoClient;


public class FragmentFour extends Fragment {
    String nname,nimage;
    int nrate;
    TextView textname,textrate;
    ImageView imageimage;
    Spinner spinner ;
    Button button;
    EditText email;

    ArrayAdapter<CharSequence> adapter;

    public void getDetails(String name,int rate,String image){

        this.nname=name;
        this.nrate=rate;
        this.nimage=image;
    }

    public final  boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public void emailadded(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setIcon(R.drawable.tick);
        builder.setTitle("Successfully Submitted");
        builder.setMessage("you will be notified whenever price drops as per your request");
        builder.show();
        builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });



    }
    public void emailnotadded(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_four,container,false);
        spinner = (Spinner)view.findViewById(R.id.spinner);
        textname=(TextView)view.findViewById(R.id.notifyname);
        textrate=(TextView)view.findViewById(R.id.notifyrate);
        imageimage=(ImageView)view.findViewById(R.id.notifyimage);
        button=(Button)view.findViewById(R.id.notifybutton);
        email=(EditText)view.findViewById(R.id.mailtext);
        adapter = ArrayAdapter.createFromResource(getContext(),R.array.dropdownmenu,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(isValidEmail(email.getText()))
               {
                   String emailaddress=email.getText().toString();
                   String pdtname=textname.getText().toString();
                   String pdtrate=textrate.getText().toString();
                   String when =spinner.getSelectedItem().toString();
                   String store = MyAccessibilityService.appname;
                   BackgroundWorker backgroundWorker=new BackgroundWorker(getContext());
                   backgroundWorker.execute(emailaddress,pdtname,pdtrate,when,store);
                   // emailadded();
                    email.setText("");

               }
               else{

                   emailnotadded();
                   email.setText("");
                               }
            }
        });


        nname =  getActivity().getIntent().getExtras().getString("NAME");
        nrate =  getActivity().getIntent().getExtras().getInt("RATE");
        nimage =  getActivity().getIntent().getExtras().getString("IMAGE");

        textname.setText(nname);
        textrate.setText(""+nrate);
        PicassoClient.downloadimage(getActivity(),nimage,imageimage);










        return view;
    }


}
