package com.nishu.utils;

import java.util.HashMap;

public class ResourceManager {
	
	private static HashMap<String, Texture> textures = new HashMap<String, Texture>();
	private static HashMap<String, HashMap<?, ?>> userLists = new HashMap<String, HashMap<?, ?>>();
	
	public void addTexture(String name, Texture texture){
		if(textures.get(name) != null) throw new IllegalStateException("Cannot add the same texture twice. Delete the texture first and then re-upload it!");
		else textures.put(name, texture);
	}
	
	public Texture loadTexture(String name){
		if(textures.get(name) == null) throw new IllegalStateException("Could not find the texture with key: " + name);
		return textures.get(name);
	}
	
	public void addList(String name, HashMap<?, ?> map){
		if(userLists.get(name) != null) throw new IllegalStateException("Cannot add the same user list twice. Delete the user list first and then re-upload it!");
		userLists.put(name, map);
	}
	
	public HashMap<?, ?> getList(String name){
		if(userLists.get(name) == null) throw new IllegalStateException("Could not find the user list with key: " + name);
		return userLists.get(name);
	}
	
	public <T> Object getObjectFromList(String name, String objectName){
		if(userLists.get(name) == null) throw new IllegalStateException("Could not find the user list with key: " + name);
		if(userLists.get(name).get(objectName) == null) throw new IllegalStateException("Could not find the object in the user list with key: " + objectName);
		return userLists.get(name).get(objectName);
	}
}
