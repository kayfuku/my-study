//**************************************************************
//  Author    : Kei Fukutani
//  File Name : SendBirthdayWishActivity.java
//  Date      : 5/17/14
//  Objective : This program checks if today is somebody's birth
//              date and send message to the person by either
//              SMS or email.  
//**************************************************************

package com.example.sendbirthdaywish;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class SendBirthdayWishActivity extends Activity implements
        OnCheckedChangeListener
{
    private TextView tvName, tvNum, tvMsg;
    private RadioGroup rg;
    private RadioButton rbSMS, rbEmail;

    // This file is put in the assets directory.
    private static final String FILE_PATH = "contact.txt";
    private static final String FILE_PATH2 = "birthday_wish.txt";

    private List<String> names = new ArrayList<String>();
    private List<String> emails = new ArrayList<String>();
    private List<String> telNumbers = new ArrayList<String>();
    private List<String> birthDates = new ArrayList<String>();
    private String[] temp = new String[5];
    private List<HappyGuy> happyGuys = new ArrayList<HappyGuy>();
    private int indexGuy = 0;
    private int numGuys;
    private String message = new String();
    private String messageChanged = new String();

    // Database.
    private SQLiteDatabase db;
    static final String DB_FILE_NAME = "contact_db";
    static final int DB_VERSION = 1;
    static final String DB_TABLE = "contact_tbl";
    static final String DB_COLUMN_ID = "_id";
    static final String DB_COLUMN_NAME = "name";
    static final String DB_COLUMN_EMAIL = "email";
    static final String DB_COLUMN_TEL = "tel_number";
    static final String DB_COLUMN_BIRTH = "birth_date";

    // -------------onCreate()-----------------------------------
    // When this Activity invoked, this method will be executed.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i("birth", "onCreate() begin.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_birthday_wish);

        // Create a database and a table
        db = openOrCreateDatabase(DB_FILE_NAME, SQLiteDatabase.CREATE_IF_NECESSARY,
                null);
        db.setVersion(DB_VERSION);

        // and put data into the table in background.
        new DataLoadAsyncTask().execute(1);

        // Identify TextView.
        tvName = (TextView) findViewById(R.id.tv_name);
        tvNum = (TextView) findViewById(R.id.tv_num);
        tvMsg = (TextView) findViewById(R.id.tv_msg);

        // Identify RadioGroup and Set the event listener on them.
        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(this);

        // Identify RadioButton and Set the event listener on them.
        rbSMS = (RadioButton) findViewById(R.id.rb_sms);
        rbEmail = (RadioButton) findViewById(R.id.rb_email);

        Log.i("birth", "onCreate() end.");
    }

    // -------------doit()---------------------------------------
    // When Button is hit, this method will be executed.
    public void doit(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_happy:
                checkHappyGuy();
                break;
            case R.id.btn_next:
                displayNext();
                break;
            case R.id.btn_send:
                send();
                break;
        }
    }

    // -------------checkHappyGuy()------------------------------
    // Check the contact_tbl in the database if there is a person
    // whose birth date is today.
    public void checkHappyGuy()
    {
        Cursor cursor;
        int columnIndex = 0;

        // Open the database.
        db = openOrCreateDatabase(DB_FILE_NAME, SQLiteDatabase.CREATE_IF_NECESSARY,
                null);

        // Get the date of today.
        Date today = new Date();
        String[] date = new String[2];
        SimpleDateFormat formatterMonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatterDayOfMonth = new SimpleDateFormat("dd");
        date[0] = formatterMonth.format(today);
        date[1] = formatterDayOfMonth.format(today);
        Log.i("birth", "Got the date of today.");

        // Get the rows whose birth date is today.
        Log.i("birth", "rawQuery() begin. " + date[0] + "/" + date[1]);
        cursor = db.rawQuery("select name, email, tel_number from " + DB_TABLE + " "
                + "where strftime('%m', birth_date) = ? "
                + "and strftime('%d', birth_date) = ? ", date);
        Log.i("birth", "rawQuery() end.");

        // Get the name from the rows.
        cursor.moveToFirst();
        numGuys = cursor.getCount();
        tvNum.setText(Integer.toString(numGuys));
        Log.i("birth", "Get the name from cursor. " + numGuys);
        for (int i = 0; i < numGuys; i++)
        {
            HappyGuy happyGuy = new HappyGuy();

            // Get the value of name column.
            columnIndex = cursor.getColumnIndex(DB_COLUMN_NAME);
            happyGuy.setName(cursor.getString(columnIndex));
            // Get the value of email column.
            columnIndex = cursor.getColumnIndex(DB_COLUMN_EMAIL);
            happyGuy.setEmail(cursor.getString(columnIndex));
            // Get the value of tel_number column.
            columnIndex = cursor.getColumnIndex(DB_COLUMN_TEL);
            happyGuy.setSMS(cursor.getString(columnIndex));

            happyGuys.add(happyGuy);

            cursor.moveToNext();
        }
        cursor.close();
        db.close();

        // Display the name on the TextView tvName.
        if (numGuys == 0)
        {
            tvName.setText("Nobody");
        }
        else
        {
            tvName.setText(happyGuys.get(indexGuy).getName());
        }

        // Change xxx to the name in the message and display it.
        Log.i("birth", "before replaceAll(): " + message);
        messageChanged = message
                .replaceAll("xxx", happyGuys.get(indexGuy).getName());
        Log.i("birth", "after replaceAll(): " + message);
        tvMsg.setText(messageChanged);
    }

    // -------------displayNext()--------------------------------
    // Display the next person's name whose birth date is today
    // one by one.
    public void displayNext()
    {
        // Get the next guy and display it.
        indexGuy++;
        if (indexGuy < numGuys)
        {
            tvName.setText(happyGuys.get(indexGuy).getName());
        }
        else
        {
            indexGuy = 0;
            tvName.setText(happyGuys.get(indexGuy).getName());
        }

        // Change xxx to the name in the message and display it.
        messageChanged = message
                .replaceAll("xxx", happyGuys.get(indexGuy).getName());
        tvMsg.setText(messageChanged);
    }

    // -------------onCheckedChanged()---------------------------
    // When the radio button checked is changed, this method will
    // be executed.
    @Override
    public void onCheckedChanged(RadioGroup rg, int checkedId)
    {
        switch (checkedId)
        {
            case R.id.rb_sms:
                break;
            case R.id.rb_email:
                break;
        }
    }

    // -------------send()---------------------------------------
    // When the Send button is pressed, this will be executed.
    public void send()
    {
        if (rbSMS.isChecked())
        {
            try
            {
                String address = new String(happyGuys.get(indexGuy).getSMS());
                sendSMS(address, messageChanged);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                String address = new String(happyGuys.get(indexGuy).getEmail());
                sendEmail(address, messageChanged);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    // -------------sendSMS()------------------------------------
    // Send SMS.
    private void sendSMS(String address, String message) throws Exception
    {
        SmsManager sm = SmsManager.getDefault();
        sm.sendTextMessage(address, null, message, null, null);
    }

    // -------------sendEmail()------------------------------------
    // Send Email.
    private void sendEmail(String address, String message) throws Exception
    {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + address));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Happy birthday!");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    // ***********DataLoadAsyncTask inner class******************
    // inner class: For background tasks.
    public class DataLoadAsyncTask extends AsyncTask<Integer, Integer, Void>
    {
        @Override
        protected void onPreExecute()
        {
            // text_bg.setText("Sequence numbers bigins.");
        }

        // -----doInBackground()---------------------------------
        // Get data from a file and put the data into the table.
        @Override
        protected Void doInBackground(Integer... args)
        {
            // Get data.
            insert();

            /*
             * for (int i = args[0]; i <= 3; i++) { publishProgress(i);
             * SystemClock.sleep(1000); }
             */
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... args)
        {
            // text_bg.setText(args[0].toString());
        }

        @Override
        protected void onPostExecute(Void result)
        {
            // text_bg.setText("Sequence numbers over.");
        }
    } // end of DataLoadAsyncTask inner class.

    // -------------insert()-------------------------------------
    // Insert data.
    private void insert()
    {
        Log.i("birth", "insert() begin.");
        // Get data from a file.
        parseData(names, emails, telNumbers, birthDates);

        // Create a DB_TABLE table.
        String sqlcmd = "";
        sqlcmd += "drop table if exists " + DB_TABLE + ";";
        db.execSQL(sqlcmd);
        sqlcmd = "";
        sqlcmd += "create table if not exists " + DB_TABLE + " (";
        sqlcmd += DB_COLUMN_ID + " integer primary key autoincrement, ";
        sqlcmd += DB_COLUMN_NAME + " text not null, ";
        sqlcmd += DB_COLUMN_EMAIL + " text not null, ";
        sqlcmd += DB_COLUMN_TEL + " text not null, ";
        sqlcmd += DB_COLUMN_BIRTH + " text not null";
        sqlcmd += ")";
        db.execSQL(sqlcmd);

        // Insert 'contact.txt' data into the table.
        ContentValues cv = new ContentValues();
        for (int i = 0; i < names.size(); i++)
        {
            cv.put(DB_COLUMN_NAME, names.get(i));
            cv.put(DB_COLUMN_EMAIL, emails.get(i));
            cv.put(DB_COLUMN_TEL, telNumbers.get(i));
            cv.put(DB_COLUMN_BIRTH, birthDates.get(i));

            db.insert(DB_TABLE, null, cv);
        }

        db.close();
        Log.i("birth", "insert() end.");
    } // end of insert().

    // -------------parseData()------------------------------
    // Get data from a file in the assets dir and parse the data.
    private void parseData(List<String> names, List<String> emails,
            List<String> telNumbers, List<String> birthDates)
    {
        try
        {
            Log.i("birth", "parseData() begin.");
            AssetManager assets = getResources().getAssets();

            // From contact.txt
            InputStream inputStream = assets.open(FILE_PATH);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inputStream));
            String line;
            int ig = 0;
            while ((line = reader.readLine()) != null)
            {
                // Ignore the first two lines.
                if (ig > 1)
                {
                    temp = line.split(",");

                    names.add(temp[0]);
                    emails.add(temp[1]);
                    telNumbers.add(temp[2]);
                    birthDates.add(temp[3]);
                }
                ig++;
            }

            // From birthday_with.txt
            InputStream inputStream2 = assets.open(FILE_PATH2);
            reader = new BufferedReader(new InputStreamReader(inputStream2));
            while ((line = reader.readLine()) != null)
            {
                message += line + "\n";
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
        Log.i("birth", "parseData() end.");
    } // end of parseData().
}
