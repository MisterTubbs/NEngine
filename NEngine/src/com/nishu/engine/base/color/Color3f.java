package com.nishu.engine.base.color;

public class Color3f {
	
	public static final Color3f BLACK = new Color3f(0, 0, 0, 1);
	public static final Color3f WHITE = new Color3f(1, 1, 1, 1);
	public static final Color3f BLUE = new Color3f(0, 0, 1, 1);
	public static final Color3f RED = new Color3f(1, 0, 0, 1);
	public static final Color3f GREEN = new Color3f(0, 1, 0, 1);
	
	public float r, g, b, a;
	
	public Color3f(float r, float g, float b, float a){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	public Color3f getColor(){
		return this;
	}

}
