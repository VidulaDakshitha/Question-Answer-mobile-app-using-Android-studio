package com.example.madforumapp;

public class Reply {
    private  int id;
    private  String userName;
    private String userImage;
    private  String reply_user_name;
    private  String reply_messge;
    private String reply_date;


    public Reply(int id, String userName, String userImage, String reply_user_name, String reply_messge, String reply_date) {
        this.id = id;
        this.userName = userName;
        this.userImage = userImage;
        this.reply_user_name = reply_user_name;
        this.reply_messge = reply_messge;
        this.reply_date = reply_date;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public String getReply_user_name() {
        return reply_user_name;
    }

    public String getReply_messge() {
        return reply_messge;
    }

    public String getReply_date() {
        return reply_date;
    }
}
