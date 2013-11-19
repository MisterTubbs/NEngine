package com.nishu.engine.base.graphics;

import static com.nishu.engine.base.graphics.BUtils.disableState;
import static com.nishu.engine.base.graphics.BUtils.drawArrays;
import static com.nishu.engine.base.graphics.BUtils.enableState;
import static com.nishu.engine.base.graphics.BUtils.pointer;
import static com.nishu.engine.base.graphics.BaseGL.COLOR_ARRAY_POINTER;
import static com.nishu.engine.base.graphics.BaseGL.GL_COLOR_ARRAY;
import static com.nishu.engine.base.graphics.BaseGL.GL_TEXTURE_COORD_ARRAY;
import static com.nishu.engine.base.graphics.BaseGL.GL_TRIANGLES;
import static com.nishu.engine.base.graphics.BaseGL.GL_VERTEX_ARRAY;
import static com.nishu.engine.base.graphics.BaseGL.GLenableTexture;
import static com.nishu.engine.base.graphics.BaseGL.TEXTURE_COORD_ARRAY_POINTER;
import static com.nishu.engine.base.graphics.BaseGL.VERTEX_ARRAY_POINTER;

import java.nio.FloatBuffer;

import com.nishu.engine.base.Game;
import com.nishu.engine.base.color.Color4f;
import com.nishu.engine.base.resources.ResourceManager;
import com.nishu.engine.base.resources.Texture;
import com.nishu.shaderutils.ShaderProgram;

public class SpriteBatch {

	private FloatBuffer vertices, colorvertices, texturevertices;
	private ResourceManager rm = Game.getRm();
	private ShaderProgram currentShaderProgram, defaultShaderProgram = rm.loadDefaultShader();
	private Texture currentTexture, defaultTexture = Texture.createEmptyTexture();
	private Color4f currentColor, defaultColor = Color4f.DEFAULT;

	private int maxVerts, drawCount, currentVerticeCount;
	private boolean useDefaultShader = true, active = false;
	private static final int VERTEX_COORDINATE_SIZE = 2, COLOR_COORDINATE_SIZE = 4, TEXTURE_COORDINATE_SIZE = 2;

	public SpriteBatch() {
		this(100000);
	}

	public SpriteBatch(int maxVerts) {
		this.drawCount = 0;
		this.currentVerticeCount = 0;
		this.maxVerts = maxVerts;
		vertices = BUtils.createFloatBuffer(VERTEX_COORDINATE_SIZE, maxVerts);
		colorvertices = BUtils.createFloatBuffer(COLOR_COORDINATE_SIZE, maxVerts);
		texturevertices = BUtils.createFloatBuffer(TEXTURE_COORDINATE_SIZE, maxVerts);
		active = false;
	}

	public void begin() {
		if (active)
			throw new IllegalStateException("Must call end() before begin()!");
		active = true;
	}

	public void render() {
		useShader();
		GLenableTexture();

		enableState(GL_VERTEX_ARRAY);
		enableState(GL_COLOR_ARRAY);
		enableState(GL_TEXTURE_COORD_ARRAY);

		pointer(VERTEX_ARRAY_POINTER, VERTEX_COORDINATE_SIZE, vertices);
		pointer(COLOR_ARRAY_POINTER, COLOR_COORDINATE_SIZE, colorvertices);
		pointer(TEXTURE_COORD_ARRAY_POINTER, TEXTURE_COORDINATE_SIZE, texturevertices);

		drawArrays(GL_TRIANGLES, 0, drawCount);

		disableState(GL_VERTEX_ARRAY);
		disableState(GL_COLOR_ARRAY);
		disableState(GL_TEXTURE_COORD_ARRAY);

		releaseShader();

		drawCount++;
	}

	public void end() {
		if (!active)
			throw new IllegalStateException("Must call begin() before end()!");
		active = false;
	}

	public void useShader() {
		if (useDefaultShader) {
			end();
			defaultShaderProgram.use();
		} else {
			useDefaultShader = false;
			end();
			currentShaderProgram.use();
		}
	}

	public void setShader(String name) {
		end();
		if (currentShaderProgram.equals(rm.getShaderProgram(name)))	return;
		currentShaderProgram = rm.getShaderProgram(name);
	}

	public void releaseShader() {
		if (currentShaderProgram.equals(null))
			return;
		if (active)
			end();
		currentShaderProgram.release();
	}

	public void bindTexture(Texture texture) {
		end();
		if (currentTexture != null)
			unbindTexture();
		currentTexture = texture;
		if (currentTexture == null)
			currentTexture = defaultTexture;
		bindTexture();
	}

	public void bindTexture(String name) {
		end();
		if (currentTexture != null)
			unbindTexture();
		currentTexture = rm.getTexture(name);
		if (currentTexture == null)
			currentTexture = defaultTexture;
		bindTexture();
	}

	private void bindTexture() {
		currentTexture.bind();
	}

	private void unbindTexture() {
		currentTexture.delete();
		currentTexture = defaultTexture;
	}

	public void setColor(Color4f color) {
		end();
		currentColor = color;
	}

	public void setColor(float r, float g, float b, float a) {
		end();
		currentColor = new Color4f(r, g, b, a);
	}

	public void clearColor() {
		end();
		currentColor = defaultColor;
	}

	private void flush() {
		end();
		begin();
	}

	public void setVertex(float x, float y) {
		setVertex(x, y, Color4f.DEFAULT.r, Color4f.DEFAULT.g, Color4f.DEFAULT.b, Color4f.DEFAULT.a, 0, 0);
	}

	public void setVertex(float x, float y, float r, float g, float b, float a) {
		setVertex(x, y, r, g, b, a, 0, 0);
	}

	public void setVertex(float x, float y, Color4f color) {
		setVertex(x, y, color.r, color.g, color.b, color.a, 0, 0);
	}

	public void setVertex(float x, float y, float u, float v) {
		setVertex(x, y, Color4f.DEFAULT.r, Color4f.DEFAULT.g, Color4f.DEFAULT.b, Color4f.DEFAULT.a, u, v);
	}

	public void setVertex(float x, float y, Color4f color, float u, float v) {
		setVertex(x, y, color.r, color.g, color.b, color.a, u, v);
	}

	public void setVertex(float x, float y, float r, float g, float b, float a, float u, float v) {
		setVertex(new BatcherData(x, y, r, g, b, a, u, v));
	}

	public void setVertex(BatcherData data) {
		if (currentVerticeCount >= maxVerts - 1)
			flush();
		vertices.put(data.x).put(data.y);
		colorvertices.put(data.r).put(data.g).put(data.b).put(data.a);
		texturevertices.put(data.u).put(data.v);

		currentVerticeCount++;
	}

	public static class BatcherData {
		float x, y, r, g, b, a, u, v;

		public BatcherData(float x, float y, float r, float g, float b, float a, float u, float v) {
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