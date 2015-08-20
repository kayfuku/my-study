//**************************************************************
//  Author    : Kei Fukutani
//  File Name : ScoreActivity.java
//  Date      : 4/22/14
//  Objective : This program provides a U.S. states game in 
//              which user answers capital for 5 each states 
//              selected randomly and answers state for 5 each 
//              capital selected randomly and this program grade
//              the answers and display the player's name and 
//              the score in the descending order of the score.  
//**************************************************************
package com.example.usstatesgame2;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ScoreActivity extends Activity
{
    private TextView text_title;
    private SQLiteDatabase db;
    private TextView[] scores = new TextView[11];
    private TextView[] names = new TextView[11];

    // -------------onCreate()-----------------------------------
    // When this Activity invoked, this method will be executed.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        text_title = (TextView) findViewById(R.id.text_title);
        names[0] = (TextView) findViewById(R.id.text_n1);
        names[1] = (TextView) findViewById(R.id.text_n2);
        names[2] = (TextView) findViewById(R.id.text_n3);
        names[3] = (TextView) findViewById(R.id.text_n4);
        names[4] = (TextView) findViewById(R.id.text_n5);
        names[5] = (TextView) findViewById(R.id.text_n6);
        names[6] = (TextView) findViewById(R.id.text_n7);
        names[7] = (TextView) findViewById(R.id.text_n8);
        names[8] = (TextView) findViewById(R.id.text_n9);
        names[9] = (TextView) findViewById(R.id.text_n10);
        names[10] = (TextView) findViewById(R.id.text_n11);
        scores[0] = (TextView) findViewById(R.id.text_s1);
        scores[1] = (TextView) findViewById(R.id.text_s2);
        scores[2] = (TextView) findViewById(R.id.text_s3);
        scores[3] = (TextView) findViewById(R.id.text_s4);
        scores[4] = (TextView) findViewById(R.id.text_s5);
        scores[5] = (TextView) findViewById(R.id.text_s6);
        scores[6] = (TextView) findViewById(R.id.text_s7);
        scores[7] = (TextView) findViewById(R.id.text_s8);
        scores[8] = (TextView) findViewById(R.id.text_s9);
        scores[9] = (TextView) findViewById(R.id.text_s10);
        scores[10] = (TextView) findViewById(R.id.text_s11);

        // Display the score table.
        displayScore();
    }

    // -------------doit()---------------------------------------
    // When Button is hit, this method will be executed.
    public void doit(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_home:
                toMain();
                break;
        }
    }

    // -------------toMain()-------------------------------------
    // Go back to MainActivity.
    private void toMain()
    {
        // Invoke MainActivity.
        Intent in_main = new Intent(getApplicationContext(), MainActivity2.class);
        startActivity(in_main);
    }

    // -------------displayScore()-------------------------------
    // Display the score table.
    private void displayScore()
    {
        String name;
        String score;

        // Open the database.
        db = openOrCreateDatabase(MainActivity2.DB_FILE_NAME,
                SQLiteDatabase.CREATE_IF_NECESSARY, null);

        // Sort the data on the table in descending order of score.
        Cursor c = db.query(MainActivity2.DB_TABLE2, null, null, null, null, null,
                "score DESC", // order by
                Integer.toString(10) // limit
                );

        // Display the table.
        c.moveToFirst();
        int length = c.getCount();
        int index = 0;
        names[0].setText(MainActivity2.DB_COLUMN_NAME);
        scores[0].setText(MainActivity2.DB_COLUMN_SCORE);

        for (int i = 1; i <= length; i++)
        {
            index = c.getColumnIndex(MainActivity2.DB_COLUMN_NAME);
            if (index >= 0)
            {
                name = c.getString(index);
                names[i].setText(name);
            }

            index = c.getColumnIndex(MainActivity2.DB_COLUMN_SCORE);
            if (index >= 0)
            {
                score = c.getString(index);
                scores[i].setText(score);
            }

            c.moveToNext();
        }

        c.close();
        db.close();
    }
}
