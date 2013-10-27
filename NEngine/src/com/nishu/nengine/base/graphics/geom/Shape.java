package com.nishu.nengine.base.graphics.geom;

import static com.nishu.nengine.base.graphics.GL11.*;

import com.nishu.nengine.base.graphics.color.Color4f;
import com.nishu.nengine.base.graphics.utilities.images.SpriteSheet;

public class Shape {
	
	public void createSquare(float x, float y, float size){
		createSquare(x, y, size, size);
	}
	
	public void createSquare(float x, float y, float width, float height){
		vertex2f(x, y);
		vertex2f(x + width, y);
		vertex2f(x + width, y + height);
		vertex2f(x, y + height);		
	}
	
	public void createColoredSquare(Color4f color, float x, float y, float size){
		createColoredSquare(color, x, y, size, size);
	}
	
	public void createColoredSquare(Color4f color, float x, float y, float width, float height){
		color4f(color);
		vertex2f(x, y);
		vertex2f(x + width, y);
		vertex2f(x + width, y + height);
		vertex2f(x, y + height);	
	}
	
	public void createTexturedSquare(SpriteSheet sheet, float x, float y, float tx, float ty, float size){
		createTexturedSquare(sheet, x, y, tx, ty, size, size);
	}
	
	public void createTexturedSquare(SpriteSheet sheet, float x, float y, float tx, float ty, float width, float height){
		texture2f(tx, ty);
		vertex2f(x, y);
		texture2f(tx + sheet.getUnitScale(), ty);
		vertex2f(x + width, y);
		texture2f(tx + sheet.getUnitScale(), ty + sheet.getUnitScale());
		vertex2f(x + width, y + height);
		texture2f(tx, ty + sheet.getUnitScale());
		vertex2f(x, y + height);		
	}

}
