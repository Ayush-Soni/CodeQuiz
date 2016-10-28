package com.area51.ayush.codequiz;

import android.content.Intent;
import android.database.sqlite.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextUsername = (EditText) findViewById(R.id.editTextUsername);
                EditText editTextPassword = (EditText) findViewById(R.id.editTextPassword);
                String specifiedUsername = editTextUsername.getText().toString();
                if((editTextPassword.getText().toString()).equals((new DatabaseHelper(getApplicationContext())).getUserDetails(specifiedUsername).getPassword())) {
                    Intent i = new Intent(LoginActivity.this, QuizListActivity.class);
                    startActivity(i);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid credentials. Try Again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
