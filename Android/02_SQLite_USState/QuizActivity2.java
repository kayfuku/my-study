//**************************************************************
//  Author    : Kei Fukutani
//  File Name : QuizActivity2.java
//  Date      : 4/22/14
//  Objective : This program provides a U.S. states game in 
//              which user answers capital for 5 each states 
//              selected randomly and answers state for 5 each 
//              capital selected randomly and this program grade
//              the answers and display the player's name and 
//              the score in the descending order of the score.  
//**************************************************************
package com.example.usstatesgame2;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class QuizActivity2 extends Activity
{
    private SQLiteDatabase db;
    private TextView user_name, text_points;
    private TextView[] tvs = new TextView[10];
    private TextView[] ans = new TextView[10];
    private EditText[] ets = new EditText[10];
    private String name;
    private int points = 0;
    private String[] states;
    private String[] capitals;
    private int[] indexStatesSelected = new int[5];
    private int[] indexCapitalsSelected = new int[5];

    // -------------onCreate()-----------------------------------
    // When this Activity invoked, this method will be executed.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_activity2);
        Log.i("USGame", "QuizActivity2 onCreate() called.");

        user_name = (TextView) findViewById(R.id.user_name);
        tvs[0] = (TextView) findViewById(R.id.text_q1);
        tvs[1] = (TextView) findViewById(R.id.text_q2);
        tvs[2] = (TextView) findViewById(R.id.text_q3);
        tvs[3] = (TextView) findViewById(R.id.text_q4);
        tvs[4] = (TextView) findViewById(R.id.text_q5);
        tvs[5] = (TextView) findViewById(R.id.text_q6);
        tvs[6] = (TextView) findViewById(R.id.text_q7);
        tvs[7] = (TextView) findViewById(R.id.text_q8);
        tvs[8] = (TextView) findViewById(R.id.text_q9);
        tvs[9] = (TextView) findViewById(R.id.text_q10);
        text_points = (TextView) findViewById(R.id.text_points);
        ets[0] = (EditText) findViewById(R.id.et_q1);
        ets[1] = (EditText) findViewById(R.id.et_q2);
        ets[2] = (EditText) findViewById(R.id.et_q3);
        ets[3] = (EditText) findViewById(R.id.et_q4);
        ets[4] = (EditText) findViewById(R.id.et_q5);
        ets[5] = (EditText) findViewById(R.id.et_q6);
        ets[6] = (EditText) findViewById(R.id.et_q7);
        ets[7] = (EditText) findViewById(R.id.et_q8);
        ets[8] = (EditText) findViewById(R.id.et_q9);
        ets[9] = (EditText) findViewById(R.id.et_q10);
        ans[0] = (TextView) findViewById(R.id.text_result1);
        ans[1] = (TextView) findViewById(R.id.text_result2);
        ans[2] = (TextView) findViewById(R.id.text_result3);
        ans[3] = (TextView) findViewById(R.id.text_result4);
        ans[4] = (TextView) findViewById(R.id.text_result5);
        ans[5] = (TextView) findViewById(R.id.text_result6);
        ans[6] = (TextView) findViewById(R.id.text_result7);
        ans[7] = (TextView) findViewById(R.id.text_result8);
        ans[8] = (TextView) findViewById(R.id.text_result9);
        ans[9] = (TextView) findViewById(R.id.text_result10);

        // Get data from MainActivity.
        Intent i = getIntent();
        name = (String) i.getStringExtra("PLAYERS_NAME_KEY");
        states = (String[]) i.getStringArrayExtra("STATES_KEY");
        capitals = (String[]) i.getStringArrayExtra("CAPITALS_KEY");
        Log.i("USGame", "Got intent.");

        // Display texts.
        user_name.setText(name);

        // Make a Quiz.
        makeQuiz();
    }

    // -------------doit()---------------------------------------
    // When Button is hit, this method will be executed.
    public void doit(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_done:
                getResult();
                break;
            case R.id.btn_score:
                toScore();
                break;
            case R.id.btn_replay:
                makeQuiz();
                break;
        }
    }

    // -------------getResult()----------------------------------
    // Score the quiz.
    private void getResult()
    {
        String[] answers = new String[10];
        String capitalCorrect = "c";
        String stateCorrect = "s";
        Cursor c;
        int ci = 0;
        Log.i("USGame", "DB opened.");

        // Open the database.
        db = openOrCreateDatabase(MainActivity2.DB_FILE_NAME,
                SQLiteDatabase.CREATE_IF_NECESSARY, null);

        // Get answers user input.
        for (int i = 0; i < 10; i++)
        {
            answers[i] = ets[i].getText().toString();
        }
        Log.i("USGame", "Got answers.");

        // Grade the answers.
        for (int i = 0; i < 5; i++)
        {
            c = db.query(MainActivity2.DB_TABLE,
                    new String[] {MainActivity2.DB_COLUMN_CAPITAL}, "state = ?",
                    new String[] {states[indexStatesSelected[i]]}, null, null, null,
                    null);
            Log.i("USGame", "query.");
            c.moveToFirst();
            Log.i("USGame", "c.moveToFirst().");
            ci = c.getColumnIndex(MainActivity2.DB_COLUMN_CAPITAL);
            Log.i("USGame", "ci.");
            capitalCorrect = c.getString(ci);
            Log.i("USGame", "capitalCorrect." + capitalCorrect);
            c.close();
            Log.i("USGame", "c.close()." + i);

            if (answers[i].equals(capitalCorrect))
            {
                points += 10;
                ans[i].setText("Correct!!");
            }
            else
            {
                ans[i].setText(capitalCorrect);
                ans[i].setTextColor(Color.RED);
            }

        }
        for (int i = 5; i < 10; i++)
        {
            c = db.query(MainActivity2.DB_TABLE,
                    new String[] {MainActivity2.DB_COLUMN_STATE}, "capital = ?",
                    new String[] {capitals[indexCapitalsSelected[i - 5]]}, null,
                    null, null, null);
            c.moveToFirst();
            ci = c.getColumnIndex(MainActivity2.DB_COLUMN_STATE);
            stateCorrect = c.getString(ci);
            c.close();

            if (answers[i].equals(stateCorrect))
            {
                points += 10;
                ans[i].setText("Correct!!");
            }
            else
            {
                ans[i].setText(stateCorrect);
                ans[i].setTextColor(Color.RED);
            }
        }
        db.close();
        // Log.i("USGame", "DB closed.");

        text_points.setText(Integer.toString(points));
    } // end of getResult().

    // -------------toScore()------------------------------------
    // Correct the answers.
    private void toScore()
    {
        // Open the database.
        db = openOrCreateDatabase(MainActivity2.DB_FILE_NAME,
                SQLiteDatabase.CREATE_IF_NECESSARY, null);

        // Insert data into the score table.
        ContentValues cv = new ContentValues();
        // cv.put(MainActivity2.DB_COLUMN_RANK, rank);
        cv.put(MainActivity2.DB_COLUMN_NAME, name);
        cv.put(MainActivity2.DB_COLUMN_SCORE, points);
        db.insert(MainActivity2.DB_TABLE2, null, cv);

        db.close();

        // Invoke ScoreActivity.
        Intent in_score = new Intent(getApplicationContext(), ScoreActivity.class);
        startActivity(in_score);
    }

    // -------------makeQuiz()---------------------------------------
    // Make the quiz.
    private void makeQuiz()
    {
        points = 0;
        text_points.setText("");

        Log.i("USGame", "makeQuiz() called.");

        // Clear input fields and correct answers.
        for (int i = 0; i < 10; i++)
        {
            ets[i].setText("");
            ans[i].setText("");
        }

        // Pick up states and capitals.
        pickUp(states, true);
        pickUp(capitals, false);
        Log.i("USGame", "pickUp() finished.");
    } // end of makeQuiz().

    // -------------pickUp()---------------------------------------
    // Pick up 5 without duplication in the array.
    private void pickUp(String[] strs, boolean statesFlag)
    {
        Random rnd = new Random();
        int i = 0;
        int indexRan;
        boolean isInArray;
        int[] indexSelected = new int[5];

        while (i < 5)
        {
            isInArray = true;
            indexRan = rnd.nextInt(50);
            Log.i("USGame", "indexRan" + i + ": " + indexRan);

            // Check if it's already selected.
            for (int n = 0; n < indexSelected.length; n++)
            {
                if (indexRan == indexSelected[n])
                {
                    isInArray = false;
                    break;
                }
            }

            if (isInArray)
            {
                indexSelected[i] = indexRan;
                if (statesFlag)
                {
                    tvs[i].setText((i + 1) + ": " + strs[indexSelected[i]]);
                    indexStatesSelected[i] = indexSelected[i];
                }
                else
                {
                    tvs[i + 5].setText((i + 1 + 5) + ": " + strs[indexSelected[i]]);
                    indexCapitalsSelected[i] = indexSelected[i];
                }

                i++;
            }
        }
    }
}
