package com.nishu.engine.base.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import static com.nishu.engine.base.graphics.BaseGL.*;

public class BUtils {
	
	public static FloatBuffer createFloatBuffer(int dimensions, int numCoords){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(dimensions * numCoords); 
		return buffer;
	}
	
	public static void putVertices(FloatBuffer buffer, float[] vertices){
		buffer.put(vertices);
	}
	
	public static void rewindBuffer(FloatBuffer buffer){
		buffer.rewind();
	}
	
	public static int genBuffers(){
		int id = GLgenBuffer();
		return id;
	}
	
	public static void bindBuffer(int target, int id){
		GLbindBuffer(target, id);
	}
	
	public static void bufferData(int target, FloatBuffer data, int usage){
		GLbufferData(target, data, usage);
	}
	
	public static void pointer(int type, int size, FloatBuffer data){
		GLpointer(size, type, data);
	}
	
	public static void enableState(int state){
			GLenableClientState(state);
	}
	
	public static void drawArrays(int mode, int first, int count){
		GLdrawArrays(mode, first, count);
	}
	
	public static void disableState(int state){
		GLdisableClientState(state);
	}
	
	public static void disposeBuffer(int id){
		GLdisposeBuffer(id);
	}
	
	public static void diposeBuffer(int... id){
		for(int i = 0; i < id.length; i++){
			disposeBuffer(id[i]);
		}
	}

}
