package com.nishu.nengine.base.graphics.camera;

import static com.nishu.nengine.base.graphics.GL11.*;

import org.lwjgl.opengl.Display;

public class Camera {
	
	public Camera(int width, int height){
		setMatrixModeProjection();
		loadIdentity();
		
		setMatrixModeModelView();
		setupOrtho(0, width, height, 0);
		setupViewPort(0, 0, Display.getWidth(), Display.getHeight());
	}
	
	public void translate(float x, float y){
		translate2f(x, y);
	}

}
