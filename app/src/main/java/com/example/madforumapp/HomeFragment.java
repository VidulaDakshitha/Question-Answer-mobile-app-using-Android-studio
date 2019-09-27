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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private List<Question> questionList = new ArrayList<>();

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
                TextView questionViews = v.findViewById(R.id.views);

                questionTitle.setText(model.getTitle());
                questionAuthor.setText("by "+model.getAuthor());
                questionViews.setText(String.valueOf(model.getViews()));

            }
        };

        mainListView.setAdapter(firebaseListAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("posts").child(String.valueOf(i));

                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int viewCount = Integer.parseInt(dataSnapshot.child("views").getValue().toString());
                        dbRef.child("views").setValue(viewCount+1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

//        final QuestionListAdapter questionListAdapter = new QuestionListAdapter(getContext(),questionList);
//        mainListView.setAdapter(questionListAdapter);
//
//        dbRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                questionList.remove(dataSnapshot.getValue(Question.class));
//                questionList.add(dataSnapshot.getValue(Question.class));
//                Collections.reverse(questionList);
//                questionListAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//                questionList.remove(dataSnapshot.getValue(Question.class));
//                Collections.reverse(questionList);
//                questionListAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        // Inflate the layout for this fragment
        return view;
    }

}
