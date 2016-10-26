package com.area51.ayush.codequiz;

/**
 * Created by Ayush on 26-10-2016.
 */

public class QuizQuestion {
    private int quizId;
    private int questionId;
    private String question;

    public QuizQuestion() {

    }

    public QuizQuestion(int quizId, int questionId, String question) {
        this.quizId = quizId;
        this.questionId = questionId;
        this.question = question;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuizId() {
        return quizId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }
}