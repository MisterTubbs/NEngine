package com.nishu.utils;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class SpriteBatch {

	public static int TYPE_DYNAMIC;
	public static int TYPE_STATIC;

	private FloatBuffer vertices, colorVertices, textureVertices;

	private ShaderProgram currentShader;
	private Texture currentTexture, defaultTexture = Texture.createEmptyTexture();
	private Color4f currentColor, defaultColor = Color4f.DEFAULT;

	private int type, size, currentSize, vID, cID, tID;
	private boolean render2D, active;

	public SpriteBatch() {
		this(TYPE_STATIC, 10000, true);
	}

	public SpriteBatch(int type, int size, boolean render2D) {
		this.type = type;
		this.size = size;
		this.render2D = render2D;
		this.active = false;
		this.currentSize = 0;

		createBuffers();
	}

	private void createBuffers() {
		if (render2D) {
			vertices = BufferUtils.createFloatBuffer(2 * size);
		} else if (!render2D) {
			vertices = BufferUtils.createFloatBuffer(3 * size);
		}
		colorVertices = BufferUtils.createFloatBuffer(4 * size);
		textureVertices = BufferUtils.createFloatBuffer(2 * size);

		if (type == TYPE_STATIC) {
			vID = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, vID);
			glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);
			glBindBuffer(GL_ARRAY_BUFFER, 0);

			cID = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, cID);
			glBufferData(GL_ARRAY_BUFFER, colorVertices, GL_STATIC_DRAW);
			glBindBuffer(GL_ARRAY_BUFFER, 0);

			tID = glGenBuffers();
			glBindBuffer(GL_ARRAY_BUFFER, cID);
			glBufferData(GL_ARRAY_BUFFER, textureVertices, GL_STATIC_DRAW);
			glBindBuffer(GL_ARRAY_BUFFER, 0);
		}
	}

	public void begin() {
		if (active)
			throw new IllegalStateException("Must call end() before begin()!");
		active = true;
	}

	public void render() {
		glEnable(GL_TEXTURE_2D);

		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_COLOR_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);

		if (type == TYPE_STATIC)
			renderStatic();
		if (type == TYPE_DYNAMIC)
			renderDynamic();

		glDrawElements(GL_TRIANGLES, vertices.remaining() + colorVertices.remaining() + textureVertices.remaining(), GL_UNSIGNED_INT, 0);

		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_COLOR_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
	}

	private void renderStatic() {
		glBindBuffer(GL_ARRAY_BUFFER, vID);
		if (render2D) {
			glVertexPointer(2, GL_FLOAT, 0, 0);
		} else if (!render2D) {
			glVertexPointer(3, GL_FLOAT, 0, 0);
		}

		glBindBuffer(GL_ARRAY_BUFFER, cID);
		glColorPointer(4, GL_FLOAT, 0, 0);

		glBindBuffer(GL_ARRAY_BUFFER, tID);
		glTexCoordPointer(2, GL_FLOAT, 0, 0);
	}

	private void renderDynamic() {
		if (render2D) {
			glVertexPointer(2, 0, vertices);
		}else if(!render2D){
			glVertexPointer(3, 0, vertices);
		}
		glColorPointer(4, 0, colorVertices);
		glTexCoordPointer(2, 0, textureVertices);
	}

	public void end() {
		if (!active)
			throw new IllegalStateException("Must call begin() before end()!");

		vertices.flip();
		colorVertices.flip();
		textureVertices.flip();

		render();

		vertices.clear();
		colorVertices.clear();
		textureVertices.clear();

		active = false;
	}
	
	public void dispose(){
		glDeleteBuffers(vID);
		glDeleteBuffers(cID);
		glDeleteBuffers(tID);
	}
}
