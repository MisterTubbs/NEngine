package com.nishu.engine.base.entities;

import com.nishu.engine.base.math.Vector2f;

public class Entity {
	
	private Vector2f pos;
	private float angle;
	
	public Entity(float x, float y){
		this(new Vector2f(x, y), 0);
	}
	
	public Entity(float x, float y, float angle){
		this(new Vector2f(x, y), angle);
	}
	
	public Entity(Vector2f pos, float angle){
		this.pos = pos;
		this.angle = angle;
	}
	
	public void setPosition(float x, float y){
		pos.set(x, y);
	}
	
	public void setRotation(float angle){
		this.angle = angle;
		pos.rotate(angle);
	}
	
}
