package com.example.quizappsummer2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView questionTV;
    Button trueBTN, falseBTN, nextBTN, hintBTN, newBTN;
    int score;
    String message;
    Question q1, q2, q3, q4, q5, currentQ;
    Question[] questions;
    int currentIndex;
    boolean ansFlag;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTV = (TextView) findViewById(R.id.questionTV);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        trueBTN = (Button) findViewById(R.id.trueBTN);
        hintBTN = (Button) findViewById(R.id.hintBTN);
        nextBTN = (Button) findViewById(R.id.nextBTN);
        newBTN = (Button) findViewById(R.id.newBTN);
        score = 0;
        ansFlag = false;
        message = "";
        q1 = new Question(getString(R.string.q1Text), getString(R.string.q1Hint), false);
        q2 = new Question(getString(R.string.q2Text), getString(R.string.q2Hint), true);
        q3 = new Question(getString(R.string.q3Text),getString(R.string.q3Hint), true);
        q4 = new Question(getString(R.string.q4Text), getString(R.string.q4Hint),true);
        q5 = new Question(getString(R.string.q5Text), getString(R.string.q5Hint), false);
        currentIndex = 0;
        currentQ = q1;
        questions = new Question[] {q1, q2, q3, q4, q5};

        falseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ansFlag) {
                    if (currentQ.getCorrectAnswer() == false) {
                        message = getString(R.string.rightMsg);
                        score++;

                    } else {
                        message = getString(R.string.wrongMsg);
                    }
                    ansFlag = true;
                } else {
                    message = getString(R.string.alreadyAns);
                }

                Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();
            }
        });
        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ansFlag) {
                    if (currentQ.getCorrectAnswer() == true) {
                        message = getString(R.string.rightMsg);
                        score++;

                    } else {
                        message = getString(R.string.wrongMsg);
                    }
                    ansFlag = true;
                } else {
                    message = getString(R.string.alreadyAns);
                }

                Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();
            }
        });
        hintBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = currentQ.getqHint();
                Toast.makeText(MainActivity.this,message, Toast.LENGTH_LONG).show();
            }
        });
        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                ansFlag = false;
                if (currentIndex > 4) {
                    Intent scoreIntent = new Intent(MainActivity.this, ScoreActivity.class);
                    scoreIntent.putExtra("param1", score);
                    startActivity(scoreIntent);
                    currentIndex = 0;
                    score = 0;
                }
                currentQ = questions[currentIndex];
                questionTV.setText(currentQ.getqPrompt());

            }
        });

    }
}