package com.example.madforumapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class personalprofile extends AppCompatActivity {

    private EditText name,email,phone;


    private ImageView feedbackimg;

   private  Button changePassword,editbtn,deletebtn,signoutbtn;

    private TextView moveToDashboardBtn;
    private ImageView moveToDashboardImage;

    private FirebaseUser firebaseUser;

    private FirebaseAuth firebaseAuth;



    String mydata="";
    String data[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalprofile);


        Log.d("mydata","database retrieve");

        signoutbtn=findViewById(R.id.btnsignout);

        changePassword=findViewById(R.id.btnprofchangepass);

        editbtn=findViewById(R.id.btnEditinfo);

        deletebtn=findViewById(R.id.btnDeleteAcc);

        feedbackimg=findViewById(R.id.clickfeedback);

        name=findViewById(R.id.etprofName);

        email=findViewById(R.id.etprofEmail);

        phone=findViewById(R.id.etprofPhone);

        moveToDashboardBtn = findViewById(R.id.rtrdashboard2);

        moveToDashboardImage = findViewById(R.id.rtrdashboard);

        firebaseAuth=FirebaseAuth.getInstance();

        firebaseUser=firebaseAuth.getCurrentUser();

        try{

            FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();

            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

            DatabaseReference databaseReference1=databaseReference.child("users");

            String id= firebaseAuth.getUid();

            databaseReference1.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for(DataSnapshot user :dataSnapshot.getChildren() ){

                        mydata+=user.getValue().toString()+"/";

                        Log.d("mydata",user.getValue().toString());

                    }

                    data=mydata.split("/");

                    email.setText(data[0]);
                    name.setText(data[1]);
                    phone.setText(data[2]);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(personalprofile.this,databaseError.getCode(),Toast.LENGTH_SHORT);

                }
            });

        }catch (Exception e){
            Log.d("mydata","firebase error"+e);
        }

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String user_name= name.getText().toString().trim();
                final String user_email=email.getText().toString().trim();
                final String user_phone=phone.getText().toString().trim();

                user User=new user(

                        user_name,
                        user_email,
                        user_phone



                );
                FirebaseDatabase.getInstance().getReference("users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .setValue(User);



                Toast.makeText(personalprofile.this,"Data successfully updated",Toast.LENGTH_SHORT).show();


            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(personalprofile.this);
                dialog.setTitle("Do you want to delete Account?");
                dialog.setMessage("Deleting this account will result in completely removing your account and you cannot login again");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(personalprofile.this,"Account Deleted",Toast.LENGTH_LONG).show();

                                    Intent intent=new Intent(personalprofile.this,act2.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(personalprofile.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                });
                dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog=dialog.create();
                alertDialog.show();
            }
        });






      /*  changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //   firebaseAuth=FirebaseAuth.getInstance();

                //firebaseUser=firebaseAuth.getCurrentUser();


               final AlertDialog.Builder dialog=new AlertDialog.Builder(personalprofile.this);
                dialog.setTitle("Do you want to delete Account?");
                dialog.setMessage("Deleting this account will result in completely removing your account and you cannot login again");
                dialog.setPositiveButton("Dialog", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        firebaseUser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful())
                                {
                                    Toast.makeText(personalprofile.this,"Account deleted successfully",Toast.LENGTH_LONG).show();

                                    Intent i=new Intent(personalprofile.this,act2.class);

                                   i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                    startActivity(i);
                                }

                            }
                        });

                      dialog.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                            }
                        });

                        AlertDialog alertDialog=dialog.create();
                        alertDialog.show();

                    }
                });
            }
        });*/







    }

    @Override
    protected void onResume() {
        super.onResume();


        feedbackimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i3=new Intent(personalprofile.this,FeedBack.class);

                startActivity(i3);
            }
        });


        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

         FirebaseAuth.getInstance().signOut();
         Intent intent=new Intent(personalprofile.this,act2.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);

            }
        });


        moveToDashboardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userDashbaord = new Intent(personalprofile.this, UserActivitydashbord.class);
                startActivity(userDashbaord);
            }
        });

        moveToDashboardImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent userDashbaord = new Intent(personalprofile.this, UserActivitydashbord.class);
                startActivity(userDashbaord);
            }
        });


        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent change = new Intent(personalprofile.this, forgotpassword.class);
                startActivity(change);
            }
        });


    }
}
