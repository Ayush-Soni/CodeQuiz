package com.area51.ayush.codequiz;

/**
 * Created by Ayush on 26-10-2016.
 */

public class AnswerDetails {
    private int answerId;
    private int questionId;
    private String answer;

    public AnswerDetails() {

    }

    public AnswerDetails(int ansId, int qId, String ans) {
        answerId = ansId;
        questionId = qId;
        answer = ans;
    }

    public void setAnswerId(int ansId) {
        answerId = ansId;
    }

    public void setQuestionId(int qId) {
        questionId = qId;
    }

    public void setAnswer(String ans) {
        answer = ans;
    }

    public int getAnswerId() {
        return answerId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getAnswer() {
        return answer;
    }
}
