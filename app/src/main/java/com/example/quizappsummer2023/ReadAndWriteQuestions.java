package com.example.quizappsummer2023;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ReadAndWriteQuestions {
    private static final String TAG = "ReadAndWriteQuestions";

    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    private DataSnapshot mDataSnapshot;

    // [END declare_database_ref]

     public  ReadAndWriteQuestions(DatabaseReference database) {
        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]
    }

    // [START rtdb_write_new_question]
    public void writeNewQuestion(String questionId, String prompt, String hint, Boolean answer) {
        Question question = new Question(prompt, hint, answer);

        mDatabase.child("question").child(questionId).setValue(question);


    }
    // [END rtdb_write_new_question]

    public void writeNewPost(String userId, String username, int questions, int points) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        // String key = mDatabase.child("posts").push().getKey();
        Post post = new Post(username, questions, points);
        mDatabase.child("records").child(userId).setValue(post);

        //Map<String, Object> postValues = post.toMap();

        //Map<String, Object> childUpdates = new HashMap<>();
        //childUpdates.put("/posts/" + key, postValues);
        //childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        //mDatabase.updateChildren(childUpdates);
    }
    // [END write_fan_out]

    // [START rtdb_read_question]

    public void writeNewQuestionWithTaskListeners(String questionId, String prompt, String hint, Boolean answer) {
        Question question = new Question(prompt, hint, answer);

        // [START rtdb_write_new_user_task]
        mDatabase.child("question").child(questionId).setValue(question)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        // ...
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
                    }
                });
        // [END rtdb_write_new_user_task]
    }

    private void addPostEventListener(DatabaseReference mPostReference) {
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Question question = dataSnapshot.getValue(Question.class);
                // ..
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mPostReference.addValueEventListener(postListener);
        // [END post_value_event_listener]
    }

    // [START write_fan_out]

    private String getUid() {
        return "";
    }


}
