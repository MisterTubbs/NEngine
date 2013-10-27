package com.nishu.nengine.base.graphics.utilities.images;

import com.nishu.nengine.utilities.error.ErrorHandler;

public class SpriteSheet {
	
	private Texture texture;
	private int width, height;
	private float unitScale;
	private String location;
	
	public SpriteSheet(String location){
		this.location = location;
		texture = Texture.loadTexture(location);
		width = texture.getWidth();
		height = texture.getHeight();
		unitScale = (float) 1 / height;
	}
	
	public Texture getTextureAt(int x, int y){
		Texture texture = null;
		if(x > width || x < 0 || y > height || y < 0) return null;
		else{
			texture = Texture.loadTexture(location, x, y);
		}
		if(texture == null) ErrorHandler.printError("Error Loading Texture Data At x == " + x + " Y == " + y);
		return texture;
	}
	
	public void bind(){
		texture.bind();
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
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

	public float getUnitScale() {
		return unitScale;
	}

	public void setUnitScale(float unitScale) {
		this.unitScale = unitScale;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
