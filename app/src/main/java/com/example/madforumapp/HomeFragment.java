package com.example.madforumapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView mainListView = view.findViewById(R.id.question_listview);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference dbRef = database.getReference("posts");

        FirebaseListAdapter<Question> firebaseListAdapter = new FirebaseListAdapter<Question>(
                this.getActivity(),
                Question.class,
                R.layout.question_layout,
                dbRef
        ) {
            @Override
            protected void populateView(View v, Question model, int position) {

                TextView questionTitle = v.findViewById(R.id.question_title);
                TextView questionAuthor = v.findViewById(R.id.question_author);
                TextView questionDescription = v.findViewById(R.id.question_description);

                questionTitle.setText(model.getTitle());
                questionAuthor.setText("by "+model.getAuthor());
                questionDescription.setText(model.getDescription());

            }
        };

        mainListView.setAdapter(firebaseListAdapter);


        // Inflate the layout for this fragment
        return view;
    }

}
