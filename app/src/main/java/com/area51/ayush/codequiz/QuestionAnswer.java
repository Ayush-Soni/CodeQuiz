package com.area51.ayush.codequiz;

/**
 * Created by Ayush on 31-10-2016.
 */

public class QuestionAnswer {
    private int questionId;
    private int answerId;

    QuestionAnswer() {

    }

    QuestionAnswer(int questionId, int answerId) {
        this.answerId = answerId;
        this.questionId = questionId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public int getQuestionId() {
        return questionId;
    }
}
