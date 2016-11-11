package com.area51.ayush.codequiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
    private static final String TABLE_QUIZZES_TAKEN = "QuizzesTaken";
    private static final String TABLE_QUESTION_ANSWERS = "QuestionAnswers";

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
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "(" + KEY_USERNAME + " TEXT PRIMARY KEY, " + KEY_FIRSTNAME + " TEXT, " + KEY_LASTNAME + " TEXT, " + KEY_PASSWORD + " TEXT)";
    private static final String CREATE_TABLE_QUIZZES = "CREATE TABLE " + TABLE_QUIZZES + "(" + KEY_QUIZ_ID + " INTEGER PRIMARY KEY, " + KEY_QUIZ_TITLE + " TEXT)";
    private static final String CREATE_TABLE_QUESTIONS = "CREATE TABLE " + TABLE_QUESTIONS + "(" + KEY_QUESTION_ID + " INTEGER PRIMARY KEY, " + KEY_QUESTION + " TEXT, " + KEY_QUIZ_ID + " INTEGER, FOREIGN KEY (" + KEY_QUIZ_ID + ") REFERENCES " + TABLE_QUIZZES + "(" + KEY_QUIZ_ID + "))";
    private static final String CREATE_TABLE_ANSWERS = "CREATE TABLE " + TABLE_ANSWERS + "(" + KEY_ANSWER_ID + " INTEGER PRIMARY KEY, " + KEY_ANSWER + " TEXT, " + KEY_QUESTION_ID + " INTEGER,  FOREIGN KEY (" + KEY_QUESTION_ID + ") REFERENCES " + TABLE_QUESTIONS + "(" + KEY_QUESTION_ID + "))";
    private static final String CREATE_TABLE_QUIZZES_TAKEN = "CREATE TABLE " + TABLE_QUIZZES_TAKEN + "(" + KEY_USERNAME + " TEXT, " + KEY_QUIZ_ID + " INTEGER, FOREIGN KEY (" + KEY_QUIZ_ID + ") REFERENCES " + TABLE_QUIZZES + "(" + KEY_QUIZ_ID + "), FOREIGN KEY (" + KEY_USERNAME + ") REFERENCES " + TABLE_USERS + "(" + KEY_USERNAME + "))";
    private static final String CREATE_TABLE_QUESTION_ANSWERS =  "CREATE TABLE " + TABLE_QUESTION_ANSWERS + "("+KEY_QUESTION_ID + " INTEGER, "+KEY_ANSWER_ID+" INTEGER, FOREIGN KEY (" +KEY_QUESTION_ID+") REFERENCES "+TABLE_QUESTIONS+"("+KEY_QUESTION_ID+"), FOREIGN KEY (" +KEY_ANSWER_ID+") REFERENCES "+TABLE_ANSWERS+"("+KEY_ANSWER_ID+"))";

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
        db.execSQL(CREATE_TABLE_QUESTION_ANSWERS);

        /*Creating five users*/
        UserDetails userDetails1 = new UserDetails("ayushsoni","Ayush","Soni","aysoni",null);
        UserDetails userDetails2 = new UserDetails("aishbhutra","Aishwarya","Bhutra","aibhutra",null);
        UserDetails userDetails3 = new UserDetails("akshatvora","Akshat","Vora","akvora",null);
        UserDetails userDetails4 = new UserDetails("sakigarg","Saksham","Garg","sagarg",null);
        UserDetails userDetails5 = new UserDetails("reubenjohn","Reuben","John","rejohn",null);

        /*Creating five quiz topics*/
        QuizDetails quizDetails1 = new QuizDetails(1000,"Back Tracking");
        QuizDetails quizDetails2 = new QuizDetails(1001,"Binary Search");
        QuizDetails quizDetails3 = new QuizDetails(1002,"Bubble Sort");
        QuizDetails quizDetails4 = new QuizDetails(1003,"Dynamic Programming");
        QuizDetails quizDetails5 = new QuizDetails(1004,"Greedy Algorithms");

        /*Creating Quiz Questions for the five quizzes, three in each*/
        QuizQuestion quizQuestionBacktracking1 = new QuizQuestion(1000,1,"Which of the following is not a backtracking algorithm? ");

        QuizQuestion quizQuestionBinarySearch1 = new QuizQuestion(1001,4,"What is the time complexity of binary search? ");

        QuizQuestion quizQuestionBubbleSort1 = new QuizQuestion(1002,7,"What is the best time complexity of bubble sort? ");
        QuizQuestion quizQuestionBubbleSort2 = new QuizQuestion(1002,8,"Assume that we use Bubble Sort to sort n distinct elements in ascending order. When does the best case of Bubble sort occur? ");
        QuizQuestion quizQuestionBubbleSort3 = new QuizQuestion(1002,9,"What is the worst case time complexity of Bubble Sort? ");

        QuizQuestion quizQuestionDynamicProgramming1 = new QuizQuestion(1003,10,"Which of the following standard algorithms is not Dynamic Programming based? ");
        QuizQuestion quizQuestionDynamicProgramming2 = new QuizQuestion(1003,11,"We use Dynamic Programming approach when? ");
        QuizQuestion quizQuestionDynamicProgramming3 = new QuizQuestion(1003,12,"Kadane's algorithm is used to find? ");

        QuizQuestion quizQuestionGreedyAlgorithms1 = new QuizQuestion(1004,13,"Which of the following standard algorithms is not a greedy algorithm? ");
        QuizQuestion quizQuestionGreedyAlgorithms2 = new QuizQuestion(1004,14,"What is the time complexity of Huffman Coding? ");
        QuizQuestion quizQuestionGreedyAlgorithms3 = new QuizQuestion(1004,15,"Which of the following is true about Kruskal and Prim MST algorithms? Assume that Prim is implemented for adjacency list representation using Binary Heap and Kruskal is implemented using union by rank. ");

        /*Creating answers for questions*/
        AnswerDetails answerDetailsBacktracking11 = new AnswerDetails(1,1,"Knight tour problem");
        AnswerDetails answerDetailsBacktracking12 = new AnswerDetails(2,1,"N queen's problem");
        AnswerDetails answerDetailsBacktracking13 = new AnswerDetails(3,1,"Tower of Hanoi");
        AnswerDetails answerDetailsBacktracking14 = new AnswerDetails(4,1,"M coloring problem");

        AnswerDetails answerDetailsBinarySearch11 = new AnswerDetails(5,4,"O(n)");
        AnswerDetails answerDetailsBinarySearch12 = new AnswerDetails(6,4,"O(Log(n))");
        AnswerDetails answerDetailsBinarySearch13 = new AnswerDetails(7,4,"O(n^2)");
        AnswerDetails answerDetailsBinarySearch14 = new AnswerDetails(8,4,"O(n(Log(n))^2");

        AnswerDetails answerDetailsBubbleSort11 = new AnswerDetails(9,7,"O(n(Log(n))");
        AnswerDetails answerDetailsBubbleSort12 = new AnswerDetails(10,7,"O(n^2)");
        AnswerDetails answerDetailsBubbleSort13 = new AnswerDetails(11,7,"O(n)");
        AnswerDetails answerDetailsBubbleSort14 = new AnswerDetails(12,7,"O(2n)");

        AnswerDetails answerDetailsBubbleSort21 = new AnswerDetails(13,8,"When the elements are sorted in ascending order");
        AnswerDetails answerDetailsBubbleSort22 = new AnswerDetails(14,8,"When the elements are sorted in descending order");
        AnswerDetails answerDetailsBubbleSort23 = new AnswerDetails(15,8,"When the elements are not sorted");
        AnswerDetails answerDetailsBubbleSort24 = new AnswerDetails(16,8,"There is no best case for bubble sort");

        AnswerDetails answerDetailsBubbleSort31 = new AnswerDetails(17,9,"O(n^3)");
        AnswerDetails answerDetailsBubbleSort32 = new AnswerDetails(18,9,"O(n^2)");
        AnswerDetails answerDetailsBubbleSort33 = new AnswerDetails(19,9,"O(n)");
        AnswerDetails answerDetailsBubbleSort34 = new AnswerDetails(20,9,"O(2n)");

        AnswerDetails answerDetailsDynamicProgramming11 = new AnswerDetails(21,10,"Bellman-Ford Algorithm for single source shortest path");
        AnswerDetails answerDetailsDynamicProgramming12 = new AnswerDetails(22,10,"Floyd-Warshall's Algorithm for all pairs shortest path");
        AnswerDetails answerDetailsDynamicProgramming13 = new AnswerDetails(23,10,"0-1 Knapsack problem");
        AnswerDetails answerDetailsDynamicProgramming14 = new AnswerDetails(24,10,"Prim's minimum spanning tree");

        AnswerDetails answerDetailsDynamicProgramming21 = new AnswerDetails(25,11,"It provides optimal solution");
        AnswerDetails answerDetailsDynamicProgramming22 = new AnswerDetails(26,11,"The solution has optimal substructure");
        AnswerDetails answerDetailsDynamicProgramming23 = new AnswerDetails(27,11,"The given problem can be reduced to 3-SAT problem");
        AnswerDetails answerDetailsDynamicProgramming24 = new AnswerDetails(28,11,"It's faster than greedy");

        AnswerDetails answerDetailsDynamicProgramming31 = new AnswerDetails(29,12,"Maximum sub-sequence in an array");
        AnswerDetails answerDetailsDynamicProgramming32 = new AnswerDetails(30,12,"Maximum sub-array in an array");
        AnswerDetails answerDetailsDynamicProgramming33 = new AnswerDetails(31,12,"Maximum product sub-sequence in an array");
        AnswerDetails answerDetailsDynamicProgramming34 = new AnswerDetails(32,12,"Maximum product sub-array in an array");

        AnswerDetails answerDetailsGreedyAlgorithms11 = new AnswerDetails(33,13,"Djikstra's Algorithm");
        AnswerDetails answerDetailsGreedyAlgorithms12 = new AnswerDetails(34,13,"Prim's Algorithm");
        AnswerDetails answerDetailsGreedyAlgorithms13 = new AnswerDetails(35,13,"Krushkal's Algorithm");
        AnswerDetails answerDetailsGreedyAlgorithms14 = new AnswerDetails(36,13,"Bellman-Ford shortest path algorithm");

        AnswerDetails answerDetailsGreedyAlgorithms21 = new AnswerDetails(37,14,"O(n)");
        AnswerDetails answerDetailsGreedyAlgorithms22 = new AnswerDetails(38,14,"O(n(Log(n)))");
        AnswerDetails answerDetailsGreedyAlgorithms23 = new AnswerDetails(39,14,"O(n^2)");
        AnswerDetails answerDetailsGreedyAlgorithms24 = new AnswerDetails(40,14,"O(n(Log(n))^2)");

        AnswerDetails answerDetailsGreedyAlgorithms31 = new AnswerDetails(41,15,"Worst case time complexity of both of these is same");
        AnswerDetails answerDetailsGreedyAlgorithms32 = new AnswerDetails(42,15,"Worst case time complexity of Kruskal's is better than Prim's");
        AnswerDetails answerDetailsGreedyAlgorithms33 = new AnswerDetails(43,15,"Worst case time complexity of Prim's is better than Kruskal's");
        AnswerDetails answerDetailsGreedyAlgorithms34 = new AnswerDetails(44,15,"Depends on the input");

        QuestionAnswer questionAnswer1 = new QuestionAnswer(1,3);
        QuestionAnswer questionAnswer2 = new QuestionAnswer(4,6);
        QuestionAnswer questionAnswer3 = new QuestionAnswer(7,11);
        QuestionAnswer questionAnswer4 = new QuestionAnswer(8,13);
        QuestionAnswer questionAnswer5 = new QuestionAnswer(9,18);
        QuestionAnswer questionAnswer6 = new QuestionAnswer(10,24);
        QuestionAnswer questionAnswer7 = new QuestionAnswer(11,26);
        QuestionAnswer questionAnswer8 = new QuestionAnswer(12,30);
        QuestionAnswer questionAnswer9 = new QuestionAnswer(13,36);
        QuestionAnswer questionAnswer10 = new QuestionAnswer(14,38);
        QuestionAnswer questionAnswer11 = new QuestionAnswer(15,41);

        /*Inserting now...*/
        createUser(userDetails1, db);
        createUser(userDetails2, db);
        createUser(userDetails3, db);
        createUser(userDetails4, db);
        createUser(userDetails5, db);

        createQuiz(quizDetails1, db);
        createQuiz(quizDetails2, db);
        createQuiz(quizDetails3, db);
        createQuiz(quizDetails4, db);
        createQuiz(quizDetails5, db);

        createQuestion(quizQuestionBacktracking1, db);
        createQuestion(quizQuestionBinarySearch1, db);
        createQuestion(quizQuestionBubbleSort1, db);
        createQuestion(quizQuestionBubbleSort2, db);
        createQuestion(quizQuestionBubbleSort3, db);
        createQuestion(quizQuestionDynamicProgramming1, db);
        createQuestion(quizQuestionDynamicProgramming2, db);
        createQuestion(quizQuestionDynamicProgramming3, db);
        createQuestion(quizQuestionGreedyAlgorithms1, db);
        createQuestion(quizQuestionGreedyAlgorithms2, db);
        createQuestion(quizQuestionGreedyAlgorithms3, db);

        createAnswer(answerDetailsBacktracking11, db);
        createAnswer(answerDetailsBacktracking12, db);
        createAnswer(answerDetailsBacktracking13, db);
        createAnswer(answerDetailsBacktracking14, db);

        createAnswer(answerDetailsBinarySearch11, db);
        createAnswer(answerDetailsBinarySearch12, db);
        createAnswer(answerDetailsBinarySearch13, db);
        createAnswer(answerDetailsBinarySearch14, db);

        createAnswer(answerDetailsBubbleSort11, db);
        createAnswer(answerDetailsBubbleSort12, db);
        createAnswer(answerDetailsBubbleSort13, db);
        createAnswer(answerDetailsBubbleSort14, db);

        createAnswer(answerDetailsBubbleSort21, db);
        createAnswer(answerDetailsBubbleSort22, db);
        createAnswer(answerDetailsBubbleSort23, db);
        createAnswer(answerDetailsBubbleSort24, db);

        createAnswer(answerDetailsBubbleSort31, db);
        createAnswer(answerDetailsBubbleSort32, db);
        createAnswer(answerDetailsBubbleSort33, db);
        createAnswer(answerDetailsBubbleSort34, db);

        createAnswer(answerDetailsDynamicProgramming11, db);
        createAnswer(answerDetailsDynamicProgramming12, db);
        createAnswer(answerDetailsDynamicProgramming13, db);
        createAnswer(answerDetailsDynamicProgramming14, db);

        createAnswer(answerDetailsDynamicProgramming21, db);
        createAnswer(answerDetailsDynamicProgramming22, db);
        createAnswer(answerDetailsDynamicProgramming23, db);
        createAnswer(answerDetailsDynamicProgramming24, db);

        createAnswer(answerDetailsDynamicProgramming31, db);
        createAnswer(answerDetailsDynamicProgramming32, db);
        createAnswer(answerDetailsDynamicProgramming33, db);
        createAnswer(answerDetailsDynamicProgramming34, db);

        createAnswer(answerDetailsGreedyAlgorithms11, db);
        createAnswer(answerDetailsGreedyAlgorithms12, db);
        createAnswer(answerDetailsGreedyAlgorithms13, db);
        createAnswer(answerDetailsGreedyAlgorithms14, db);

        createAnswer(answerDetailsGreedyAlgorithms21, db);
        createAnswer(answerDetailsGreedyAlgorithms22, db);
        createAnswer(answerDetailsGreedyAlgorithms23, db);
        createAnswer(answerDetailsGreedyAlgorithms24, db);

        createAnswer(answerDetailsGreedyAlgorithms31, db);
        createAnswer(answerDetailsGreedyAlgorithms32, db);
        createAnswer(answerDetailsGreedyAlgorithms33, db);
        createAnswer(answerDetailsGreedyAlgorithms34, db);

        createQuestionAnswer(questionAnswer1, db);
        createQuestionAnswer(questionAnswer2, db);
        createQuestionAnswer(questionAnswer3, db);
        createQuestionAnswer(questionAnswer4, db);
        createQuestionAnswer(questionAnswer5, db);
        createQuestionAnswer(questionAnswer6, db);
        createQuestionAnswer(questionAnswer7, db);
        createQuestionAnswer(questionAnswer8, db);
        createQuestionAnswer(questionAnswer9, db);
        createQuestionAnswer(questionAnswer10, db);
        createQuestionAnswer(questionAnswer11, db);
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

    //Insert methods
    public long createUser(UserDetails userDetails, SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, userDetails.getUsername());
        contentValues.put(KEY_FIRSTNAME, userDetails.getFirstName());
        contentValues.put(KEY_LASTNAME, userDetails.getLastName());
        contentValues.put(KEY_PASSWORD, userDetails.getPassword());

        return db.insert(TABLE_USERS, null, contentValues);
    }

    public long createQuiz(QuizDetails quizDetails, SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_QUIZ_ID, quizDetails.getQuizId());
        contentValues.put(KEY_QUIZ_TITLE, quizDetails.getQuizTitle());
        return db.insert(TABLE_QUIZZES, null, contentValues);
    }

    public long createQuestion(QuizQuestion quizQuestion, SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_QUESTION_ID, quizQuestion.getQuestionId());
        contentValues.put(KEY_QUESTION, quizQuestion.getQuestion());
        contentValues.put(KEY_QUIZ_ID, quizQuestion.getQuizId());

        return db.insert(TABLE_QUESTIONS, null, contentValues);
    }

    public long createAnswer(AnswerDetails answerDetails, SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ANSWER_ID, answerDetails.getAnswerId());
        contentValues.put(KEY_ANSWER, answerDetails.getAnswer());
        contentValues.put(KEY_QUESTION_ID, answerDetails.getQuestionId());

        return db.insert(TABLE_ANSWERS, null, contentValues);
    }

    public long createQuestionAnswer(QuestionAnswer questionAnswer, SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ANSWER_ID, questionAnswer.getAnswerId());
        contentValues.put(KEY_QUESTION_ID, questionAnswer.getQuestionId());

        return db.insert(TABLE_QUESTION_ANSWERS, null, contentValues);
    }

    public long createQuizTaken(QuizzesTaken quizzesTaken, SQLiteDatabase db) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, quizzesTaken.getUsername());
        contentValues.put(KEY_QUIZ_ID, quizzesTaken.getQuizId());

        return db.insert(TABLE_QUIZZES_TAKEN, null, contentValues);
    }

    //Getter methods
    public ArrayList<UserDetails> getAllUserDetails() {
        ArrayList<UserDetails> allUserDetails = new ArrayList<>();
        String selectAllFromUserDetails = "SELECT * FROM " + TABLE_USERS;
        Log.e(LOG, selectAllFromUserDetails);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectAllFromUserDetails, null);
        if (c.moveToFirst()) {
            do {
                UserDetails userDetails = new UserDetails();
                userDetails.setUsername(c.getString(c.getColumnIndex(KEY_USERNAME)));
                userDetails.setFirstName(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
                userDetails.setLastName(c.getString(c.getColumnIndex(KEY_LASTNAME)));
                userDetails.setPassword(c.getString(c.getColumnIndex(KEY_PASSWORD)));
                allUserDetails.add(userDetails);
            } while (c.moveToNext());
            c.close();
        }
        return allUserDetails;
    }

    public UserDetails getUserDetails(String username) {
        String selectSpecificUser = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_USERNAME + " = '" + username + "'";
        Log.e(LOG, selectSpecificUser);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectSpecificUser, null);
        if (c.moveToFirst()) {
            UserDetails userDetails = new UserDetails();
            userDetails.setUsername(c.getString(c.getColumnIndex(KEY_USERNAME)));
            userDetails.setFirstName(c.getString(c.getColumnIndex(KEY_FIRSTNAME)));
            userDetails.setLastName(c.getString(c.getColumnIndex(KEY_LASTNAME)));
            userDetails.setPassword(c.getString(c.getColumnIndex(KEY_PASSWORD)));
            c.close();
            return userDetails;
        }
        else return (new UserDetails());
    }

    public String getQuizName(int quizId) {
        String selectQuizNameFromQuizId = "SELECT * FROM "+TABLE_QUIZZES+" WHERE "+KEY_QUIZ_ID+" = "+ quizId;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(selectQuizNameFromQuizId, null);
        String quizName = "";
        if(c.moveToFirst()) {
            quizName = c.getString(c.getColumnIndex(KEY_QUIZ_TITLE));
        }
        c.close();
        return quizName;
    }

    public ArrayList<QuizQuestion> getAllQuestionsOfQuiz(int quiz_id) {
        String selectAllQuestionsOfQuiz = "SELECT * FROM " + TABLE_QUESTIONS + " WHERE " + KEY_QUIZ_ID + " = " + quiz_id;
        ArrayList<QuizQuestion> allQuestionsOfQuiz = new ArrayList<>();
        Log.e(LOG, selectAllQuestionsOfQuiz);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectAllQuestionsOfQuiz, null);
        if (c.moveToFirst()) {
            do {
                QuizQuestion quizQuestion = new QuizQuestion();
                quizQuestion.setQuestion(c.getString(c.getColumnIndex(KEY_QUESTION)));
                quizQuestion.setQuestionId(c.getInt(c.getColumnIndex(KEY_QUESTION_ID)));
                quizQuestion.setQuizId(c.getInt(c.getColumnIndex(KEY_QUIZ_ID)));
                allQuestionsOfQuiz.add(quizQuestion);
            } while (c.moveToNext());
            c.close();
        }
        return allQuestionsOfQuiz;
    }

    public String getAnswerForQuestion(int questionId) {
        String selectAnswerForQuestion = "SELECT "+KEY_ANSWER_ID+" FROM "+TABLE_QUESTION_ANSWERS+" WHERE "+KEY_QUESTION_ID+" = " +questionId;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(selectAnswerForQuestion, null);
        int ansId=0;
        if(c.moveToFirst()) {
            ansId = c.getInt(c.getColumnIndex(KEY_ANSWER_ID));
        }
        c.close();
        return getAnswerForAnswerId(ansId);
    }

    public String getAnswerForAnswerId(int answerId) {
        String selectAnswerForAnswerId = "SELECT * FROM "+TABLE_ANSWERS+" WHERE "+KEY_ANSWER_ID+" = "+answerId;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(selectAnswerForAnswerId, null);
        if(c.moveToFirst()) {
            AnswerDetails answerDetails = new AnswerDetails();
            answerDetails.setAnswerId(answerId);
            answerDetails.setAnswer(c.getString(c.getColumnIndex(KEY_ANSWER)));
            c.close();
            return answerDetails.getAnswer();
        }
        return null;
    }

    public ArrayList<QuizDetails> getAllQuizzes() {
        String selectAllQuizzes = "SELECT * FROM " + TABLE_QUIZZES;
        ArrayList<QuizDetails> allQuizDetails = new ArrayList<>();
        Log.e(LOG, selectAllQuizzes);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectAllQuizzes, null);
        if (c.moveToFirst()) {
            do {
                QuizDetails quizDetails = new QuizDetails();
                quizDetails.setQuizTitle(c.getString(c.getColumnIndex(KEY_QUIZ_TITLE)));
                quizDetails.setQuizId(c.getInt(c.getColumnIndex(KEY_QUIZ_ID)));
                allQuizDetails.add(quizDetails);
            } while (c.moveToNext());
            c.close();
        }
        return allQuizDetails;
    }

    public ArrayList<AnswerDetails> getAnswersForQuestion(int questionId) {
        String selectAnswersForQuestion = "SELECT * FROM "+TABLE_ANSWERS+" WHERE "+ KEY_QUESTION_ID + " = "+questionId;
        ArrayList<AnswerDetails> allAnswersForQuestion = new ArrayList<>();
        Log.e(LOG, selectAnswersForQuestion);
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(selectAnswersForQuestion, null);
        if(c.moveToFirst()) {
            do {
                AnswerDetails answerDetails = new AnswerDetails();
                answerDetails.setQuestionId(questionId);
                answerDetails.setAnswer(c.getString(c.getColumnIndex(KEY_ANSWER)));
                answerDetails.setAnswerId(c.getInt(c.getColumnIndex(KEY_ANSWER_ID)));
                allAnswersForQuestion.add(answerDetails);
            }while(c.moveToNext());
            c.close();
        }
        return allAnswersForQuestion;
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
