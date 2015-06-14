//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : Fragment1.java
//  Date      : 9/28/14
//  Objective : Fragment1 has 7 buttons.
//**************************************************************

package com.cs311d.demofragmentf1;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


public class Fragment1 extends Fragment
{
    private TextView tv1;

    private int carId;
    private String selectedCar;
    private String selectedInfo;
    private int price;
    private String power;
    private final String[] CARS = {"Fiat", "Honda", "Corvett",
                                   "Kia", "BMW"};
    private final String[] INFO = {"price", "power"};
    private final int[] VALUES_PRICE = {21000, 20000,
            19000, 18000, 17000, 22000};
    private final String[] VALUES_POWER = {"128HP", "140HP",
            "150HP", "155HP", "160HP", "170HP"};

    // ------------ onCreateView() -----------------------------
    // Create and return the layout that the Fragment uses.
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frag1, container,
                                     false);

        return view;
    } // end of onCreateView().

    // ------------ onStart() ----------------------------------
    // This is called right before the Fragment is displayed.
    @Override
    public void onStart()
    {
        super.onStart();

        tv1 = (TextView) getActivity().findViewById(R.id.tv1);

        // Set the listener to the btn_fiat.
        Button btnFiat = (Button) getActivity().findViewById(
                R.id.btn_fiat);
        btnFiat.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                carId = 0;
                selectedCar = CARS[carId];
                tv1.setText(selectedCar + " selected");
            }
        });

        // Set the listener to the btn_honda.
        Button btnHonda = (Button) getActivity().findViewById(
                R.id.btn_honda);
        btnHonda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                carId = 1;
                selectedCar = CARS[carId];
                tv1.setText(selectedCar + " selected");
            }
        });

        // Set the listener to the btn_corvett.
        Button btnCorvett = (Button) getActivity().findViewById(
                R.id.btn_corvett);
        btnCorvett.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                carId = 2;
                selectedCar = CARS[carId];
                tv1.setText(selectedCar + " selected");
            }
        });

        // Set the listener to the btn_kia.
        Button btnKia = (Button) getActivity().findViewById(
                R.id.btn_kia);
        btnKia.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                carId = 3;
                selectedCar = CARS[carId];
                tv1.setText(selectedCar + " selected");
            }
        });

        // Set the listener to the btn_bmw.
        Button btnBmw = (Button) getActivity().findViewById(
                R.id.btn_bmw);
        btnBmw.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                carId = 4;
                selectedCar = CARS[carId];
                tv1.setText(selectedCar + " selected");
            }
        });

        // Set the listener to the btn_price.
        Button btnPrice = (Button) getActivity().findViewById(
                R.id.btn_price);
        btnPrice.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (selectedCar != null)
                {
                    price = VALUES_PRICE[carId];
                    selectedInfo = INFO[0];
                    showInfo();
                }
                else
                {
                    Context con = getActivity()
                            .getApplicationContext();
                    Toast t = Toast.makeText(con,
                            "Please select a car.",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        // Set the listener to the btn_power.
        Button btnPower = (Button) getActivity().findViewById(
                R.id.btn_power);
        btnPower.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (selectedCar != null)
                {
                    power = VALUES_POWER[carId];
                    selectedInfo = INFO[1];
                    showInfo();
                }
                else
                {
                    Context con = getActivity()
                            .getApplicationContext();
                    Toast t = Toast.makeText(con,
                            "Please select a car.",
                            Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    } // end of onStart().

    // ------------ showInfo() ---------------------------------
    // Show the description of the selected fruit.
    public void showInfo()
    {
        if (isLandscape())
        {
            // Landscape.
            TextView tv2Car = (TextView) getActivity()
                              .findViewById(R.id.tv2_car);
            tv2Car.setText("The " + selectedInfo + " of the " +
                           selectedCar + " is...");

            TextView tv2Value = (TextView) getActivity()
                                .findViewById(R.id.tv2_value);
            DecimalFormat currencyFormatter =
                             new DecimalFormat("$######");
            tv2Value.setText((selectedInfo.equals("price")) ?
                             currencyFormatter.format(price) :
                             power);
        }
        else
        {
            // Portrait or Square.
            // Send the info to ShowInfoActivity.
            Intent intent = new Intent(
                          getActivity().getApplicationContext(),
                          ShowInfoActivity.class);
            intent.putExtra("price", price);
            intent.putExtra("power", power);
            intent.putExtra("selectedCar", selectedCar);
            intent.putExtra("selectedInfo", selectedInfo);
            startActivity(intent);
        }
    } // end of showInfo().

    // ------------ isLandscape() ------------------------------
    // Check if the orientation of the device is portrait or 
	// landscape.
    private boolean isLandscape()
    {
        return (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE);
    }
} // end of Fragment1 class.
