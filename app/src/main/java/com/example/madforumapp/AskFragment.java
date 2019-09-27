package com.example.madforumapp;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class AskFragment extends Fragment {
    EditText title, description;
    Button btnPost;
    long num=0;

    public AskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ask, container, false);

        title = view.findViewById(R.id.questionTitle);
        description = view.findViewById(R.id.questionDescription);
        btnPost = view.findViewById(R.id.btnPostNewQuestion);

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference dbRef = database.getReference("posts");


                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        num = dataSnapshot.getChildrenCount();

                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("posts").child(Long.toString(num));

                        Question q = new Question((int)num, title.getText().toString(), currentUser.getEmail(), description.getText().toString());

                        ref.setValue(q);
                        Toast.makeText(getActivity().getApplicationContext(), "Posted Successfully!", Toast.LENGTH_SHORT).show();
                        ((MainFeed)getActivity()).ChangeToHome();
                        title.setText("");
                        description.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        // Inflate the layout for this fragment
        return view;
    }

}
