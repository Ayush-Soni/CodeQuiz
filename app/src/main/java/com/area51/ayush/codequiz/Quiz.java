package com.area51.ayush.codequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Quiz extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabEnd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Next question.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fabStart);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Previous question.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Intent intent = getIntent();
        int quizId = intent.getIntExtra("quizID",0);
        ArrayList<QuizQuestion> allQuestionsOfQuiz = (new DatabaseHelper(getApplicationContext())).getAllQuestionsOfQuiz(quizId);
        if(allQuestionsOfQuiz.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No questions for quizId: "+ quizId, Toast.LENGTH_SHORT).show();
        }
        else {

        }
    }
}
