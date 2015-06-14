//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : DemoService.java
//  Date      : 10/25/14
//  Objective : This app demonstrates how to use Service and
//              how Activity interacts with Service.
//**************************************************************

package com.cs311d.demoservice;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

// import the inner class in the Service.
import com.cs311d.demoservice.PrimeNumberService.MyBinder;

import java.util.ArrayList;


public class DemoService extends Activity
{
    private PrimeNumberService mPrimeNumberService = null;
    private boolean isBound = false;
    private static final String TAG = "DemoService";
    private TextView tv;
    private final String SECRET_CODE = "kill";

    private ServiceConnection scon = new ServiceConnection()
    {
        @Override
        //--------- onServiceConnected() -----------------------
        // When the Activity is bound with the Service, this
        // method is called.
        public void onServiceConnected(ComponentName cn, IBinder ib)
        {
            Log.i(TAG, "Activity: onServiceConnected() called.");
            tv.setText("Service started.");
            MyBinder mb = (MyBinder) ib;
            mPrimeNumberService = mb.getService();
            isBound = true;
        }

        @Override
        //--------- onServiceDisconnected() --------------------
        // When the Service is disconnected accidentally, this
        // method is called.
        public void onServiceDisconnected(ComponentName name)
        {
            Log.i(TAG, "Activity: onServiceDisconnected() called.");
            mPrimeNumberService = null;
            isBound = false;
        }
    };

    @Override
    //------------ onCreate() ----------------------------------
    // When this Activity invoked, this method is executed.
    protected void onCreate(Bundle b)
    {
        Log.i(TAG, "Activity: onCreate() called.");
        super.onCreate(b);
        setContentView(R.layout.activity_demo_service);

        final EditText etFrom = (EditText) findViewById(R.id.et_from);
        final EditText etTo = (EditText) findViewById(R.id.et_to);
        final EditText etNum = (EditText) findViewById(R.id.et_num);
        final EditText etCode = (EditText) findViewById(R.id.et_code);
        tv = (TextView) findViewById(R.id.tv);

        // Set the listener to btn_start.
        Button btnStart = (Button) findViewById(R.id.btn_start);
        btnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getApplicationContext(),
                                      PrimeNumberService.class);
                //bindService(i, scon, Context.BIND_AUTO_CREATE);
                startService(i);
                Log.i(TAG, "Activity: startService() called.");

                bindService(i, scon, 0);
                Log.i(TAG, "Activity: bindService() called.");

            }
        });

        // Set the listener to btn_generate.
        Button btnGenerate = (Button) findViewById(R.id.btn_generate);
        btnGenerate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!etFrom.getText().toString().equals("") &&
                    !etTo.getText().toString().equals("")   &&
                    !etTo.getText().toString().equals(""))
                {
                    int f = Integer.parseInt(etFrom.getText().toString());
                    int t = Integer.parseInt(etTo.getText().toString());
                    int num = Integer.parseInt(etNum.getText().toString());
                    tv.setText("");

                    if (mPrimeNumberService != null)
                    {
                        ArrayList<Integer> primeNums;
                        if (f >= 0 && f < t &&
                            num <= t - f && num > 0 &&
                            t > 0 && t <= 1000000)
                        {
                            // Remote Procedure Call(RPC).
                            primeNums = mPrimeNumberService
                                            .genPrimeNum(f, t, num);
                            for (Integer n: primeNums)
                            {
                                tv.append(Integer.toString(n) + "\n");
                            }
                        }
                        else
                        {
                            tv.setText("Please enter " +
                                       "from < to && num <= to - from " +
                                       "from > 0 && to > 0 && num > 0");
                        }
                    }
                    else
                    {
                        tv.setText("First, please start Service.");
                    }
                } // end of if (!etFrom...).
            } // end of onClick().
        });

        // Set the listener to btn_stop.
        Button btnStop = (Button) findViewById(R.id.btn_stop);
        btnStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isBound)
                {
                    String secretCode = etCode.getText().toString();
                    if (secretCode.equals(SECRET_CODE))
                    {
                        unbindService(scon);
                        Log.i(TAG, "Activity: unbindService() called.");

                        tv.setText("");
                        isBound = false;
                    }
                    else
                    {
                        tv.setText("The code is invalid.");
                    }
                }
            } // end of onClick().
        });
    } // end of onCreate().

    @Override
    //------------- onStart() ----------------------------------
    protected void onStart()
    {
        Log.i(TAG, "Activity: onStart() called.");
        super.onStart();
    }

    @Override
    //------------- onStop() -----------------------------------
    protected void onStop()
    {
        Log.i(TAG, "Activity: onStop() called.");
        super.onStop();
        if (isBound)
        {
            Log.i(TAG, "Activity: unbindService() called.");
            unbindService(scon);

            Intent i = new Intent(getApplicationContext(),
                                  PrimeNumberService.class);
            stopService(i);
            Log.i(TAG, "Activity: stopService() called.");

            isBound = false;
        }
    }
}
