//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : DemoFragmentF1.java
//  Date      : 9/28/14
//  Objective : This app program demonstrates how to handle the
//              Fragments.
//**************************************************************

package com.cs311d.demofragmentf1;


import android.app.Activity;
import android.os.Bundle;
import android.view.Window;


public class DemoFragmentF1 extends Activity
{
    @Override
    // ----------- onCreate() ----------------------------------
    // When this Activity invoked, this method is executed.
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_demo_fragment_f1);
    }
}
