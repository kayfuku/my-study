//**************************************************************
//  Author    : Kei Fukutani
//  File Name : MainActivity2.java
//  Date      : 4/22/14
//  Objective : This program provides a U.S. states game in 
//              which user answers capital for 5 each states 
//              selected randomly and answers state for 5 each 
//              capital selected randomly and this program grade
//              the answers and display the player's name and 
//              the score in the descending order of the score.  
//**************************************************************
package com.example.usstatesgame2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends Activity 
       implements View.OnKeyListener
{
	private TextView text_bg;
	private EditText etInput;
	private String name;
	
	// This file is put in the assets directory. 
	private static final String FILE_PATH = "US_states";
	private String[] states = new String[50];
	private String[] capitals = new String[50];
	private String[] temp;
	
	// Database.
	private SQLiteDatabase db;
	static final String DB_FILE_NAME = "us_states_db";
	static final int DB_VERSION = 1;
	static final String DB_TABLE = "us_states_tbl";
	static final String DB_TABLE2 = "score_tbl";
	static final String DB_COLUMN_ID = "_id";
	static final String DB_COLUMN_STATE = "state";
	static final String DB_COLUMN_CAPITAL = "capital";
	static final String DB_COLUMN_RANK = "rank";
	static final String DB_COLUMN_NAME = "name";
	static final String DB_COLUMN_SCORE = "score";

    //-------------onCreate()-----------------------------------
	// When this Activity invoked, this method will be executed.
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//text_bg = (TextView)findViewById(R.id.text_bg);
		
		// Create a database and a table
		db = openOrCreateDatabase(DB_FILE_NAME, 
				           SQLiteDatabase.CREATE_IF_NECESSARY, 
				           null);
		db.setVersion(DB_VERSION);
		
		// and put data into the table in background.
		new DataLoadAsyncTask().execute(1);
		
		// Identify EditText and Set the event listener on it. 
		etInput = (EditText)findViewById(R.id.et);
		etInput.setOnKeyListener(this);
	}

	
	//-------------onKey()--------------------------------------
	// When Enter key is hit, this method will be executed. 
	@Override
	public boolean onKey(View v, int keyCode, KeyEvent ke) 
	{
	    if ((ke.getAction() == KeyEvent.ACTION_UP) && 
			(keyCode == KeyEvent.KEYCODE_ENTER))
		{
	    	name = etInput.getText().toString();
	    	// Not go to Quiz page until user input something.
	    	if (name.equals(new String(""))) 
			{
				Toast t = Toast.makeText(getBaseContext(), 
						 "Enter your name.", 
		                 Toast.LENGTH_LONG);
				t.show();
			}
			else
			{
				toQuiz();
			}
		    
		    return true;
		}
		return false;
	} 
	
	//-------------doit()---------------------------------------
	// When Button is hit, this method will be executed. 
	public void doit(View v)
	{
		switch (v.getId())
	   	{
	   	    case R.id.btn_play:
	  	    	toQuiz();
	   	    	break;
	   	    case R.id.btn_score:
	   	    	toScore();
	   	    	break;
	   	}
	}
	
	//-------------toQuiz()-------------------------------------
	// Move to QuizActivity2.
	private void toQuiz()
	{
		// Get the Player's Name that user input.
		name = etInput.getText().toString();
		
		if (name.equals(new String(""))) 
		{
			Toast t = Toast.makeText(getBaseContext(), 
					 "Enter your name.", 
	                 Toast.LENGTH_LONG);
			t.show();
		}
		else
		{
			Intent i = new Intent(getApplicationContext(),
		              QuizActivity2.class);
	        i.putExtra("PLAYERS_NAME_KEY", name);
	        i.putExtra("STATES_KEY", states);
	        i.putExtra("CAPITALS_KEY", capitals);	        
	        startActivity(i);
		}	
	}
	
	//-------------toScore()------------------------------------
	// Go to ScoreActivity. 
	private void toScore()
	{
		Intent in_score = new Intent(getApplicationContext(),
	              ScoreActivity.class);        
        startActivity(in_score);
	}
	
	//***********DataLoadAsyncTask inner class******************
	// inner class: For background tasks.
	public class DataLoadAsyncTask 
		          extends AsyncTask<Integer, Integer, Void>
	{
	    //-----onPreExecute()-----------------------------------
		// This will be invoked before doInBackground() called.
		@Override
		protected void onPreExecute()
		{
			//text_bg.setText("Sequence numbers bigins.");
		}
		
		//-----doInBackground()---------------------------------
		// Get data from a file and put the data into the table.
		@Override
		protected Void doInBackground(Integer ... args)
		{
			// Get data.
			insert();		
			
			for (int i = args[0]; i <= 3; i++)
			{
				publishProgress(i);
				SystemClock.sleep(1000);
			}
			return null;
		}
		
		//-----onProgressUpdate()-------------------------------
		//
		@Override
		protected void onProgressUpdate(Integer ... args)
		{
			//text_bg.setText(args[0].toString());
		}
		
		//-----onProgressUpdate()-------------------------------
		// This will be invoked after doInBackground() done.
		@Override
		protected void onPostExecute(Void result)
		{
			//text_bg.setText("Sequence numbers over.");
		}			
	} // end of DataLoadAsyncTask inner class.

	//-------------insert()-------------------------------------
	// Insert data.
	private void insert() 
	{
	    // Get data from a file.
		parseUSStates(states, capitals);
	    
	    // Create a DB_TABLE table. The first three lines 
		// were worth working for hours!
		String sqlcmd = ""; 
		sqlcmd += "drop table if exists " + DB_TABLE + ";";
		db.execSQL(sqlcmd);
		sqlcmd = ""; 
		sqlcmd += "create table " + DB_TABLE + " (";
		sqlcmd += DB_COLUMN_ID + " integer primary key " +  
		          "autoincrement, ";
		sqlcmd += DB_COLUMN_STATE + " text not null, ";
		sqlcmd += DB_COLUMN_CAPITAL + " text not null";
		sqlcmd += ")";
        db.execSQL(sqlcmd);	 
        
        /* 
	    // Create a DB_TABLE2 table. The first three lines 
		// were worth working for hours!
		sqlcmd = ""; 
		sqlcmd += "drop table if exists " + DB_TABLE2 + ";";
		db.execSQL(sqlcmd);
		sqlcmd = ""; 
		sqlcmd += "create table " + DB_TABLE2 + " (";
		sqlcmd += DB_COLUMN_RANK + " integer, ";
		sqlcmd += DB_COLUMN_NAME + " text not null, ";
		sqlcmd += DB_COLUMN_SCORE + " integer";
		sqlcmd += ")";
        db.execSQL(sqlcmd);	     */    
	    
	    // Insert US_states data into the table. 
        ContentValues cv = new ContentValues();
        for (int i = 0; i < 50; i++){
        	cv.put(DB_COLUMN_STATE,
                   states[i]);
        	cv.put(DB_COLUMN_CAPITAL,
                   capitals[i]); 
        	db.insert(DB_TABLE, null, cv);
        } 

        db.close(); 
    } // end of insert().
	
	//-------------parseUSStates()------------------------------
	// Get data from a file in the assets dir and parse the data. 
	private void parseUSStates(String[] states, String[] capitals)
    {
    	try 
    	{
			AssetManager assets = getResources().getAssets();
			InputStream inputStream = assets.open(FILE_PATH);
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(inputStream));
			String line;
			int ig = 0;
			int i = 0;
			while ((line = reader.readLine()) != null)
			{
				// Ignore the first two lines. 
				if (ig > 1)
				{
					temp = line.split("\\s\\s+");
		            if(temp.length >= 2)
		            {
		               if(temp.length == 2) 
		               {
		                  states[i] = temp[0];
		                  capitals[i] = temp[1];
		               }
		               else
		               {
		                  states[i] = temp[0] + " " + temp[1];
		                  capitals[i] = temp[2];
		               }
		               i++;
		            }
				}
				ig++;
			}
			reader.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}  	
    } // end of parseUSState().
}
