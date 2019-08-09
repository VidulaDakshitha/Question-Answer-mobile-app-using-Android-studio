package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class FeedBack extends AppCompatActivity {

    ImageButton btn1, btn2, btn3, btn4, btn5, btnsub, btnfeed;

    EditText edt1, edt2;

    String   val1
            ,val6
            ,val7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        btn1 = (ImageButton)findViewById(R.id.excellent);
        btn2 = (ImageButton)findViewById(R.id.good);
        btn3 = (ImageButton)findViewById(R.id.dontno);
        btn4 = (ImageButton)findViewById(R.id.bad);
        btn5 = (ImageButton)findViewById(R.id.vbad);
        btnsub = (ImageButton)findViewById(R.id.submit);
        btnfeed = (ImageButton)findViewById(R.id.myfeedback);

        edt1 = (EditText)findViewById(R.id.enterEmail);
        edt2 = (EditText)findViewById(R.id.feedmsg);

    }

    @Override
    protected void onResume() {
        super.onResume();

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val6 = edt1.getText().toString();
                val7 = edt2.getText().toString();

                Intent int1 = new Intent(FeedBack.this, FeedBack2.class);
                int1.putExtra("email", val6);
                int1.putExtra("msg", val7);

                if(btn1.isPressed())
                {
                    val1 = "Excellent";
                    int1.putExtra("rate", val1);
                }
                else if(btn2.isPressed())
                {
                    val1 = "Good";
                    int1.putExtra("rate", val1);
                }
                else if(btn3.isPressed())
                {
                    val1 = "Don't Know";
                    int1.putExtra("rate", val1);
                }
                else if(btn4.isPressed())
                {
                    val1 = "Bad";
                    int1.putExtra("rate", val1);
                }
                else if(btn5.isPressed())
                {
                    val1 = "Very Bad";
                    int1.putExtra("rate", val1);
                }

                startActivity(int1);
            }
        });

        btnfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(FeedBack.this, FeedBack3.class);
                startActivity(int1);
            }
        });
    }
}
