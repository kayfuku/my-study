//**************************************************************
// Author    : Kei Fukutani
// File Name : BubbleActivity.java
// Date      : 3/24/14
// Objective : This program draws a circle with random color and
//             random position when the button Bubble is hit and 
//             clears the circles drawn when the button Clear is 
//             hit.
//**************************************************************

package com.example.graphicsdemo;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class BubbleActivity extends Activity implements OnClickListener
{
    DrawView dv;
    Button btn1, btn2;
    String BTN1_TEXT = "Clear";
    String BTN2_TEXT = "Bubble";
    int[] dim = new int[2];
    int DV_ID = 1, BTN_CLEAR_ID = 2, BTN_BUBBLE_ID = 3;
    Toast t;

    // *************onCreate(Bundle savedInstanceState)**********
    // When the Activity is created, this method will be executed.
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_bubble);

        // Create a RelativeLayout object and layout it.
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(relativeLayout);

        // Create a DrawView object and layout it.
        dv = new DrawView(this);
        dv.setBackgroundColor(Color.WHITE);
        dv.setId(DV_ID);
        dim = dv.getScreenSize();
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, (int) (dim[1] * 0.75));
        dv.setLayoutParams(params);
        relativeLayout.addView(dv);

        // Create a LinearLayout object and layout it
        // for the buttons.
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        params = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.BELOW, 1);
        linearLayout.setLayoutParams(params);
        relativeLayout.addView(linearLayout);

        // Create a Clear Button and layout it.
        btn1 = new Button(this);
        btn1.setText(BTN1_TEXT);
        btn1.setId(BTN_CLEAR_ID);
        btn1.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        btn1.setOnClickListener(this);
        linearLayout.addView(btn1);

        // Create a Bubble Button and Layout it.
        btn2 = new Button(this);
        btn2.setText(BTN2_TEXT);
        btn2.setId(BTN_BUBBLE_ID);
        btn2.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        btn2.setOnClickListener(this);
        linearLayout.addView(btn2);
    } // end of onCreate().

    // *************Inner class DrawView*************************
    public class DrawView extends View
    {
        ArrayList<Circle> drawList = new ArrayList<Circle>();
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        Circle circle;
        int xCircle, yCircle, rCircle, cCircle;
        final int R_MIN = 3, R_MAX = 100;

        // *********Constructor 1********************************
        public DrawView(Context con)
        {
            super(con);
            p.setColor(Color.BLACK);
        }

        // *********Constructor 2********************************
        public DrawView(Context context, AttributeSet attrs)
        {
            super(context, attrs);
        }

        // *********Constructor 3********************************
        public DrawView(Context context, AttributeSet attrs, int defStyle)
        {
            super(context, attrs, defStyle);
        }

        // *********onDraw(Canvas c)*****************************
        // When the Activity is created, this method will be
        // executed.
        @Override
        public void onDraw(Canvas c)
        {
            // Paint the entire DrawView screen white.
            c.drawColor(Color.WHITE);

            // Redraw the data in the list.
            for (int i = 0; i < drawList.size(); i++)
            {
                circle = drawList.get(i);
                p.setColor(circle.getColor());
                c.drawCircle(circle.getX(), circle.getY(), circle.getR(), p);
            }

            // For testing.
            Log.v("testBubble", "onDraw() called! " + drawList.size());
        }

        // *********bubble()*************************************
        // Draw a circle on the DrawView screen.
        public void bubble()
        {
            // Get a random color and random position.
            cCircle = Color.rgb(randomInRange(0, 255), randomInRange(0, 255),
                    randomInRange(0, 255));
            dim = getScreenSize();
            xCircle = randomInRange(0, dim[0]);
            yCircle = randomInRange(0, (int) (dim[1] * 0.75));
            rCircle = randomInRange(R_MIN, R_MAX);
            p.setStyle(Paint.Style.FILL);

            // Put the circle data in the list.
            circle = new Circle();
            circle.setX(xCircle);
            circle.setY(yCircle);
            circle.setR(rCircle);
            circle.setColor(cCircle);
            drawList.add(circle);
            invalidate();

            // For testing.
            Log.v("testBubble", "bubble() called!");
        }

        // *********clear()**************************************
        // Clear the screen.
        public void clear()
        {
            drawList.clear();
            invalidate();

            // For testing.
            Log.v("testBubble", "clear() called!");
        }

        // *********getScreenSize()******************************
        // Get the size of the screen.
        public int[] getScreenSize()
        {
            int dim[] = {0, 0};
            Point p = new Point();
            WindowManager wm = getWindowManager();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
            {
                wm.getDefaultDisplay().getSize(p);
                dim[0] = p.x;
                dim[1] = p.y;
            }
            else
            {
                Display d = wm.getDefaultDisplay();
                dim[0] = d.getWidth();
                dim[1] = d.getHeight();
            }

            return dim;
        }

        // *********randomInRange(int low, int high)*************
        // Returns the random number in the specified range.
        public int randomInRange(int low, int high)
        {
            return ((int) (Math.random() * (high - low + 1)) + low);
        }
    }

    // *************onClick(View v)******************************
    // When the Button is hit, this method will be executed.
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case 2: // The Clear Button.
                dv.clear();
                break;
            case 3: // The Bubble Button.
                dv.bubble();

                // For testing.
                Log.v("testBubble", "Bubble Button clicked!");

                break;
            default:
                break;
        }
    }
}
