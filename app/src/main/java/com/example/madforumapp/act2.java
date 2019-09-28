package com.example.madforumapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
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
    private ProgressDialog progressDialog;

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

        progressDialog =new ProgressDialog(this);
       /* if(user!=null)
        {
            finish();
            startActivity(new Intent(act2.this,MainFeed.class));
        }*/
    }

    public void validate(String userName,String userPassword)
    {
        progressDialog.setMessage("Your Account has been successfully Verified through Email");
        progressDialog.show();
        if(validate()) {

            firebaseAuth.signInWithEmailAndPassword(userName, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                      //  Toast.makeText(act2.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(act2.this, MainFeed.class));
                        checkEmailVerification();
                    } else {
                        progressDialog.dismiss();

                        Toast.makeText(act2.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        counter--;

                        info.setText("Login Attempts remaining:" + String.valueOf(counter));

                        if (counter == 0) {
                            btnLogin.setEnabled(false);
                        }

                    }

                }
            });
        }


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

    private Boolean validate()
    {
        Boolean result = false;
        String val_email=email.getText().toString();
        String val_password=password.getText().toString();
        if(val_email.isEmpty() || val_password.isEmpty())
        {
            Context context=getApplicationContext();
            LayoutInflater inflater=getLayoutInflater();
            View customToastroot=inflater.inflate(R.layout.emptyfield_toast,null);
            Toast customToast=new Toast(context);

            customToast.setView(customToastroot);
            customToast.setDuration(Toast.LENGTH_LONG);
            customToast.show();

        }
        else{

            result=true;
        }
        return result;
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

    private void checkEmailVerification()
    {
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        Boolean email = firebaseUser.isEmailVerified();

        if(email)
        {
            finish();
            Toast.makeText(act2.this, "Login Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(act2.this,MainFeed.class));
        }else{
            Toast.makeText(act2.this,"Please verify by clicking link sent to your mail",Toast.LENGTH_LONG).show();
            firebaseAuth.signOut();
        }
    }
}
