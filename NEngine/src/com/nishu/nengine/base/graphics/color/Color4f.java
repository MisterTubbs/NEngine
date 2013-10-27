package com.nishu.nengine.base.graphics.color;

public class Color4f {
	
	private float r, g, b, a;
	
	public Color4f(float r, float g, float b, float a){
		checkColors(r, g, b, a);
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	private void checkColors(float r, float g, float b, float a){
		if(r > 1) r /= 255;
		if(r < 0) r = 0;
		
		if(g > 1) g /= 255;
		if(g < 0) g = 0;
		
		if(b > 1) b /= 255;
		if(b < 0) b = 0;
		
		if(a > 1) a /= 255;
		if(a < 0) a = 0;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	public float getA() {
		return a;
	}

	public void setA(float a) {
		this.a = a;
	}

}
