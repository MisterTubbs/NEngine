package com.nishu.engine.base.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import static com.nishu.engine.base.graphics.BaseGL.*;

public class VertexBufferObject {
	
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
	
	public static void vertexPointer(int size, int type, int stride, long pointer_buffer_offset){
		GLvertexPointer(size, type, stride, pointer_buffer_offset);
	}
	
	public static void enableStates(int... states){
		if(states.length > 1){
			for(int i = 0; i < states.length; i++){
				GLenableClientState(states[i]);
			}
		}else{
			GLenableClientState(states[0]);
		}
	}
	
	public static void drawArrays(int mode, int first, int count){
		GLdrawArrays(mode, first, count);
	}
	
	public static void disableStates(int... states){
		if(states.length > 1){
			for(int i = 0; i < states.length; i++){
				GLdisableClientState(states[i]);
			}
		}else{
			GLdisableClientState(states[0]);
		}
	}
	
	public static void diposeBuffer(int id){
		GLdiposeBuffer(id);
	}

}
