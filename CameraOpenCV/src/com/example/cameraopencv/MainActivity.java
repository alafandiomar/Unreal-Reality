package com.example.cameraopencv;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	Button btnSignIn;
	Button btnSignUp;
	 Button btnCameraStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnCameraStart = (Button) findViewById(R.id.btnStartCam);
        
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnCameraStart.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		Intent i = null;
		switch(v.getId()){
			case R.id.btnSignIn:
				i = new Intent(this,SignInActivity.class);
				break;
			case R.id.btnSignUp:
				i = new Intent(this,SignUpActivity.class);
				break;
			case R.id.btnStartCam:
				i = new Intent(this,CameraActivity.class);
				break;
		}
		startActivity(i);
	}


    
}
