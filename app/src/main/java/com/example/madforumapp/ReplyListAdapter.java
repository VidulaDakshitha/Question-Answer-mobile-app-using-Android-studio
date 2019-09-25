package com.example.madforumapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReplyListAdapter extends BaseAdapter {
    Context context;
    ArrayList <Reply> replyArrayList;

    public ReplyListAdapter(Context context, ArrayList<Reply> replyArrayList) {
        this.context = context;
        this.replyArrayList = replyArrayList;
    }

    @Override
    public int getCount() {
        return replyArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.reply_dashbord_reply_row,viewGroup,false);

        //get taxtview from ListView
        TextView UserName=view.findViewById(R.id.user_name);
      //  TextView UserImage=view.findViewById(R.id.userimage);
        TextView ReplyMessage=view.findViewById(R.id.reply_messge);
        TextView ReplyUserName=view.findViewById(R.id.reply_user_name);
        TextView ReplayDate=view.findViewById(R.id.reply_date);
        //set text
        UserName.setText(replyArrayList.get(i).getUserName());
        ReplyMessage.setText(replyArrayList.get(i).getReply_messge());
        ReplayDate.setText(replyArrayList.get(i).getReply_date());
        ReplyUserName.setText("@"+replyArrayList.get(i).getReply_user_name());


        return view;
    }
}
