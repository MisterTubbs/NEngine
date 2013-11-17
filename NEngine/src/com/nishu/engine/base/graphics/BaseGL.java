package com.nishu.engine.base.graphics;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL11.glEnableClientState;
import static org.lwjgl.opengl.GL11.glVertexPointer;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;

public class BaseGL {
	
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
	
	public static void GLdiposeBuffer(int id){
		glDeleteBuffers(id);
	}
}
