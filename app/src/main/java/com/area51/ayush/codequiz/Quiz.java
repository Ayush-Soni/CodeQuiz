package com.area51.ayush.codequiz;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
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
    String quizName;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    ArrayList<QuizQuestion> allQuestionsForQuiz;
    FloatingActionButton fab;
    FloatingActionButton fab1;
    int[] answerSelected;
    boolean[] score;

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
        quizName = db.getQuizName(quizId);
        allQuestionsForQuiz = db.getAllQuestionsOfQuiz(quizId);
        answerSelected = new int[allQuestionsForQuiz.size()];
        score = new boolean[allQuestionsForQuiz.size()];
        for(int i=0; i<allQuestionsForQuiz.size(); i++) {
            answerSelected[i] = -1;
            score[i] = false;
        }

        Log.i("","****************************Question: "+allQuestionsForQuiz.get(currentQuestionNo).getQuestion());
        Log.i("","****************************Current question id: "+ currentQuestionID);

        fab = (FloatingActionButton) findViewById(R.id.fabEnd);
        fab1 = (FloatingActionButton) findViewById(R.id.fabStart);
        if(allQuestionsForQuiz.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No questions for quizId: "+ quizId, Toast.LENGTH_SHORT).show();
        }
        else {
            if (currentQuestionNo < allQuestionsForQuiz.size()) {
                updateQuestion(currentQuestionNo);
            }
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId == -1) {
                    //Do nothing
                }
                else{
                    answerSelected[currentQuestionNo]=checkedRadioButtonId;
                    RadioButton radioButton = (RadioButton) findViewById(checkedRadioButtonId);
                    String answerString = (String)radioButton.getText();
                    if(answerString.equals(db.getAnswerForQuestion(currentQuestionID))){
                        score[currentQuestionNo] = true;
                    }
                    else {
                        score[currentQuestionNo] = false;
                    }
                }
                currentQuestionNo++;
                if(currentQuestionNo<allQuestionsForQuiz.size()) {
                    Snackbar.make(view, "Next question.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    updateQuestion(currentQuestionNo);
                }
                else {
                    int totalScore = 0;
                    for(int i=0; i<score.length;i++) {
                        if(score[i])
                            totalScore++;
                    }
                    //Snackbar.make(view, "Total: "+totalScore+"/"+allQuestionsForQuiz.size(), Snackbar.LENGTH_LONG)
                    //        .setAction("Action", null).show();
                    issueNotification(totalScore, allQuestionsForQuiz.size());
                    currentQuestionNo--;
                }
            }
        });

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

        if(currentQuestionNo==allQuestionsForQuiz.size()-1) {
        fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#8BC34A")));
        }
        if(currentQuestionNo<allQuestionsForQuiz.size()-1) {
            fab.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
        }
        if(answerSelected[currentQuestionNo]!=-1) {
            RadioButton rbutton = (RadioButton) findViewById(answerSelected[currentQuestionNo]);
            rbutton.setChecked(true);
        }
    }

    public void issueNotification(int totalScore, int totalQuestions) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.success)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.logomkra))
                .setContentTitle("Quiz complete")
                .setContentText("Congratulations on completing the quiz on " + quizName +".")
                .setSubText("Tap to view result");

        Intent intent = new Intent(getApplicationContext(), DisplayResultActivity.class);
        intent.putExtra("quizname", quizName);
        intent.putExtra("totalmarks", totalScore);
        intent.putExtra("totalquestions", totalQuestions);
        intent.putExtra("notificationid", 1);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        mBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, mBuilder.build());
    }
}
