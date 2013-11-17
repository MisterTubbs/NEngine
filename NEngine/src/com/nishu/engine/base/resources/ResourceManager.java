package com.nishu.engine.base.resources;

import java.util.HashMap;

public class ResourceManager {

	private final HashMap<String, Texture> textures = new HashMap<String, Texture>();
	
	public void loadTexture(String name, Texture texture){
		textures.put(name, texture);
	}

}
