package com.area51.ayush.codequiz;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {
    RadioGroup radioGroup;
    int currentQuestionID;
    int currentQuestionNo = 0;
    TextView textView;
    int quizId;
    Intent intent;
    DatabaseHelper db;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    ArrayList<QuizQuestion> allQuestionsForQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        radioGroup = (RadioGroup) findViewById(R.id.optionsRadioGroup);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById((R.id.toolbar_layout));
        setSupportActionBar(toolbar);
        db = new DatabaseHelper(getApplicationContext());
        intent = getIntent();
        textView = (TextView) findViewById(R.id.question_text_view);
        quizId = intent.getIntExtra("quizID",0);
        allQuestionsForQuiz = db.getAllQuestionsOfQuiz(quizId);

        Log.i("","****************************Question: "+allQuestionsForQuiz.get(currentQuestionNo).getQuestion());
        Log.i("", "***************************Current question id: "+ currentQuestionID);
        if(allQuestionsForQuiz.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No questions for quizId: "+ quizId, Toast.LENGTH_SHORT).show();
        }
        else {
                if (currentQuestionNo < allQuestionsForQuiz.size()) {
                    updateQuestion(currentQuestionNo);
                }
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabEnd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestionNo++;
                if(currentQuestionNo<allQuestionsForQuiz.size()) {
                    Snackbar.make(view, "Next question.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    updateQuestion(currentQuestionNo);
                }
                else {
                    Snackbar.make(view, "This is the last question.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    currentQuestionNo--;
                }
            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fabStart);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestionNo--;
                if(currentQuestionNo>=0) {
                    Snackbar.make(view, "Previous question.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    updateQuestion(currentQuestionNo);
                }
                else {
                    Snackbar.make(view, "This is the first question.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    currentQuestionNo++;
                }
            }
        });
    }

    void updateQuestion(int currentQuestionNo) {
        currentQuestionID = allQuestionsForQuiz.get(currentQuestionNo).getQuestionId();
        ArrayList<AnswerDetails> allAnswersForQuestion = db.getAnswersForQuestion(currentQuestionID);
        collapsingToolbarLayout.setTitle(Integer.toString(currentQuestionID));
        textView.setText(allQuestionsForQuiz.get(currentQuestionNo).getQuestion());
        Log.i("", "****************************UPDATE::allAnswersForQuestion.size(): " + allAnswersForQuestion.size());
        Log.i("", "****************************UPDATE::Question: " + allQuestionsForQuiz.get(currentQuestionNo).getQuestion());
        Log.i("", "****************************UPDATE::Current question id: " + currentQuestionID);
        if (allAnswersForQuestion.size()==0) {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                ((RadioButton) radioGroup.getChildAt(i)).setText(new String("No options"));
            }
        } else {
            for (int i = 0; i < radioGroup.getChildCount(); i++) {
                ((RadioButton) radioGroup.getChildAt(i)).setText(allAnswersForQuestion.get(i).getAnswer());
            }
        }
    }
}
