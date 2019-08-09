package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class act2 extends AppCompatActivity {

    TextView register,forgotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);

        register=findViewById(R.id.signup);
        forgotp=findViewById(R.id.forgot);
    }

    @Override
    protected void onResume() {
        super.onResume();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(act2.this,signup.class);
                startActivity(i);
            }
        });



        forgotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(act2.this,forgotpassword.class);
                startActivity(i);
            }
        });


        ImageButton btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainFeedIntent = new Intent(act2.this, MainFeed.class);
                startActivity(mainFeedIntent);
            }
        });

    }
}
