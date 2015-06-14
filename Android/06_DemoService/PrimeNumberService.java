//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : PrimeNumberService.java
//  Date      : 10/25/14
//  Objective : This program do the job as Service that takes 3 
//              arguments and generates 'num' number of prime 
//				numbers in the range from 'f' to 't'.
//**************************************************************

package com.cs311d.demoservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class PrimeNumberService extends Service
{
    private final IBinder mb = new MyBinder();
    private static final String TAG = "DemoService";
    private int latestStartId;
    public boolean isStopped = false;

    //************* MyBinder inner class ***********************
    // The Activity uses this class to access to the Service.
    public class MyBinder extends Binder
    {
        //--------- getService() -------------------------------
        PrimeNumberService getService()
        {
            return PrimeNumberService.this;
        }
    }

    //------------- genPrimeNum() ------------------------------
    // Generate unique random num of prime numbers from f to t.
    public ArrayList<Integer> genPrimeNum(int f, int t, int num)
    {
        Log.i(TAG, "Service: genPrimeNum() called.");
        final int  MAX_PRIME = t;
        boolean[] primes = new boolean[MAX_PRIME + 1];
        ArrayList<Integer> primeNumOutputs = new ArrayList<Integer>();
        ArrayList<Integer> selectedPrimeNums = new ArrayList<Integer>();

        for (int primeNum = 0; primeNum < MAX_PRIME + 1; primeNum++)
        {
            primes[primeNum] = true;
        }

        primes[0] = false;
        primes[1] = false;

        // Pick up prime numbers.
        for (int primeNum = 0; primeNum < MAX_PRIME + 1; primeNum++)
        {
            if (primes[primeNum])
            {
                // Prime numbers to be displayed.
                if (primeNum >= f)
                {
                    primeNumOutputs.add(primeNum);
                }

                // Multiple of primeNum is not a prime number.
                for (int i = 2; i * primeNum < MAX_PRIME + 1; i++)
                {
                    primes[i * primeNum] = false;
                }
            }
        }

        // Pick up num of prime numbers in the primeNumOutputs.
        // Unique random numbers of them.
        int len = primeNumOutputs.size();
        int rand;
        for (int i = 0; i < num; i++)
        {
            rand = randomInRange(0, len - 1);
            selectedPrimeNums.add(i, primeNumOutputs.get(rand));
            primeNumOutputs.remove(rand);
            len--;
            if (len == 0)
            {
                break;
            }
        }

        return selectedPrimeNums;
    } // end of genPrimeNum().

    //------------- randomInRange() ----------------------------
    // Returns the random number in the specified range.
    private int randomInRange(int low, int high)
    {
        return ((int)(Math.random() * (high - low + 1)) + low);
    }

    //------------- showToast() --------------------------------
    // The Activity calls this method.
    public void showToast(String msg)
    {
        Log.i(TAG, "Service: showToast() called.");
        Toast.makeText(getApplicationContext(), msg,
                       Toast.LENGTH_SHORT).show();
    }

    //------------- onCreate() ---------------------------------
    // When this Service invoked, this method is executed.
    public void onCreate()
    {
        Log.i(TAG, "Service: onCreate() called.");
        super.onCreate();
    }

    //------------- onStartCommand() ---------------------------
    public int onStartCommand(Intent i, int flags, int startId)
    {
        Log.i(TAG, "Service: onStartCommand() called. " +
                "startId: " + startId + ", flags: " + flags);
        latestStartId = startId;

        return (START_STICKY);
    }

    @Override
    //------------- onBind() -----------------------------------
    // When the Service is bound, this method is called.
    public IBinder onBind(Intent i)
    {
        Log.i(TAG, "Service: onBind() called.");
        return mb;
    }

    @Override
    //------------- onUnbind() --------------------------------
    public boolean onUnbind(Intent intent)
    {
        Log.i(TAG, "Service: onUnbind() called.");

        if (latestStartId >= 1 && latestStartId <= 5)
        {
            isStopped = stopSelfResult(latestStartId);
            Log.i(TAG, "Service: stopSelfResult(startId) called. " +
                    "latestStartId: " + latestStartId);
        }

        return super.onUnbind(intent);
    }

    @Override
    //------------- onDestroy() --------------------------------
    public void onDestroy()
    {
        Log.i(TAG, "Service: onDestroy() called.");
        super.onDestroy();
    }
}
