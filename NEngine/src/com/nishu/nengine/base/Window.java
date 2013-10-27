package com.nishu.nengine.base;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
	
	private static int fpsTarget;
	
	public static void createWindow(String title, int width, int height, int fpsTarget){
		try{
			Display.setTitle(title);
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setResizable(true);
			Display.create();
		}catch(LWJGLException e){
			e.printStackTrace();
		}
	}
	
	public static boolean isClosed(){
		return Display.isCloseRequested();
	}
	
	public static void update(){
		Display.update();
		Display.sync(fpsTarget);
	}
	
	public static void dispose(){
		Display.destroy();
	}

}
