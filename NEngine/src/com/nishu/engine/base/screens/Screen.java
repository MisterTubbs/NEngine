package com.nishu.engine.base.screens;

import com.nishu.engine.base.interfaces.ScreenObject;

public abstract class Screen implements ScreenObject{

	@Override
	public abstract void init();

	@Override
	public abstract void initGL();

	@Override
	public abstract void update();

	@Override
	public abstract void render();

	@Override
	public abstract void dispose();

}
