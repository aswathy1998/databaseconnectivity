package com.example.aswathy.dbapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
TextView tv1,tv2;
    String s1,s2;
    Button b;
    databasehelper Dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1=(TextView)findViewById(R.id.nam);
        tv2=(TextView)findViewById(R.id.mailid);
        b=(Button)findViewById(R.id.sub);
        Dbhelper=new databasehelper(this);
        Dbhelper.getWritableDatabase();


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             s1=tv1.getText().toString();
                s2=tv2.getText().toString();
                Log.d("name",s1);
                Log.d("Email id",s2);

                boolean status=Dbhelper.insertData(s1,s2);
                if(status==true){
                    Toast.makeText(getApplicationContext(),"succcessfully inserted",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
