package com.example.madforumapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.List;

public class QuestionListAdapter extends ArrayAdapter<Question> {

    public QuestionListAdapter(Context context, List<Question> questions) {

        super(context, 0, questions);

    }



    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        Question question = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.question_layout, parent, false);

        }

        // Lookup view for data population

        TextView titleTextView = convertView.findViewById(R.id.question_title);
        TextView authorTextView = convertView.findViewById(R.id.question_author);
        TextView questionViews = convertView.findViewById(R.id.view);


        // Populate the data into the template view using the data object

        titleTextView.setText(question.getTitle());
        authorTextView.setText(question.getAuthor());
        questionViews.setText(question.getViews());

        // Return the completed view to render on screen

        return convertView;

    }
}
