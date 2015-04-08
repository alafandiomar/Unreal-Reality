package com.example.cameraopencv;

import org.opencv.core.Scalar;

import android.R.integer;

public class obj {

	public int xPos,yPos;
	public String type;
	public Scalar HSVmin,HSVmax;
	public Scalar colour;
	
	
	
	public obj(String x){
		type = x;
		
}
	
	
	
	public void setxPos(int x){
		xPos = x;
	}
	
	public int getxPos(){
		return xPos;
	}
	
	public void setyPos(int y){
		yPos = y;
	}
	
	public int getyPos(){
		return yPos;
	}
	
	public void setType(String s){
		type = s;
	}
	
	public String getType(){
		return type;
	}
	
	
	public void setColor(Scalar s){
		colour = s;
	}
	
	public Scalar getColor() {
		return colour;
	}
	
	public void setHSVmin(Scalar s){
		HSVmin = s;
	}
	
	public void setHSVmax(Scalar s){
		HSVmax = s;
	}
	
	public Scalar getHSVmin(){
		return HSVmin;
	}
	
	public Scalar getHSVmax(){
		return HSVmax;
	}	
	
}
