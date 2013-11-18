package com.nishu.engine.base.resources;

import java.util.HashMap;

import com.nishu.shaderutils.Shader;
import com.nishu.shaderutils.ShaderProgram;

public class ResourceManager {

	private final HashMap<String, Texture> textures = new HashMap<String, Texture>();
	private final HashMap<String, ShaderProgram> shaderPrograms = new HashMap<String, ShaderProgram>();
	
	public void loadTexture(String name, Texture texture){
		textures.put(name, texture);
	}
	
	public Texture getTexture(String name){
		return textures.get(name);
	}
	
	public void dispose(){
		textures.clear();
	}
	
	public void loadShaderProgram(String name, String vertShader, String fragShader){
		ShaderProgram program = null;
		Shader shaders = new Shader(vertShader, fragShader);
		program = new ShaderProgram(shaders.getvShader(), shaders.getfShader());
		shaderPrograms.put(name, program);
	}
	
	public ShaderProgram loadDefaultShader(){
		return shaderPrograms.get("Defualt_Shader");
	}
	
}
