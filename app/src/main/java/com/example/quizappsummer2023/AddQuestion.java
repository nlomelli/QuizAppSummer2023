package com.example.quizappsummer2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddQuestion extends AppCompatActivity {
    TextView myTextView;
    EditText myEditQuestion, myEditHint, myEditAnswer;

    Button submitQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        myTextView = (TextView) findViewById(R.id.greeting);
        myEditQuestion = (EditText) findViewById(R.id.typeQuestion);
        myEditHint = (EditText) findViewById(R.id.typeHint);
        myEditAnswer = (EditText) findViewById(R.id.typeAnswer);
        submitQuestion = (Button) findViewById(R.id.submitQuestion);

        submitQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String question = myEditQuestion.getText().toString();
                String hint = myEditHint.getText().toString();
                String answer = myEditAnswer.getText().toString();
                myTextView.setText("Submmiting...");
            }
        });

    }

}