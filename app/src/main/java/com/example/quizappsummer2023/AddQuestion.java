package com.example.quizappsummer2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class AddQuestion extends AppCompatActivity {
    TextView myTextView;
    EditText myEditQuestion, myEditHint;
    Switch myEditAnswer;

    Button submitQuestion;

    int newIndex;

    DatabaseReference myRef;

    ReadAndWriteQuestions myQuestion = new ReadAndWriteQuestions(myRef);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        myTextView = (TextView) findViewById(R.id.greeting);
        myEditQuestion = (EditText) findViewById(R.id.typeQuestion);
        myEditHint = (EditText) findViewById(R.id.typeHint);
        myEditAnswer = (Switch) findViewById(R.id.answerSW);
        submitQuestion = (Button) findViewById(R.id.submitQuestion);
        Intent incomingIntent = getIntent();
        newIndex = incomingIntent.getIntExtra("nextIndex", -10);

        submitQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = myEditQuestion.getText().toString();
                String hint = myEditHint.getText().toString();
                Boolean answer = myEditAnswer.isChecked();
                myTextView.setText("Submmiting");

                myQuestion.writeNewQuestion(String.valueOf(newIndex), question, hint, answer);
                Intent mainIntent = new Intent(AddQuestion.this, MainActivity.class);
                startActivity(mainIntent);

            }
        });

    }

}