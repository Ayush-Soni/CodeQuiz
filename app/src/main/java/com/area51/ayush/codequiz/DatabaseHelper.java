package com.area51.ayush.codequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ayush on 19-10-2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "codeQuizManager";
    private static final String LOG = "DatabaseHelper";

    //Table Names
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_QUIZZES = "Quizzes";
    private static final String TABLE_ANSWERS = "Answers";
    private static final String TABLE_QUESTIONS = "Questions";
    private static final String TABLE_QUIZZES_TAKEN = "QuizzesTaken"

    //Common //QUIZZES TAKEN Table columns
    private static final String KEY_QUIZ_ID = "quiz_ID";
    private static final String KEY_USERNAME = "username";

    //USERS table columns
    private static final String KEY_FIRSTNAME = "first_name";
    private static final String KEY_LASTNAME = "last_name";
    private static final String KEY_PASSWORD = "password";

    //QUIZ QUESTION table columns
    private static final String KEY_QUESTION_ID = "question_ID";
    private static final String KEY_QUESTION = "question";

    //QUIZ DETAILS table columns
    private static final String KEY_QUIZ_TITLE = "quiz_title";

    //ANSWER table columns
    private static final String KEY_ANSWER_ID = "answer_ID";
    private static final String KEY_ANSWER = "answer";

    //Table create statements
    private static final String CREATE_TABLE_USERS = "CREATE TABLE "+TABLE_USERS+"("+KEY_USERNAME+" TEXT PRIMARY KEY, "+KEY_FIRSTNAME+" TEXT, "+KEY_LASTNAME+" TEXT, "+KEY_PASSWORD+" TEXT)";
    private static final String CREATE_TABLE_QUIZZES = "CREATE TABLE "+TABLE_QUIZZES+"("+KEY_QUIZ_ID+" INTEGER PRIMARY KEY, "+KEY_QUIZ_TITLE+" TEXT)";
    private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE "+TABLE_QUESTIONS+"("+KEY_QUESTION_ID+" INTEGER PRIMARY KEY, "+KEY_QUESTION+" TEXT, "+KEY_QUIZ_ID+" INTEGER FOREIGN KEY REFERENCES "+ TABLE_QUIZZES+"("+KEY_QUIZ_ID+"))";
    private static final String CREATE_TABLE_ANSWERS = "CREATE TABLE "+TABLE_ANSWERS+"("+KEY_ANSWER_ID+" INTEGER PRIMARY KEY, "+KEY_ANSWER+" TEXT, "+KEY_QUESTION_ID+" INTEGER REFERENCES "+TABLE_QUESTIONS+"("+KEY_QUESTION_ID+")";
    private static final String CREATE_TABLE_QUIZZES_TAKEN = "CREATE TABLE "+TABLE_QUIZZES_TAKEN+"("+KEY_USERNAME+" TEXT FOREIGN KEY REFERENCES "+TABLE_USERS+"("+KEY_USERNAME+"), "+KEY_QUIZ_ID+" INTEGER FOREIGN KEY REFERENCES "+ TABLE_QUIZZES+"("+KEY_QUIZ_ID+"))";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_QUIZZES);
        db.execSQL(CREATE_TABLE_QUESTIONS);
        db.execSQL(CREATE_TABLE_ANSWERS);
        db.execSQL(CREATE_TABLE_QUIZZES_TAKEN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZZES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZZES_TAKEN);

        onCreate(db);
    }

    public void createUser(UserDetails userDetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, userDetails.getUsername());
        contentValues.put(KEY_FIRSTNAME, userDetails.getFirstName());
        contentValues.put(KEY_LASTNAME, userDetails.getLastName());
        contentValues.put(KEY_PASSWORD, userDetails.getPassword());

        long user_id = db.insert(TABLE_USERS, null, contentValues);
    }

    public void createQuiz(QuizDetails quizDetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_QUIZ_ID, quizDetails.getQuizId());
        contentValues.put(KEY_QUIZ_TITLE, quizDetails.getQuizTitle());

        long quiz_id = db.insert(TABLE_QUIZZES, null, contentValues);
    }

    public void createQuestion(QuizQuestion quizQuestion) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_QUESTION_ID, quizQuestion.getQuestionId());
        contentValues.put(KEY_QUESTION, quizQuestion.getQuestion());
        contentValues.put(KEY_QUIZ_ID, quizQuestion.getQuizId());

        long question_id = db.insert(TABLE_QUESTIONS, null, contentValues);
    }

    public void createAnswer(AnswerDetails answerDetails) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ANSWER_ID, answerDetails.getAnswerId());
        contentValues.put(KEY_ANSWER, answerDetails.getAnswer());
        contentValues.put(KEY_QUESTION_ID, answerDetails.getQuestionId());

        long question_id = db.insert(TABLE_ANSWERS, null, contentValues);
    }

    public void createQuizTaken(QuizzesTaken quizzesTaken) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, quizzesTaken.getUsername());
        contentValues.put(KEY_QUIZ_ID, quizzesTaken.getQuizId());

        long question_id = db.insert(TABLE_QUIZZES_TAKEN, null, contentValues);
    }


}
