package com.area51.ayush.codequiz;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

    public class DisplayResultActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_result);

            Intent intent = getIntent();
            String quizName = intent.getStringExtra("quizname");
            int totalMarks = intent.getIntExtra("totalmarks", 0);
            int totalQuestions = intent.getIntExtra("totalquestions", 0);
            int notificationId = intent.getIntExtra("notificationid", 0);
            TextView textView = (TextView) findViewById(R.id.result_quiz_textview);
            TextView textView1 = (TextView) findViewById(R.id.result_display_textview);
            textView.setText(quizName);
            String marks = totalMarks + "/" +  totalQuestions;
            textView1.setText(marks);
            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            notificationManager.cancel(notificationId);
        }

        @Override
        protected void onResume() {
            super.onResume();
        }
    }
