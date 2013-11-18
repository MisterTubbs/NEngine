package com.nishu.engine.base.graphics;


import java.nio.FloatBuffer;


public class SpriteBatch {

	private FloatBuffer vertices, colorvertices, texturevertices;

	private int maxVerts, drawCount;
	private boolean useDefaultShader = true, active = false;
	private static final int VERTEX_COORDINATE_SIZE = 2, COLOR_COORDINATE_SIZE = 4, TEXTURE_COORDINATE_SIZE = 2;

	public SpriteBatch() {
		this(1000);
	}

	public SpriteBatch(int maxVerts) {
		this.drawCount = 0;
		this.maxVerts = maxVerts;
		vertices = VertexBufferObject.createFloatBuffer(VERTEX_COORDINATE_SIZE, maxVerts);
		colorvertices = VertexBufferObject.createFloatBuffer(COLOR_COORDINATE_SIZE, maxVerts);
		texturevertices = VertexBufferObject.createFloatBuffer(TEXTURE_COORDINATE_SIZE, maxVerts);
		active = false;
	}

	private void createBuffer() {
	}

	public void begin() {
		if (active)
			throw new IllegalStateException("Must call end() before begin()!");
		active = true;
	}

	public void render() {
	}

	public void end() {
		if (!active)
			throw new IllegalStateException("Must call begin() before end()!");
		active = false;
	}

	public static class BatcherVertices {
		float x, y, r, g, b, a, u, v;

		public BatcherVertices(float x, float y, float r, float g, float b, float a, float u, float v) {
			this.x = x;
			this.y = y;
			this.r = r;
			this.g = g;
			this.b = b;
			this.a = a;
			this.u = u;
			this.v = v;
		}
	}

}