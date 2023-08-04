package com.example.quizappsummer2023;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
/** NLOMELLI MESSAGE TO NICK YATES
 * 08/02/2023 I tried to create a query to the Firebase database but is not working, I read a lot
 * of documentation and did differents alternatives  but I am still getting the error.
 * 08/03/2023: Finnaly it's running...
 */

/**
 * Activity to demonstrate basic data querying. To start this Activity, run:
 * <code>adb shell am start -n com.google.firebase.quickstart.database/.QueryActivity</code>
 *
 * Use {@link MainActivity} to populate the Message data.
 */
public class QueryActivity extends AppCompatActivity {

    private static final String TAG = "QueryActivity";

    private DatabaseReference databaseReference;
    private DatabaseReference mMessagesRef;
    private Query mMessagesQuery;

    private ValueEventListener mMessagesListener;
    private ChildEventListener mMessagesQueryListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //nlm setContentView(R.layout.activity_query);

        // Get a reference to the Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference();

        Log.d(TAG, "end onCreate");

    }

    public String getUid() {
        return "42";
    }

    public void basicListen(ArrayList<Question> questionList) {
        // [START basic_listen]
        // Get a reference to Messages and attach a listener
        Log.d(TAG, "starting basicListen");
        mMessagesRef = FirebaseDatabase.getInstance().getReference("question");

        Log.d(TAG, "after databaseReference");

        mMessagesListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // New data at this path. This method will be called after every change in the
                // data at this path or a subpath.

                // Get the data as Questions objects

                Log.d(TAG, "Number of questions: " + dataSnapshot.getChildrenCount());
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.d(TAG, "Entro al for (DataSnap...");
                    // Extract a Questions object from the DataSnapshot

                    // This command shall work and it's failing
                    //Question readQ = child.getValue(Question.class);

                    // I used the old style...
                    String qRaw = child.toString();
                    Log.d(TAG, "question raw:" + qRaw);
                    int firstInd = qRaw.indexOf("key =") + 5;
                    int  lastInd = qRaw.indexOf(", value");
                    String keystr = qRaw.substring(firstInd, lastInd);
                    //int currentQ = Integer.parseInt(keystr);
                    //currentQ = Integer.parseInt(keystr);
                    Log.d(TAG, "conversion a int" + keystr);

                    firstInd = qRaw.indexOf("qHint=") + 6;
                    lastInd = qRaw.indexOf(", qPrompt=");
                    String hint = qRaw.substring(firstInd, lastInd);
                    firstInd = lastInd + 10;
                    lastInd = qRaw.indexOf(", correctAnswer=");
                    String prompt = qRaw.substring(firstInd, lastInd);
                    firstInd = lastInd + 16;
                    lastInd = qRaw.indexOf("} ");
                    String answer = qRaw.substring(firstInd, lastInd);
                    Boolean corans = answer.contentEquals("true");

                    Question readQ = new Question(prompt,hint,corans);

                    questionList.add(readQ);

                   //questionList.set(currentQ, readQ);
                    // Saving in the public array
                    //questions[currentQ] = readQ;
                    //Log.d(TAG, "It's working finally");
                    // Use the Message
                    // [START_EXCLUDE]
                    Log.d(TAG, keystr + " question promot:" + readQ.getqPrompt());
                    Log.d(TAG, "question hint:" + readQ.getqHint());
                    Log.d(TAG, "question answer:" + readQ.getCorrectAnswer());
                    // [END_EXCLUDE]
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Could not successfully listen for data, log the error
                Log.e(TAG, "messages:onCancelled:" + error.getMessage());
            }
        };
        mMessagesRef.addValueEventListener(mMessagesListener);
        // [END basic_listen]
    }

    public void basicQuery() {
        // [START basic_query]
        // My top posts by number of stars
        String myUserId = getUid();
        Query myTopPostsQuery = databaseReference.child("records").child(myUserId)
                .orderByChild("points");
        myTopPostsQuery.addChildEventListener(new ChildEventListener() {
            // TODO: implement the ChildEventListener methods as documented above
            // [START_EXCLUDE]
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, String s) { }
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, String s) { }
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, String s) { }
            public void onCancelled(@NonNull DatabaseError databaseError) { }
            // [END_EXCLUDE]
        });
        // [END basic_query]
    }

    public void basicQueryValueListener() {
        String myUserId = getUid();
        Query myTopPostsQuery = databaseReference.child("records").child(myUserId)
                .orderByChild("points");

        // [START basic_query_value_listener]
        // My top posts by number of stars
        myTopPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // TODO: handle the post
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        });
        // [END basic_query_value_listener]
    }

    public void cleanBasicListener() {
        // Clean up value listener
        // [START clean_basic_listen]
        mMessagesRef.removeEventListener(mMessagesListener);
        // [END clean_basic_listen]
    }

    public void cleanBasicQuery() {
        // Clean up query listener
        // [START clean_basic_query]
        mMessagesQuery.removeEventListener(mMessagesQueryListener);
        // [END clean_basic_query]
    }

    public void orderByNested() {
        // [START rtdb_order_by_nested]
        // Most viewed posts
        Query myMostViewedPostsQuery = databaseReference.child("records")
                .orderByChild("points");
        myMostViewedPostsQuery.addChildEventListener(new ChildEventListener() {
            // TODO: implement the ChildEventListener methods as documented above
            // [START_EXCLUDE]
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {}

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
            // [END_EXCLUDE]
        });
        // [END rtdb_order_by_nested]
    }

    private void childEventListenerRecycler() {
        final Context mContext = this;
        // [START child_event_listener_recycler]
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildAdded:" + dataSnapshot.getKey());

                // A new comment has been added, add it to the displayed list

                // ...
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildChanged:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so displayed the changed comment.
                //Comment newComment = dataSnapshot.getValue(Comment.class);
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved:" + dataSnapshot.getKey());

                // A comment has changed, use the key to determine if we are displaying this
                // comment and if so remove it.
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {
                Log.d(TAG, "onChildMoved:" + dataSnapshot.getKey());

                // A comment has changed position, use the key to determine if we are
                // displaying this comment and if so move it.
                //Comment movedComment = dataSnapshot.getValue(Comment.class);
                String commentKey = dataSnapshot.getKey();

                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "postComments:onCancelled", databaseError.toException());
                Toast.makeText(mContext, "Failed to load comments.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        databaseReference.addChildEventListener(childEventListener);
        // [END child_event_listener_recycler]
    }

    private void recentPostsQuery() {
        // [START recent_posts_query]
        // Last 100 posts, these are automatically the 100 most recent
        // due to sorting by push() keys
        Query recentPostsQuery = databaseReference.child("posts")
                .limitToFirst(100);
        // [END recent_posts_query]
    }

    @Override
    public void onStart() {
        super.onStart();
        basicQuery();
        basicQueryValueListener();
    }

    @Override
    public void onStop() {
        super.onStop();
        cleanBasicListener();
        cleanBasicQuery();
    }
}

