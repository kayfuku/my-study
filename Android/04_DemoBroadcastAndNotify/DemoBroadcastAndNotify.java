//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : DemoBroadcastAndNotify.java
//  Date      : 9/7/14
//  Objective : This app program demonstrates how to notify 
//              information when receiving broadcast messages. 
//              This program sends a message input by user when 
//              the user push the button.
//**************************************************************

package com.cs311d.demobroadcastandnotify;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DemoBroadcastAndNotify extends Activity
        implements View.OnKeyListener
{
    private EditText etInput;
    private String message;
    public static String BSTRING = "xx.yy.zz";

    @Override
    //-------------onCreate()-----------------------------------
    // When this Activity invoked, this method will be executed.
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_broadcast_and_notify);

        // Identify EditText and Set the event listener on it.
        etInput = (EditText)findViewById(R.id.et);
        etInput.setOnKeyListener(this);
    }

    @Override
    //-------------onKey()--------------------------------------
    // When Enter key is hit, this method will be executed.
    public boolean onKey(View v, int keyCode, KeyEvent ke)
    {
        if ((ke.getAction() == KeyEvent.ACTION_UP) &&
                (keyCode == KeyEvent.KEYCODE_ENTER))
        {
            message = etInput.getText().toString();

            // Do nothing until user input something.
            if (message.equals(""))
            {
                Toast t = Toast.makeText(getBaseContext(),
                        "Enter message.",
                        Toast.LENGTH_LONG);
                t.show();
            }
            else
            {
                send();
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
            case R.id.btn:
                send();
                break;
        }
    }

    //-------------send()---------------------------------------
    // Broadcast a message that user input through an intent.
    public void send()
    {
        message = etInput.getText().toString();

        // Broadcast an intent.
        Intent intent = new Intent();
        intent.putExtra("message", message);
        intent.setAction(BSTRING);
        sendBroadcast(intent);
    }
}
