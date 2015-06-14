//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : ShowInfoActivity.java
//  Date      : 9/28/14
//  Objective : This Activity holds Fragment2 when the 
//              orientation of the device is portrait.
//**************************************************************

package com.cs311d.demofragmentf1;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import java.text.DecimalFormat;


public class ShowInfoActivity extends Activity
{
    // ----------- onCreate() ----------------------------------
    // When this Activity invoked, this method is executed.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (isLandscape())
        {
            finish(); // landscape mode already taken care.
            return;
        }
        setContentView(R.layout.frag2);

        // Get and display the info.
        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String selectedCar = extras.getString("selectedCar");
            String selectedInfo = extras.getString("selectedInfo");
            int price = extras.getInt("price");
            String power = extras.getString("power");

            TextView tv2Car = 
			        (TextView) findViewById(R.id.tv2_car);
            tv2Car.setText("The " + selectedInfo + " of the " +
                    selectedCar + " is...");

            TextView tv2Value = 
			        (TextView) findViewById(R.id.tv2_value);
            DecimalFormat currencyFormatter =
                    new DecimalFormat("$######");
            tv2Value.setText((selectedInfo.equals("price")) ?
                    currencyFormatter.format(price) :
                    power);
        }
    } // end of onCreate().

    // ------------ isLandscape() ------------------------------
    // Check if the orientation of the device is portrait or
    // landscape.
    private boolean isLandscape()
    {
        return (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE);
    }
}
