package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReplyDashbord extends AppCompatActivity {

    ListView replyList;
    ReplyDbManipulator replies;
    EditText post_text;
    ArrayList<Reply> getReplyfirbse;
    ReplyListAdapter replyListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_reply_dashbord);

        replyList=findViewById(R.id.replyListView);
       post_text=findViewById(R.id.textuserreply);







    }

    @Override
    protected void onStart() {
        super.onStart();
        replies=new ReplyDbManipulator();
        getReplyfirbse=replies.getReplylist();
        replyListAdapter=new ReplyListAdapter(this,getReplyfirbse);
        replyList.setAdapter(replyListAdapter);

           }

    @Override
    protected void onResume() {
        super.onResume();

        replyList.setAdapter(replyListAdapter);
    }

    public void onaddNewPost(View view){
        Intent intent =new Intent(this,MainFeed.class);
        startActivity(intent);
    }
}
