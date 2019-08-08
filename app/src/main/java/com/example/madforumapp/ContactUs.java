package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ContactUs extends AppCompatActivity {

    ImageButton btn1;

    EditText edt1, edt2, edt3;

    String val1, val2, val3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        btn1 = findViewById(R.id.submit);
        edt1 = findViewById(R.id.contname);
        edt2 = findViewById(R.id.contemail);
        edt3 = findViewById(R.id.contreq);


    }

    @Override
    protected void onResume() {
        super.onResume();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val1 = edt1.getText().toString();
                val2 = edt2.getText().toString();
                val3 = edt3.getText().toString();

                Intent int1 = new Intent(ContactUs.this, ContactUs2.class);
                int1.putExtra("text1", val1);
                int1.putExtra("text2", val2);
                int1.putExtra("text3", val3);

                startActivity(int1);
            }
        });
    }
}
