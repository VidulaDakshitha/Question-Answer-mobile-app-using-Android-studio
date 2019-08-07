package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class forgotpassword extends AppCompatActivity {

    EditText emailck;
    Button send;

    String val1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        emailck=findViewById(R.id.checkemail);

        send=findViewById(R.id.btnsend);

    }

    @Override
    protected void onResume() {
        super.onResume();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                val1=emailck.getText().toString();

                Intent i=new Intent(forgotpassword.this,forgotpassword2.class);

                i.putExtra("val1",val1);

                startActivity(i);

            }
        });
    }
}
