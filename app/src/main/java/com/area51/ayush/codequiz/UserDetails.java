package com.area51.ayush.codequiz;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Ayush on 19-10-2016.
 */

public class UserDetails {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private ArrayList<Integer> quizzesTaken;

    public UserDetails() {
    }

    public UserDetails(String uname, String fName, String lName, String pWord, ArrayList<Integer> qTaken) {
        username = uname;
        firstName = fName;
        lastName = lName;
        password = pWord;
        quizzesTaken = qTaken;
    }

    public UserDetails(String uname, String fName, String lName, ArrayList<Integer> qTaken) {
        username = uname;
        firstName = fName;
        lastName = lName;
        quizzesTaken = qTaken;
    }

    public void setFirstName(String fName) {
        firstName = fName;
    }

    public void setLastName(String lName){
        lastName = lName;
    }

    public void setPassword(String pWord) {
        password = pWord;
    }

    public void addQuizTaken(int quizId){
        quizzesTaken.add(quizId);
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Integer> getQuizzesTaken(){
        return quizzesTaken;
    }
}
