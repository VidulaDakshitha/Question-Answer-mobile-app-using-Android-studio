package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReplyDashbord extends AppCompatActivity {
    String[]UserName={"sansa","aryya","osuri","sathira"} ;
    String [] Reply_messge={"10000","555555","95656898","9999"};
    ListView replyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply_dashbord);
        replyList=findViewById(R.id.replyListView);

        ArrayList<Reply> replies=new ArrayList<>();
        replies.add(new Reply(1,"sathira","gghhd","sdfddffsg","sfsferef eafeae","Sep 23"));
        replies.add(new Reply(1,"osuri","lfere","sansa","fuck me hard","Sep 23"));

        ReplyListAdapter replyListAdapter=new ReplyListAdapter(this,replies);
        replyList.setAdapter(replyListAdapter);



    }


}
