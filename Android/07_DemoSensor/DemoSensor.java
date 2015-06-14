//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : DemoSensor.java
//  Date      : 11/11/14
//  Objective : This app demonstrates how to use ListView and 
//              how to get values from the sensors of the device.
//**************************************************************

//package com.cs311d.demosensor;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class DemoSensor extends Activity
{
    ArrayList<String> list;
    ArrayAdapter<String> itemsAdapter;
    ListView lvSensors;
    List<Sensor> listSensors;

    @Override
    //------------ onCreate() ----------------------------------
    // When this Activity invoked, this method is called.
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_sensor);

        lvSensors = (ListView) findViewById(R.id.lvSensors);
        list = new ArrayList<String>();

        // Set the list to the adapter.
        itemsAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        // Set the adapter to the ListView.
        lvSensors.setAdapter(itemsAdapter);

        // Set the listener to the ListView.
        setupListViewListener();

        // Get the info of the sensors that the device has.
        listSensors = getSensors();
        int len = listSensors.size();

        for (int i = 0; i < len; i++)
        {
            list.add(listSensors.get(i).getName());
        }
    }

    //------------- getSensors() -------------------------------
    // Get the info of the sensors that the device has.
    private List<Sensor> getSensors()
    {
        SensorManager sm =
            (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        listSensors = sm.getSensorList(Sensor.TYPE_ALL);

        return listSensors;
    }

    //------------- setupListViewListener() --------------------
    // Set the listener to the ListView.
    private void setupListViewListener()
    {
        lvSensors.setOnItemClickListener(
                new AdapterView.OnItemClickListener()
                {
                    @Override
                    //------- onItemClick() --------------------
                    // This method is called when the element in
                    // the ListView tapped.
                    public void onItemClick(AdapterView<?> parent,
                                            View view, int position,
                                            long id)
                    {
                        // Get the element has just been tapped.
                        Sensor sen = listSensors.get(position);
                        int senType = sen.getType();

                        // Invoke SensorInfo.
                        Intent intent = new Intent(DemoSensor.this,
                                SensorInfo.class);
                        intent.putExtra("resourceID", list);
                        intent.putExtra("type", senType);
                        startActivity(intent);
                    }
                });
    }
}
