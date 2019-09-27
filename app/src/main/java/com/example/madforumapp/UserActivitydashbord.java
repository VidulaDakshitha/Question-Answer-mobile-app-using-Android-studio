package com.example.madforumapp;

import android.content.Intent;
import android.os.Bundle;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class UserActivitydashbord extends AppCompatActivity {

    TextView replyDashboard1, replyDashboard2;
    WebView replyDashWeb;
    AnyChartView chartViews;
    static int  reply_count ;
    int messge_count ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activitydashbord);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        replyDashboard1 = findViewById(R.id.replyDashboardText);
        replyDashboard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivitydashbord.this, ReplyDashbord.class);
                startActivity(intent);
            }
        });

        replyDashboard2 = findViewById(R.id.replyDashboardTextView);
        replyDashboard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserActivitydashbord.this, ReplyDashbord.class);
                startActivity(intent);
            }
        });
       // getReplyCount();
        chartViews = findViewById(R.id.chatwindow);
         getReplyCount();
         for(int witing=0;witing<100;witing++)

            setupChart();



        // replyDashWeb=findViewById(R.id.reply_webview);
        // replyDashWeb.loadUrl("assets/chart.html");


    }

    @Override
    protected void onStart() {
        super.onStart();

        for(int witing=0;witing<100;witing++)

        setupChart();
    }

    public boolean getReplyCount() {


        FirebaseDatabase getCountdb_reply = FirebaseDatabase.getInstance();
        DatabaseReference getcount_reply = getCountdb_reply.getReference("replies");

        getcount_reply.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){

                    String username=ds.child("username").getValue().toString();
                                      //need to username
                    if (username.equals("sansa")) {
                        reply_count=reply_count+1;
                        Log.i("count",String.valueOf(reply_count));

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                reply_count=0;
            }
        });

        return  false;
    }
    public void setupChart() {
        String[] x = {"Post", "Replies"};
        int[] y = {1, reply_count};

        List<DataEntry> dataEntries = new ArrayList<>();

        Cartesian pie = AnyChart.bar();
        for (int i = 0; i < x.length; i++) {
            dataEntries.add(new ValueDataEntry(x[i], y[i]));
        }
        pie.background().fill("#FFB600");
        pie.bar(dataEntries).color("#FFE39E");
        chartViews.setChart(pie);
        Log.i("count_chart",String.valueOf(reply_count));
    }


}
