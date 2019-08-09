package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class signup extends AppCompatActivity {

    TextView registersignin;

    Button register;

    EditText e1,e2,e3;

    String v1,v2,v3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        registersignin=findViewById(R.id.regsignin);

        e1=findViewById(R.id.nametxt);
        e2=findViewById(R.id.emailtxt);
        e3=findViewById(R.id.passwordtxt);

        register=findViewById(R.id.signupbtn);

    }

    @Override
    protected void onResume() {
        super.onResume();


        v1=e1.getText().toString();
        v2=e2.getText().toString();
        v3=e3.getText().toString();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1=new Intent(signup.this,personalprofile.class);

                i1.putExtra("message1",v1);
                i1.putExtra("message2",v2);
                i1.putExtra("message3",v3);

                startActivity(i1);
            }
        });



        registersignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(signup.this,act2.class);

                startActivity(i);
            }
        });



    }
}
