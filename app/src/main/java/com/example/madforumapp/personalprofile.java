package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class personalprofile extends AppCompatActivity {

    TextView txt1,txt2,txt3;

    String t1,t2,t3;

    ImageView img;

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalprofile);

        btn=findViewById(R.id.signout);

        img=findViewById(R.id.clickfeedback);

        txt1=findViewById(R.id.rtrname);

        txt2=findViewById(R.id.rtremail);

        txt3=findViewById(R.id.rtrpassword);



        Intent i1=getIntent();

        t1=i1.getStringExtra("message1");

        t2=i1.getStringExtra("message2");

        t3=i1.getStringExtra("message3");


        txt1.setText(t1);

        txt2.setText(t2);

        txt3.setText(t3);


    }

    @Override
    protected void onResume() {
        super.onResume();


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i3=new Intent(personalprofile.this,FeedBack.class);

                startActivity(i3);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i3=new Intent(personalprofile.this,act2.class);

                startActivity(i3);
            }
        });


    }
}
