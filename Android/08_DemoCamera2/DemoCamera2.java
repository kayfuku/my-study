//**************************************************************
//  Author    : Fukutani, Kei
//  File Name : DemoCamera2.java
//  Date      : 11/17/14
//  Objective : This app demonstrates how to take a photo and 
//              display the photo using intent.
//**************************************************************

//package com.cs311d.democamera2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class DemoCamera2 extends Activity
{
	private static final int PICTURE_ACTIVITY_CODE = 1;
    private EditText et;
    private ImageView iv;
    private TextView tv;
	// Flag for avoiding like double-clicking snap button. 
    private boolean mIsTaken = false;  
    private String strFileName;
	private File picFile;
	private Bitmap capturedImage;

	@Override
	//------------- onCreate() ---------------------------------
    // Called when the Activity is first created.
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_camera2);
		
		et = (EditText) findViewById(R.id.et);
		iv = (ImageView) findViewById(R.id.iv);
		tv = (TextView) findViewById(R.id.tv_name);
		
		// Button Snap.
        Button btnSnap = (Button) findViewById(R.id.btn_snap);
        btnSnap.setOnClickListener(new View.OnClickListener()
        {
            @Override
			//----- onClick() ----------------------------------
			// Called when Snap button is hit.
            public void onClick(View v)
            {
            	if (!mIsTaken)
            	{
            	    // Avoid something like double-clicking 
					// Snap button. 
            		mIsTaken = true;
            		
            		// Get the file name.
            		strFileName = makeFileName();
            		picFile = new File(
            			Environment.getExternalStorageDirectory()
						+ "/test", 
            			strFileName);
        			
            		// Implicit intent. Invoke the app 
					// that has a camera function.
            		Intent intent = 
					    new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            		Uri outputFileUri = Uri.fromFile(picFile);
        			intent.putExtra(MediaStore.EXTRA_OUTPUT, 
					                outputFileUri);
        			startActivityForResult(intent, 
					                       PICTURE_ACTIVITY_CODE);
            	}
            }
        });
        
        // Button Show.
        Button btnShow = (Button) findViewById(R.id.btn_show);
        btnShow.setOnClickListener(new View.OnClickListener()
        {
            @Override
			//----- onClick() ----------------------------------
			// Called when Show button is hit.
            public void onClick(View v)
            {
            	iv.setImageBitmap(capturedImage);
            	
				// Resize and rotate the image.
				Matrix mtrx = new Matrix();
				iv.setScaleType(ScaleType.MATRIX);
				mtrx.postRotate(90.0f, 
				                iv.getWidth()/2, 
				                iv.getHeight()/2);
				iv.setImageMatrix(mtrx);
				
				tv.setText(strFileName);
            }
        });
	} // end of onCreate().

	@Override
	//------------- onResume() ---------------------------------
    // Called when the Activity comes forward again.
	protected void onResume()
	{
		super.onResume();
		mIsTaken = false;
	}
	
	@Override
	//------------- onActivityResult() -------------------------
	protected void onActivityResult(int requestCode, 
		                            int resultCode, 
		                            Intent data) 
	{	
		if(requestCode == PICTURE_ACTIVITY_CODE)
		{
			if (resultCode == RESULT_OK)
			{	
				try 
				{
					FileInputStream in = 
					    new FileInputStream(picFile);
					BitmapFactory.Options options = 
					    new BitmapFactory.Options();
					options.inSampleSize = 7;
					capturedImage = 
					    BitmapFactory.decodeStream(in,
							                       null,
							                       options);
				} 
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				}
				
				mIsTaken = false;
			}
		}
	} // end of onActivityResult().
	
	//------------- makeFileName() -----------------------------
	// Make a file name.
	private String makeFileName()
	{
		String strFileName;
		
		if (et.getText().toString().equals(""))
		{
			// Get the current time for the file name.
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat formatter = 
			    new SimpleDateFormat("yyyyMMdd_HHmmss");
		    strFileName = formatter.format(cal.getTime()) + ".jpg";
		}
		else
		{
			strFileName = et.getText().toString() + ".jpg";
		}
		
		return strFileName;
	}
}
