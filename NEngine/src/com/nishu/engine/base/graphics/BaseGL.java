package com.nishu.engine.base.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;

public class BaseGL {
	
	public static int GL_TRIANGLES;
	public static int GL_VERTEX_ARRAY, GL_COLOR_ARRAY, GL_TEXTURE_COORD_ARRAY, VERTEX_ARRAY_POINTER, COLOR_ARRAY_POINTER, GL_TEXTURE_COORD_ARRAY_POINTER;
	
	public static void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public static void GLclearColor(float r, float g, float b, float a){
		glClearColor(r, g, b, a);
	}
	
	public static int GLgenBuffer(){
		int id = glGenBuffers();
		return id;
	}
	
	public static void GLbindBuffer(int target, int id){
		glBindBuffer(target, id);
	}
	
	public static void GLbufferData(int target, FloatBuffer data, int usage){
		glBufferData(target, data, usage);
	}
	
	public static void GLvertexPointer(int size, int type, int stride, long pointer_buffer_offset){
		glVertexPointer(size, type, stride, pointer_buffer_offset);
	}
	
	public static void GLenableClientState(int clientState){
		glEnableClientState(clientState);
	}
	
	public static void GLdrawArrays(int mode, int first, int count){
		glDrawArrays(mode, first, count);
	}
	
	public static void GLdisableClientState(int clientState){
		glDisableClientState(clientState);
	}
	
	public static void GLdisposeBuffer(int id){
		glDeleteBuffers(id);
	}
	
	public static void GLenableTexture(){
		glEnable(GL_TEXTURE_2D);
	}
	
	public static void GLdisableTexture(){
		glDisable(GL_TEXTURE_2D);
	}
}
