package com.example.cameraopencv;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import android.R.bool;
import android.R.integer;
import android.app.Activity;
import android.opengl.Matrix;
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
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.utils.Converters;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
public class CameraActivity extends Activity implements CvCameraViewListener2{

	protected static final String TAG = null;
	public Mat rgba,HSV,threshold;
	obj apple;
	private static double mMinContourArea = 0.1;
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
		apple = new obj("apple");
		apple.setHSVmin(new Scalar(0,0,0));
		apple.setHSVmax(new Scalar(255,255,255));
		
		
		
	}

	@Override
	public void onCameraViewStopped() {
		// TODO Auto-generated method stub
		rgba.release();
	}
	
	public void morphOps() {
		Mat erodeElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT,new Size(3,3));
		Mat dilateElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(8,8));
		
		Imgproc.erode(threshold,threshold,erodeElement);
		Imgproc.erode(threshold,threshold,erodeElement);

		Imgproc.dilate(threshold,threshold,dilateElement);
		Imgproc.dilate(threshold,threshold,dilateElement);
	}
	
	
	/*public void track(obj thefruit) {*/
	/*	Vector<obj> objects;
		Mat temp = new Mat();
		
		threshold.copyTo(temp);
        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Mat hierarchy = null;

		Imgproc.findContours(temp, contours, hierarchy, Imgproc.RETR_CCOMP,Imgproc.CHAIN_APPROX_SIMPLE);
		
		double refArea = 0;
		boolean objectFound = false;
        Iterator<MatOfPoint> each = contours.iterator();

        // Find max contour area
        double maxArea = 0;
        while (each.hasNext()) {
        	
            MatOfPoint wrapper = each.next();
            double area = Imgproc.contourArea(wrapper);
            if (area > 400)
            {
                obj apple;
                apple.setxPos(x);
                
            
            }
        }

	}
		
	*/

	@Override
	public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
		rgba = inputFrame.rgba();
		Core.circle(rgba,new Point(rgba.width()/2.0, rgba.height()/2.0),40, new Scalar(0, 0, 0), 2);
		Core.line(rgba, new Point(rgba.width()/2.0 - 40, rgba.height()/2.0), new Point(rgba.width()/2.0 + 40, rgba.height()/2.0), new Scalar(0, 0, 0), 2);
		Core.line(rgba, new Point(rgba.width()/2.0 , rgba.height()/2.0 - 40), new Point(rgba.width()/2.0, rgba.height()/2.0 + 40), new Scalar(0, 0, 0), 2);
		
/*		Imgproc.cvtColor(inputFrame.rgba(),HSV , Imgproc.COLOR_BGR2HSV);
		Core.inRange(HSV,apple.getHSVmin(),apple.getHSVmax(), threshold);
		morphOps();
		
		
		*/
		
		
		
		
		return rgba;
	}
}
