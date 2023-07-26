package com.example.quizappsummer2023;

public class Question {
    private String qPrompt;
    private String qHint;
    private boolean correctAnswer;

    public Question(String qPrompt, String qHint, boolean correctAnswer) {
        this.qPrompt = qPrompt;
        this.qHint = qHint;
        this.correctAnswer = correctAnswer;
    }

    public String getqPrompt() {
        return qPrompt;
    }

    public String getqHint() {
        return qHint;
    }

    public void setqHint(String qHint) {
        this.qHint = qHint;
    }

    public void setqPrompt(String qPrompt) {
        this.qPrompt = qPrompt;
    }

    public boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "qPrompt='" + qPrompt + '\'' +
                ", qHint='" + qHint + '\'' +
                ", correctAnswer=" + correctAnswer +
                '}';
    }

}
