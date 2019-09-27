package com.example.madforumapp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReplyDbManipulator {

    private ArrayList<Reply> replyList =new ArrayList<>();
    private Reply user_replies;

    public ReplyDbManipulator() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("replies");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    String username=ds.child("username").getValue().toString();
                    String reply_to=ds.child("reply_to").getValue().toString();
                    String reply_text=ds.child("reply_text").getValue().toString();
                    String date=ds.child("date").getValue().toString();

                    String reply_id=ds.getKey();
                    user_replies=new Reply(reply_id,username,reply_to,reply_text,date);
                    //need to username
                    if (username.equals("sansa")) {
                        replyList.add(user_replies);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void ReplyDelete(String reply_id){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference deleteref = database.getReference("replies");
        deleteref.child(reply_id).removeValue();

        Log.i("delete","Reply deleted successful :"+String.valueOf(reply_id));
    }

    public void EditReply(String reply_id,String Edit_message){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference edit_reply = database.getReference("replies");
        edit_reply.child(reply_id).child("reply_text").setValue(Edit_message);
    }




    public ArrayList getReplylist(){

        return replyList;
    }

}