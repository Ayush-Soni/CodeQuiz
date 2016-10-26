package com.area51.ayush.codequiz;

/**
 * Created by Ayush on 26-10-2016.
 */

public class QuizzesTaken {
    private String username;
    private int quizId;

    public QuizzesTaken() {

    }

    public QuizzesTaken(String username, int quizId) {
        this.username = username;
        this.quizId = quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getQuizId() {
        return quizId;
    }

    public String getUsername() {
        return username;
    }
}
