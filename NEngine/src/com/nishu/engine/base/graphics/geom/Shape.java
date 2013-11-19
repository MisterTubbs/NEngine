package com.nishu.engine.base.graphics.geom;

public class Shape {
	
	public static float[] createSquare(float[] data, float x, float y, float size){
		data = new float[4];
		data[0] = x;
		data[1] = y;
		data[2] = x + size;
		data[3] = y + size;
		
		return data;
	}

}
