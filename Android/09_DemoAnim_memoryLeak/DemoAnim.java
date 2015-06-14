//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : DemoAnim.java
//  Date      : 12/14/14
//  Objective : This program demonstrates how to make animation 
//              and how to stop or resume it and change the 
//              frame rate of the animation. Plus, a little bit 
//              about ImageView.  
//**************************************************************

//package com.cs311d.demoanim;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;


public class DemoAnim extends Activity
{
    private ImageView iv;
    private ToggleButton tglbtn;
    private TextView tv;
    private SeekBar seekBar;
    private AnimationDrawable ad;
    private int duration = 500;
    private boolean isStopped = false;
    private final String TAG = "anim";

    @Override
    //------------- onCreate() ---------------------------------
    // When this Activity invoked, this method is called.
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_demo_anim);

        tglbtn = (ToggleButton) findViewById(R.id.tglbtn);
        iv = (ImageView) findViewById(R.id.iv);
        iv.setScaleType(ImageView.ScaleType.FIT_START); 
        tv = (TextView) findViewById(R.id.tv);
        ad = new AnimationDrawable();

        // Set up the animation.
        setAnimation();

        // Set up the Toggle Button 'tglbtn'.
        tglbtn.setOnClickListener(new MyToggleBtnListener());

        // Set up the SeekBar 'seekBar'.
        seekBar = (SeekBar) findViewById(R.id.sbar);
        seekBar.setMax(1000);
        seekBar.setProgress(duration);
        seekBar.setOnSeekBarChangeListener(new MySeekBarListener());

        tv.setText(Integer.toString(duration));
    } // end of onCreate().

    //------------- setAnimation() -----------------------------
    // Set up the animation.
    private void setAnimation()
    {
        if (isLandscape())
        {
            ad.addFrame(getResources().getDrawable(R.drawable.anim1), 
			            duration);
            ad.addFrame(getResources().getDrawable(R.drawable.anim2), 
			            duration);
            ad.addFrame(getResources().getDrawable(R.drawable.anim3), 
			            duration);
            ad.addFrame(getResources().getDrawable(R.drawable.anim4), 
			            duration);
            ad.addFrame(getResources().getDrawable(R.drawable.anim5), 
			            duration);
            ad.addFrame(getResources().getDrawable(R.drawable.anim6), 
			            duration);
            ad.addFrame(getResources().getDrawable(R.drawable.anim7), 
			            duration);
            ad.addFrame(getResources().getDrawable(R.drawable.anim8), 
			            duration);
        }
        else
        {
            ad.addFrame(getResources().getDrawable(R.drawable.animp1), 
			            duration);
            ad.addFrame(getResources().getDrawable(R.drawable.animp2), 
			            duration);
            ad.addFrame(getResources().getDrawable(R.drawable.animp3), 
			            duration);
            ad.addFrame(getResources().getDrawable(R.drawable.animp4), 
			            duration);
            ad.addFrame(getResources().getDrawable(R.drawable.animp5), 
			            duration);
        }

        ad.setOneShot(false);
        //iv.setBackground(ad);
        iv.setImageDrawable(ad);
        ad.stop();
    }

    //------------- makeAnimationChanged() ---------------------
    // Make a new animation that starts from the current frame
    // and has a new duration if applicable.
    private void makeAnimationChanged()
    {
        int indexFrame;

        // Get the current frame of the animation.
        Drawable currentFrame, checkFrame;
        currentFrame = ad.getCurrent();

        // Get the index of the current frame and
        // Make a new animation that starts from the frame.
        for (int i = 0; i < ad.getNumberOfFrames(); i++)
        {
            checkFrame = ad.getFrame(i);
            if (checkFrame == currentFrame)
            {
                indexFrame = i;

                // Make a new animation that starts from the frame.
                Drawable frame;
                AnimationDrawable adResume = new AnimationDrawable();
                for (int j = indexFrame; j < ad.getNumberOfFrames(); j++)
                {
                    frame = ad.getFrame(j);
                    adResume.addFrame(frame, duration);
                }
                for (int j = 0; j < indexFrame; j++)
                {
                    frame = ad.getFrame(j);
                    adResume.addFrame(frame, duration);
                }

                ad = adResume;
                ad.setOneShot(false);
                //iv.setBackground(ad);
                iv.setImageDrawable(ad);

                if (isStopped)
                {
                    ad.stop();
                }

                break;
            }
        }
    } // end of makeAnimationChanged().

    //------------- isLandscape() ------------------------------
    // Check if the orientation of the device is portrait or landscape.
    private boolean isLandscape()
    {
        return (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE);
    }

    //************* MyToggleBtnListener class ******************
    // This is set on the Toggle Button 'tglbtn'.
    private class MyToggleBtnListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            if (tglbtn.isChecked())
            {
                ad.start();
                isStopped = false;
            }
            else
            {
                ad.stop();
                isStopped = true;

                makeAnimationChanged();
            }
        } // end of onClick().
    } // end of MyToggleBtnListener class.

    //************* MySeekBarListener class ********************
    // This is set on the SeekBar 'seekBar'.
    private class MySeekBarListener 
	                     implements SeekBar.OnSeekBarChangeListener
    {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, 
		                              boolean fromUser)
        {
            // Change the duration.
            duration = progress;

            makeAnimationChanged();

            if (!isStopped)
            {
                ad.start();
            }

            tv.setText(Integer.toString(seekBar.getProgress()));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {
            // Do nothing.
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {
            // Do nothing.
        }
    } // end of MySeekBarListener class.
}
