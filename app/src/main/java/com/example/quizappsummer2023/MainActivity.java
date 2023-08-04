package com.example.quizappsummer2023;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView questionTV;
    Button trueBTN, falseBTN, nextBTN, hintBTN, newBTN;
    int score;
    String message, dataRecord, qKey;
    Question currentQ;
    ArrayList<Question> questionList;


    int currentIndex;
    public int numQ, maxQ;
    boolean ansFlag, addNewQ;

    // Teacher style
    //FirebaseDatabase database = FirebaseDatabase.getInstance();
    //DatabaseReference myRef = database.getReference("testApp");

    // Google examples
    //private DatabaseReference databaseReference;
    //private DatabaseReference myQuestionRef;
    //private Query myQuestionQuery;

    //private DataSnapshot myDataSnapshot;

    QueryActivity myQuery;
    DatabaseReference myRef;

    DatabaseReference questionRef;
    ReadAndWriteQuestions myQuestion = new ReadAndWriteQuestions(myRef);

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
        questionList = new ArrayList<>();
        Log.d("Main", "inicializa questionList");

        currentIndex = -1;
        currentQ = new Question("Click <start> for a new test", "Click <start> button", false);

        Log.d("Main", "agrega primera pregunta");


        /* NLOMELLI MESSAGE
        On next line I was trying to add a new question to firebase but I got an error.
         */
        //myQuestion.writeNewQuestion("5", "Do you like Java", "Java it's a great language to learn", true);
        //myQuestion.writeNewQuestion("6", "Do you like Rust", "Rust is for martians", false);
        //myQuestion.writeNewPost("2","nlomelli",6,6);
        //Firebase writing a record
        //dataRecord = q5.toString();
        //myRef.setValue(dataRecord);
        //qKey = myRef.push().getKey();
        //myRef.child("questions").child(qKey).child(q2.getqPrompt()).setValue(q2);
        //databaseReference = FirebaseDatabase.getInstance().getReference();
        // Get a reference to Messages and attach a listener
        //myQuestionRef = databaseReference.child("questiom");
        //Log.d(TAG, "Number of messages: " + myDataSnapshot.getChildrenCount());
        //q4 = child.getValue(Question.class);

        myQuery = new QueryActivity();

        Log.d("Main", "despues de crear myQuery");

        // Reading the database for questions.
        myQuery.basicListen(questionList);
        Log.d("Main", "despues de crear basicListen");

        //questionList.set(currentIndex, currentQ);
        //currentQ = questionList.get(currentIndex);

        questionTV.setText(currentQ.getqPrompt());


        falseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ansFlag && (currentIndex >=0)) {
                    if (currentQ.getCorrectAnswer() == false) {
                        message = getString(R.string.rightMsg);
                        score++;

                    } else {
                        message = getString(R.string.wrongMsg);
                    }
                    ansFlag = true;
                } else if (currentIndex >=0){
                    message = getString(R.string.alreadyAns);
                } else {
                    message = "Click <start> :(";
                }

                Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();
            }
        });
        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ansFlag && (currentIndex >=0)) {
                    if (currentQ.getCorrectAnswer() == true) {
                        message = getString(R.string.rightMsg);
                        score++;

                    } else  {
                        message = getString(R.string.wrongMsg);
                    }
                    ansFlag = true;
                } else if (currentIndex >=0) {
                    message = getString(R.string.alreadyAns);
                } else {
                    message = "Click <start> :(";
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
                if (currentIndex == -1) {
                    // Is going to start the test, change the botton text.
                    nextBTN.setText("NEXT");
                }
                currentIndex++;
                ansFlag = false;
                numQ = questionList.size();
                Log.d("Main", "size del array" + numQ);

                if (currentIndex >= numQ) {
                    Intent scoreIntent = new Intent(MainActivity.this, ScoreActivity.class);
                    scoreIntent.putExtra("param1", score);
                    startActivity(scoreIntent);
                    currentIndex = 0;
                    score = 0;
                }
                currentQ = questionList.get(currentIndex);
                questionTV.setText(currentQ.getqPrompt());

            }
        });
        newBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent newIntent = new Intent(MainActivity.this, AddQuestion.class);
                    newIntent.putExtra("nextIndex", questionList.size());
                    startActivity(newIntent);
            }
        });

        Log.d("Main", "termina on create main");

    }

}