package com.example.quizappsummer2023;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

// [START post_class]
@IgnoreExtraProperties
public class Post {

    public String nickname;
    public int questions;
    public int points;

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String author, int questions, int points) {
        this.nickname = author;
        this.questions = questions;
        this.points = points;
    }

}
// [END post_class]
