package com.example.madforumapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class act2 extends AppCompatActivity {

    private TextView register,forgotp,info;
    private ImageButton btnLogin;
    private EditText email,password;
    private int counter=5;

    private FirebaseAuth firebaseAuth;


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

        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user=firebaseAuth.getCurrentUser();

       /* if(user!=null)
        {
            finish();
            startActivity(new Intent(act2.this,MainFeed.class));
        }*/
    }

    public void validate(String userName,String userPassword)
    {
       /* if(userName.equals("Admin")&&userPassword.equals("1234"))
        {
            startActivity(new Intent(act2.this,personalprofile.class));
        }*/
        firebaseAuth.signInWithEmailAndPassword(userName,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(act2.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(act2.this,MainFeed.class));
                }else{

                    Toast.makeText(act2.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    counter--;

                    info.setText("Login Attempts remaining:" + String.valueOf(counter));

                    if(counter==0)
                    {
                        btnLogin.setEnabled(false);
                    }

                }

            }
        });


       /* if((userName.equals("Admin")&&(userPassword.equals("1234")))) {
            Intent mainFeedIntent = new Intent(act2.this, MainFeed.class);
            startActivity(mainFeedIntent);
        }else{

            counter--;

            info.setText("Login Attempts remaining:" + String.valueOf(counter));

            if(counter==0)
            {
                btnLogin.setEnabled(false);
            }

        }*/

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
