package com.example.madforumapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {

    EditText emailcheck;
    Button emailsend;

    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        emailcheck=findViewById(R.id.etcheckemail);

        emailsend=findViewById(R.id.btnemailsend);

        firebaseAuth=FirebaseAuth.getInstance();

        emailsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user_email=emailcheck.getText().toString().trim();

                if(user_email.equals(""))
                {
                    Toast.makeText(forgotpassword.this,"Please enter your registered emailID",Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(user_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful())
                            {
                                Toast.makeText(forgotpassword.this,"Reset email sent",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(forgotpassword.this,act2.class));
                            }else{

                                Toast.makeText(forgotpassword.this,"Errpr in sending reset password email",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
