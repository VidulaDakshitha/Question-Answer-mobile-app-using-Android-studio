package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class act2 extends AppCompatActivity {

    private TextView register,forgotp,info;
    private ImageButton btnLogin;
    private EditText email,password;
    private int counter=5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act2);

        register=findViewById(R.id.signup);
        forgotp=findViewById(R.id.forgot);
        email=findViewById(R.id.logemail);
        password=findViewById(R.id.logpassword);
        info=findViewById(R.id.tvinfo);

        btnLogin = findViewById(R.id.btnLogin);

        info.setText("Login Attempts remaining: 5");

    }

    public void validate(String userName,String userPassword)
    {
        if((userName.equals("Admin")&&(userPassword.equals("1234")))) {
            Intent mainFeedIntent = new Intent(act2.this, MainFeed.class);
            startActivity(mainFeedIntent);
        }else{

            counter--;

            info.setText("Login Attempts remaining:" + String.valueOf(counter));

            if(counter==0)
            {
                btnLogin.setEnabled(false);
            }

        }

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


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate(email.getText().toString(),password.getText().toString());

            }
        });

    }
}
