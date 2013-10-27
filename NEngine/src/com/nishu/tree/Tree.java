package com.nishu.tree;

import static com.nishu.nengine.base.graphics.GL11.*;

import java.util.Random;

import com.nishu.nengine.base.Game;
import com.nishu.nengine.base.graphics.camera.Camera;
import com.nishu.nengine.base.graphics.color.Color4f;
import com.nishu.nengine.base.graphics.geom.Shape;
import com.nishu.nengine.utilities.Screen;

public class Tree extends Screen {

	private static Game game;
	private Shape shape;
	private Camera camera;

	Random rand;

	public Tree() {
	}

	@Override
	public void initGL() {
	}

	@Override
	public void init() {
		camera = new Camera(game.getWidth(), game.getHeight());
		shape = new Shape();

		rand = new Random();
	}

	@Override
	public void update() {
	}

	@Override
	public void render() {
		clearScreen();
		camera.translate(rand.nextInt(20) - 10, rand.nextInt(20) - 10);
		pushMatrix();
		begin();
		shape.createColoredSquare(new Color4f(0, 0, 1, 0), game.getWidth() / 2, game.getHeight() / 2, 32);
		end();
		popMatrix();

	}

	@Override
	public void resized() {
	}

	@Override
	public void dispose() {
	}

	public static void main(String[] args) {
		game = new Game("Tree", 1280, 720, 60);
		game.addMainScreen(new Tree());
		game.start();
	}

}
