package com.nishu.engine.base.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

import java.nio.FloatBuffer;

public class BaseGL {
	
	public static int GL_TRIANGLES;
	public static int GL_VERTEX_ARRAY, GL_COLOR_ARRAY, GL_TEXTURE_COORD_ARRAY, VERTEX_ARRAY_POINTER, COLOR_ARRAY_POINTER, TEXTURE_COORD_ARRAY_POINTER;
	public static int PROJECTION_MATRIX, MODELVIEW_MATRIX;
	
	public static void GLmatrixMode(int mode){
		glMatrixMode(mode);
	}
	
	public static void GLloadIdentity(){
		glLoadIdentity();
	}
	
	public static void GLpushMatrix(){
		glPushMatrix();
	}
	
	public static void GLpopMatrix(){
		glPopMatrix();
	}
	
	public static void GLortho(double left, double right, double bottom, double top, double zNear, double zFar){
		glOrtho(left, right, bottom, top, zNear, zFar);
	}
	
	public static void GLviewport(int x, int y, int width, int height){
		glViewport(x, y, width, height);
	}
	
	public static void GLclearScreen(){
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
	
	public static void GLpointer(int size, int type, FloatBuffer data) {
		if(type == VERTEX_ARRAY_POINTER) glVertexPointer(size, 0, data);
		if(type == COLOR_ARRAY_POINTER) glColorPointer(size, 0, data);
		if(type == TEXTURE_COORD_ARRAY_POINTER) glTexCoordPointer(size, 0, data);
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
