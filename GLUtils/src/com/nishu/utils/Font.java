package com.nishu.utils;

public class Font {
	
	private Texture texture;

	public Texture loadFont(String name, String location){
		texture = Texture.loadTexture(location);
		ResourceManager.addTexture(name, texture);
		return texture;
	}
	
	public void bind(){
		texture.bind();
	}

}
