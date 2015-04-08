package com.example.cameraopencv;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{

	Button btnSignIn;
	Button btnSignUp;
	Button btnCameraStart;
NetworkComponent nc;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		nc = new NetworkComponent();
		btnSignIn = (Button) findViewById(R.id.btnSignIn);
		btnSignUp = (Button) findViewById(R.id.btnSignUp);
		btnCameraStart = (Button) findViewById(R.id.btnStartCam);

		btnSignIn.setOnClickListener(this);
		btnSignUp.setOnClickListener(this);
		btnCameraStart.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		Intent i = null;
		switch (v.getId())
		{
		case R.id.btnSignIn:
			//nc.sendhello();
			//i = new Intent(this, ClientActivity.class);
			i = new Intent(this, SignInActivity.class);
			break;
		case R.id.btnSignUp:
			//NetworkComponent.sendhello();
			i = new Intent(this, SignUpActivity.class);
			break;
		case R.id.btnStartCam:
			//NetworkComponent.sendhello();
			i = new Intent(this, CameraActivity.class);
		case R.id.testNetwork:
			
			//NetworkComponent.sendhello();
			//i = new Intent(this, SignInActivity.class);
			//NetworkComponent.sendhello();NetworkComponent.sendhello();
			break;
		}
		startActivity(i);
	}
}
