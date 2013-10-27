package com.nishu.nengine.utilities;

public abstract class ScreenObject {
	
	public abstract void initGL();
	public abstract void init();
	public abstract void update();
	public abstract void render();
	public abstract void resized();
	public abstract void dispose();
	
}
