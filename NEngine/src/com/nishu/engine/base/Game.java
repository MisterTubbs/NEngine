package com.nishu.engine.base;

import static com.nishu.engine.base.graphics.BaseGL.*;

import org.lwjgl.opengl.Display;

import com.nishu.engine.base.interfaces.ScreenObject;
import com.nishu.engine.base.resources.ResourceManager;
import com.nishu.engine.base.screens.Screen;
import com.nishu.engine.base.utilites.Time;

public class Game implements ScreenObject{
	
	private int width, height, targetFPS;
	private boolean running = false;
	public static boolean debug = true;
	
	private Screen currentScreen;
	private static ResourceManager rm;
	
	public Game(String title, int width, int height, int targetFPS, boolean mouseCaptured){
		Window.createWindow(width, height, title, mouseCaptured);
		this.width = width;
		this.height = height;
		this.targetFPS = targetFPS;

		initGL();
		init();
	}
	

	@Override
	public void initGL() {
		GLmatrixMode(PROJECTION_MATRIX);
		GLloadIdentity();
		
		GLmatrixMode(MODELVIEW_MATRIX);
		GLortho(0, width, height, 0, 1, -1);
		GLviewport(0, 0, Display.getWidth(), Display.getHeight());
	}
	
	@Override
	public void init() {
		rm = new ResourceManager();
		rm.loadShaderProgram("Default_Shader", "/default_shader.vert", "/default_shader.frag");
	}
	
	public void start(){
		if(running) return;
		running = true;
	}
	
	public void setScreen(Screen screen){
		currentScreen.dispose();
		currentScreen = screen;
		currentScreen.initGL();
		currentScreen.init();
	}
	
	public void run(){
		int frames = 0;
		int frameCounter = 0;
		
		final double frameTime = 1.0f / targetFPS;
		
		long lastTime = Time.getTime();
		double unprocessed = 0;
		
		while(running){
			boolean render = false;
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessed += passedTime;
			frameCounter += passedTime;
			
			while(unprocessed > frameTime){
				render = true;
				unprocessed -= frameTime;
				
				if(Window.isWindowCloseRequested()) end();
				Time.setDelta(frameTime);
				
				update();
				
				if(frameCounter >= Time.SECOND){
					if(debug){
						System.out.println("FPS: " + frames);
						frames = 0;
					}
					frameCounter = 0;
				}
			}
			if(render){
				render();
				frames++;
			}else{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@Override
	public void update() {
		Window.update();
		if(currentScreen != null){
			currentScreen.update();
		}
	}

	@Override
	public void render() {
		if(currentScreen != null){
			currentScreen.render();
		}
	}

	public void end(){
		if(!running) return;
		running = false;
		dispose();
	}
	
	@Override
	public void dispose() {
		if(currentScreen != null){
			currentScreen.dispose();
		}
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public static ResourceManager getRm() {
		return rm;
	}

}
