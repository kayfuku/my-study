//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : SensorInfo.java
//  Date      : 11/11/14
//  Objective : This program shows the values from the sensor 
//              that user selects. 
//**************************************************************

//package com.cs311d.demosensor;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;


public class SensorInfo extends Activity
                        implements SensorEventListener
{
    SensorManager sm;
    Sensor sen;
    TextView tvValue, tvAccuracy;
    String strAccuracy = "";
    String strValue = "";

    @Override
    //------------ onCreate() ----------------------------------
    // When this Activity invoked, this method is called.
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_info);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        tvAccuracy = (TextView) findViewById(R.id.tv_a);
        tvValue = (TextView) findViewById(R.id.tv_v);

        // Check which sensor user selected.
        Intent intent = getIntent();
        int type = intent.getIntExtra("type", 0);
        sen = sm.getDefaultSensor(type);
    }

    @Override
    //------------- onResume() ---------------------------------
    protected void onResume()
    {
        super.onResume();
        // Turn on the sensor.
        sm.registerListener(this, sen,
                            SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    //------------- onPause() ---------------------------------
    protected void onPause()
    {
        super.onPause();
        // Turn off the sensor.
        sm.unregisterListener(this, sen);
    }

    //------------- onAccuracyChanged() ------------------------
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
    	strAccuracy = "";
        strAccuracy = "SENSOR NAME: " + sensor.getName() + "\n" + 
        	   "Accuracy : " +
               accuracy + (accuracy == 1 ? "(Low)" :
               (accuracy == 2 ? "(Medium)" : "(High)") + "\n");
        tvAccuracy.setText(strAccuracy);
    }

    //------------- onSensorChanged() --------------------------
    public void onSensorChanged(SensorEvent se)
    {
    	strValue = "";
        strValue = "  x: " + se.values[0] + "\n" +
                   "  y: " + se.values[1] + "\n" +
                   "  z: " + se.values[2] + "\n";
        tvValue.setText(strValue);
    }
}
