package com.nishu.nengine.base;

import com.nishu.nengine.utilities.Screen;
import com.nishu.nengine.utilities.ScreenObject;
import com.nishu.nengine.utilities.Time;

public class Game extends ScreenObject{
	
	public int width, height, fpsTarget;
	public String title;
	private boolean isRunning;
	
	private Screen currentScreen;
	
	public Game(String title, int width, int height, int fpsTarget){
		Window.createWindow(title, width, height, fpsTarget);

		setRunning(false);
		setTitle(title);
		setWidth(width);
		setHeight(height);
		setFpsTarget(fpsTarget);
	}
	
	public void addMainScreen(Screen screen){
		currentScreen = screen;
		
		initGL();
		init();
	}
	
	public void addScreen(Screen screen){
		currentScreen.dispose();
		currentScreen = screen;
		
		currentScreen.initGL();
		currentScreen.init();
	}
	
	public void start(){
		if(isRunning()) return;
		setRunning(true);
		run();
	}
	
	private void run(){
		int frames = 0; 
		int frameCounter = 0;
		
		final double frameTime = 1.0f / getFpsTarget();
		
		long lastTime = Time.getTime();
		double unprocessedTime = 0;
		
		while(isRunning){
			boolean render = false;
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double) Time.SECOND;
			frameCounter += passedTime;
			
			while(unprocessedTime > frameTime){
				render = true;
				unprocessedTime -= frameTime;
				
				if(Window.isClosed()){
					stop();
				}
				
				Time.setDelta(frameTime);
				
				update();
				
				if(frameCounter >= Time.SECOND){
					System.out.println("FPS: " + frames);
					frames = 0; 
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
		dispose();
	}
	
	public void stop(){
		if(!isRunning()) return;
		setRunning(false);
	}
	
	public void pause(){
	}

	@Override
	public void initGL() {
		currentScreen.initGL();
	}

	@Override
	public void init() {
		currentScreen.init();
	}

	@Override
	public void update() {
		Window.update();
		currentScreen.update();
	}

	@Override
	public void render() {
		currentScreen.render();
	}

	@Override
	public void resized() {
		currentScreen.resized();
	}
	
	@Override
	public void dispose() {
		currentScreen.dispose();
		Window.dispose();
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
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
	
	public int getFpsTarget() {
		return fpsTarget;
	}

	public void setFpsTarget(int fpsTarget) {
		this.fpsTarget = fpsTarget;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public Screen getCurrentScreen() {
		return currentScreen;
	}

	public void setCurrentScreen(Screen currentScreen) {
		this.currentScreen = currentScreen;
	}
}
