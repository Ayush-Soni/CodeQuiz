package com.area51.ayush.codequiz;

/**
 * Created by Ayush on 26-10-2016.
 */

public class QuizDetails {
    private int quizId;
    private String quizTitle;

    public QuizDetails() {

    }

    public QuizDetails(int qId, String qTitle) {
        quizId = qId;
        qTitle = quizTitle;
    }

    public void setQuizId(int qId) {
        quizId = qId;
    }

    public int getQuizId(){
        return quizId;
    }

    public void setQuizTitle(String qTitle) {
        quizTitle = qTitle;
    }

    public String getQuizTitle() {
        return quizTitle;
    }
}
