package com.example.quizappsummer2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {
    TextView scoreTV;
    Button sendBTN;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        scoreTV = (TextView) findViewById(R.id.scoreTV);
        sendBTN = (Button) findViewById(R.id.sendBTN);

        Intent incomingIntent = getIntent();
        score = incomingIntent.getIntExtra("param1", -10);
        scoreTV.setText("SCORE = "+score);
        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subject = "New score on my Android test app.";
                String body = "I just got a score of "+score+" in my Android test app";
                composeEmail(subject, body);
            }
        });
    }
    private void composeEmail(String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}