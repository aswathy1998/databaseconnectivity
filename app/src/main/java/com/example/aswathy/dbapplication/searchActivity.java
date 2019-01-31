package com.example.aswathy.dbapplication;

import android.database.Cursor;
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
    Button b;
    String getname,getemail;
    EditText ed1;
    databasehelper Dhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        tv1=(TextView)findViewById(R.id.nm);
        b=(Button)findViewById(R.id.srch2);
        ed1=(EditText)findViewById(R.id.srcd);

        Dhelper=new databasehelper(this);
        Dhelper.getWritableDatabase();

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
                        //Toast.makeText(getApplicationContext(),getemail,Toast.LENGTH_LONG).show();
                        ed1.setText(getemail);
                    }
                }
            }
        });
    }
}
