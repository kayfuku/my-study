//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : Fragment2.java
//  Date      : 9/28/14
//  Objective : Fragment2 displays the info from Fragment1.
//**************************************************************

package com.cs311d.demofragmentf1;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Fragment2 extends Fragment
{
    // ------------ onCreateView() -----------------------------
    // Create and return the layout that the Fragment uses.
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        return (inflater.inflate(R.layout.frag2, container,
                                 false));
    }
}
