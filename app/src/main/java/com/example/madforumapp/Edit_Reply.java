package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Edit_Reply extends AppCompatActivity {
    TextView reply_to;
    TextView reply_date_edit;
    EditText edit_reply;
    Button new_reply;
    String [] getReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__reply);

        reply_to=findViewById(R.id.reply_to_show);
        new_reply=findViewById(R.id.update_reply);
        edit_reply=findViewById(R.id.replytxt);
        reply_date_edit=findViewById(R.id.reply_date_edit);

        Intent getEditable_Reply = getIntent();
        getReply=getEditable_Reply.getStringArrayExtra("Editebale_reply");

        reply_to.setText(getReply[4]);
        edit_reply.setText(getReply[2]);
        reply_date_edit.setText(getReply[3]);

        new_reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String new_reply=edit_reply.getText().toString();

                ReplyDbManipulator dbManipulator=new ReplyDbManipulator();
                dbManipulator.EditReply(getReply[0],new_reply);
                finish();

            }
        });



    }
}
