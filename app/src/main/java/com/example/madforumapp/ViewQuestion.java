package com.example.madforumapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewQuestion extends AppCompatActivity {

    TextView title, description, author;
    EditText replyText;
    Button replyButton;

    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_question);

        Intent intent = getIntent();
        final int id = intent.getExtras().getInt("question_id");
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        replyButton=findViewById(R.id.addReply);
        replyText=findViewById(R.id.replyText);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference dbPostRef = FirebaseDatabase.getInstance().getReference("posts").child(String.valueOf(id)).getRef();
        dbPostRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                title.setText(dataSnapshot.child("title").getValue().toString());
                description.setText(dataSnapshot.child("description").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ListView answerList= findViewById(R.id.answerList);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference dbRef = database.getReference("answers").child(String.valueOf(id)).getRef();


        FirebaseListAdapter<Answer> firebaseListAdapter = new FirebaseListAdapter<Answer>(
                ViewQuestion.this,
                Answer.class,
                R.layout.answer_layout,
                dbRef
        ) {
            @Override
            protected void populateView(View v, final Answer model, int position) {

                TextView answer = v.findViewById(R.id.txtAnswer);
                TextView author = v.findViewById(R.id.txtAnswerBy);
                ImageButton btnDelete = v.findViewById(R.id.btnDelete);
                //;
                answer.setText(model.getDescription());
                author.setText("by "+model.getAuthor());

                if( model.getAuthor().equalsIgnoreCase(currentUser.getEmail().toString()) ){
                    btnDelete.setVisibility(View.VISIBLE);
                    btnDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference dbRef = database.getReference("answers");
                            dbRef.child(String.valueOf(id)).child(String.valueOf(model.getId())).removeValue();
                        }
                    });
                }
                else btnDelete.setVisibility(View.INVISIBLE);

            }
        };

        answerList.setAdapter(firebaseListAdapter);


        replyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(replyText.getText().toString().isEmpty()){
                    Toast.makeText(ViewQuestion.this, "Answer field cannot be left empty!", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference dbRef = database.getReference("answers");


                dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int num = (int)dataSnapshot.child(String.valueOf(id)).getChildrenCount();
                        //FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("answers").child(String.valueOf(id)).child(String.valueOf(num));

                        Answer a = new Answer(num,  replyText.getText().toString(), currentUser.getEmail());

                        ref.setValue(a);
                        Toast.makeText(ViewQuestion.this, "Posted Successfully!", Toast.LENGTH_SHORT).show();
                        replyText.setText("");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
//        Toast.makeText(this, "ID: "+, Toast.LENGTH_SHORT).show();
    }
}
