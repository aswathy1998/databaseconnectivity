package com.example.aswathy.dbapplication;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class searchActivity extends AppCompatActivity {
TextView tv1;
    Button b,b1,b2;
    String getname,getemail,getid,newEmail;
    EditText ed1;
AlertDialog.Builder builder;
    databasehelper Dhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        builder=new  AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure want to Delete");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Yes clicked",Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();

                boolean status=Dhelper.DeleteData(getid);
                if(status==true){
                    Toast.makeText(getApplicationContext(),"DELETED SUCESSFULLY",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"ERROR IN DELETION",Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"No clicked",Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
            }
        });

        tv1=(TextView)findViewById(R.id.nm);
        b=(Button)findViewById(R.id.srch2);
        b1=(Button)findViewById(R.id.up);
        b2=(Button)findViewById(R.id.dl);
        ed1=(EditText)findViewById(R.id.srcd);


        Dhelper=new databasehelper(this);
        Dhelper.getWritableDatabase();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                boolean status=Dhelper.DeleteData(getid);
//                if(status==true){
//                    Toast.makeText(getApplicationContext(),"DELETED SUCESSFULLY",Toast.LENGTH_LONG).show();
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"ERROR IN DELETION",Toast.LENGTH_LONG).show();
//                }

                AlertDialog alert =builder.create();
                alert.show();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //r1=ed1.getText().toString();
               //Toast.makeText(getApplicationContext(),r1,Toast.LENGTH_LONG).show();
                newEmail=ed1.getText().toString();
                boolean status=Dhelper.updateData(getid,newEmail);
                if(status==true){
                    Toast.makeText(getApplicationContext(),"UPDATED SUCESSFULLY",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"ERROR IN UPDATION",Toast.LENGTH_LONG).show();
                }
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             getname=tv1.getText().toString();
                Log.d("name",getname);
                Cursor cur=Dhelper.searchdata(getname);
                if(cur.getCount()==0)
                {
                    Toast.makeText(getApplicationContext(),"no name found",Toast.LENGTH_LONG).show();
                }
                else
                {
                    while (cur.moveToNext())
                    {
                        getemail=cur.getString(2);
                       // Toast.makeText(getApplicationContext(),getemail,Toast.LENGTH_LONG).show();
                        b1.setVisibility(view.VISIBLE);
                        b2.setVisibility(view.VISIBLE);
                        ed1.setVisibility(view.VISIBLE);
                        ed1.setText(getemail);

                        getid=cur.getString(0);
                        Toast.makeText(getApplicationContext(),getid,Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
}
