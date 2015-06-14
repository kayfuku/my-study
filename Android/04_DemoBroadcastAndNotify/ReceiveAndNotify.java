//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : ReceiveAndNotify.java
//  Date      : 9/7/14
//  Objective : This program receives a message and notify user
//              the information received.
//**************************************************************

package com.cs311d.demobroadcastandnotify;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class ReceiveAndNotify extends BroadcastReceiver
{
    private static final String LOG_TAG = "ReceiveAndNotify";
    private static final int NOTIFY_ME_ID = 1337;

    @Override
    //-------------onReceive()----------------------------------
    // When a broadcast intent is received, this method will be
    // executed.
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();
        if (action != null && action.equals("xx.yy.zz"))
        {
            String msg = intent.getStringExtra("message");
            Log.i(LOG_TAG, "Received Message: " + msg);

            // Notify user.
            notifyUser(context, msg);
        }
    }

    //-------------notifyUser()---------------------------------
    // Notify user of the information received.
    private void notifyUser(Context context, String msg)
    {
        // Get the notification manager.
        NotificationManager mgr =
                (NotificationManager) context.getSystemService(
                Context.NOTIFICATION_SERVICE);

        // Create Notification Object.
        Notification.Builder nb = new Notification.Builder(
                context.getApplicationContext())
                .setContentTitle("title: " + msg)
                .setSmallIcon(R.drawable.ic_message2)
                .setContentText("content text")
                .setTicker("ticker")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true);

        // Set ContentView.
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.google.com"));
        PendingIntent pi = PendingIntent.getActivity(
                context.getApplicationContext(), 0, intent, 0);
        nb.setContentIntent(pi);

        Notification n = nb.build();

        // Send notification.
        mgr.notify(NOTIFY_ME_ID, n);
    }
}
