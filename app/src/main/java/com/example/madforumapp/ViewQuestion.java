package com.example.madforumapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class ViewQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_question);

        Intent intent = getIntent();

        Toast.makeText(this, "ID: "+intent.getExtras().getInt("question_id"), Toast.LENGTH_SHORT).show();
    }
}
