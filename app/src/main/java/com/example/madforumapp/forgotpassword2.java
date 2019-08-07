package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class forgotpassword2 extends AppCompatActivity {

    TextView t1;

    String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword2);

        t1=findViewById(R.id.emailretrieve);

        Intent i2=getIntent();

        val=i2.getStringExtra("val1");

        t1.setText(val);


    }


}
