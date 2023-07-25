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
    Button trueBTN, falseBTN, scoreBTN;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTV = (TextView) findViewById(R.id.questionTV);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        trueBTN = (Button) findViewById(R.id.trueBTN);
        scoreBTN = (Button) findViewById(R.id.scoreBTN);
        score = 0;

        falseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                CharSequence message = "You are correct! good job";
                Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();

            }
        });
        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence message = "You are wrong! TRY AGAIN";
                Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();

            }
        });
        scoreBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoreIntent = new Intent(MainActivity.this, ScoreActivity.class);
                scoreIntent.putExtra("param1", score);
                startActivity(scoreIntent);
            }
        });

    }
}