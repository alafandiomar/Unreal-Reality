package com.example.cameraopencv;

import java.lang.reflect.Method;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class CameraActivity extends Activity implements CvCameraViewListener2{

	protected static final String TAG = null;
	public Mat rgba;
	//Fields
	private BaseLoaderCallback mLoaderCallBack = new BaseLoaderCallback(this) {
		@Override
		public void onManagerConnected(int status){
			switch(status){
			case LoaderCallbackInterface.SUCCESS:
			{
				Log.i(TAG,"OPENCV Loaded Successfully");
				mOpenCvCameraView.enableView();
				break;
			}
			default:
			{
				super.onManagerConnected(status);
			}
			}
		}
	};
	private JavaCameraView mOpenCvCameraView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cameramain);
		
		
		mOpenCvCameraView = (JavaCameraView)findViewById(R.id.MainActivityCameraView);
		setDisplayOrientation(mOpenCvCameraView, 90);
		mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
		mOpenCvCameraView.setCvCameraViewListener(this);
		
	}

	protected void setDisplayOrientation(JavaCameraView mOpenCvCameraView2, int angle){
	    Method downPolymorphic;
	    try
	    {
	        downPolymorphic = mOpenCvCameraView2.getClass().getMethod("setDisplayOrientation", new Class[] { int.class });
	        if (downPolymorphic != null)
	            downPolymorphic.invoke(mOpenCvCameraView2, new Object[] { angle });
	    }
	    catch (Exception e1)
	    {
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	public void onResume() {
		super.onResume();
		OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_9, this, mLoaderCallBack);
		
	}
	
	public void onDestroy() {
	super.onDestroy();	
	if(mOpenCvCameraView != null){
		mOpenCvCameraView.disableView();
	}
	}

	@Override
	public void onCameraViewStarted(int width, int height) {
		// TODO Auto-generated method stub
		rgba = new Mat();
		obj apple = new obj("apple");
		apple.setHSVmin(new Scalar(0,0,0));
		apple.setHSVmax(new Scalar(255,255,255));
		
		
	}

	@Override
	public void onCameraViewStopped() {
		// TODO Auto-generated method stub
		rgba.release();
	}

	@Override
	public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
		rgba = inputFrame.rgba();
		
		
		
		
		
		return inputFrame.rgba();
	}
}
