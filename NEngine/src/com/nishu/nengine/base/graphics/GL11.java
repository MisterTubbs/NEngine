package com.nishu.nengine.base.graphics;

import static org.lwjgl.opengl.GL11.*;

import com.nishu.nengine.base.graphics.color.Color4f;

public class GL11 {
	
	public static void clearScreen(){
		glClear(GL_COLOR_BUFFER_BIT);
	}
	
	public static void clearColor(float r, float g, float b, float a){
		glClearColor(r, g, b, a);
	}
	
	public static void setMatrixModeProjection(){
		glMatrixMode(GL_PROJECTION);
	}
	
	public static void loadIdentity(){
		glLoadIdentity();
	}
	
	public static void setMatrixModeModelView(){
		glMatrixMode(GL_MODELVIEW);
	}
	
	public static void setupOrtho(double left, double right, double bottom, double top){
		glOrtho(left, right, bottom, top, 1, -1);
	}
	
	public static void setupViewPort(int x, int y, int width, int height){
		glViewport(x, y, width, height);
	}
	
	public static void translate2f(float x, float y){
		glTranslatef(x, y, 0);
	}
	
	public static void rotate2f(float angle, float x, float y){
		glRotatef(angle, x, y, 0);
	}
	
	public static void popMatrix(){
		glPopMatrix();
	}
	
	public static void pushMatrix(){
		glPushMatrix();
	}
	
	public static int genDisplayList(int id, int range){
		return id = glGenLists(range);
	}
	
	public static void createNewDisplayList(int id){
		glNewList(id, GL_COMPILE);
	}
	
	public static void endDisplayList(){
		glEndList();
	}
	
	public static void vertex2f(float x, float y){
		glVertex2f(x, y);
	}
	
	public static void texture2f(float x, float y){
		glTexCoord2f(x, y);
	}
	
	public static void enableTextures(){
		glEnable(GL_TEXTURE_2D);
	}
	
	public static void disableTextures(){
		glDisable(GL_TEXTURE_2D);
	}
	
	public static void color3f(float r, float g, float b){
		GL11.color4f(r, g, b, 1);
	}
	
	public static void color4f(float r, float g, float b, float a){
		glColor4f(r, g, b, a);
	}
	
	public static void color4f(Color4f color){
		glColor4f(color.getR(), color.getG(), color.getB(), color.getA());
	}
	
	public static void enableBlending(){
		glEnable(GL_BLEND);
	}
	
	public static void disableBlending(){
		glDisable(GL_BLEND);
	}
	
	public static void blendFunctionOneMinusAlpha(){
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void begin(){
		glBegin(GL_QUADS);
	}
	
	public static void end(){
		glEnd();
	}
}
