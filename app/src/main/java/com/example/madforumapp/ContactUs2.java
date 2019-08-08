package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ContactUs2 extends AppCompatActivity {

    String v1, v2, v3;
    TextView t1, t2, t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us2);

        t1 = (TextView)findViewById(R.id.nametext);
        t2 = (TextView)findViewById(R.id.emailtext);
        t3 = (TextView)findViewById(R.id.requesttext);

        Intent int2 = getIntent();

        v1 = int2.getStringExtra("text1");
        v2 = int2.getStringExtra("text2");
        v3 = int2.getStringExtra("text3");

        t1.setText(v1);
        t2.setText(v2);
        t3.setText(v3);
    }
}
