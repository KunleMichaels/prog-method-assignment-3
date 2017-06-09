/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		buildGame();
		//playGame();
	}
	
	private void buildGame() {
		setSize(WIDTH, HEIGHT);
		buildBricks(0, BRICK_Y_OFFSET);
		buildPaddle();
		//buildBall();
	}
	
	private void buildBricks(double ax, double ay) {
		Color color= null;
		for (int row = 0; row <NBRICK_ROWS; row++) {
		for (int column =0; column < NBRICKS_PER_ROW; column++){
			
	double x = ax + (BRICK_WIDTH + BRICK_SEP) * row;
	double y = ay + (BRICK_HEIGHT + BRICK_SEP) * column;
		
	GRect rect = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
	rect.setFilled(true);
	add(rect);
			if(column<2){
				rect.setColor(Color.RED);
			} else if (column <=3) {
				rect.setColor(Color.ORANGE);
			} else if (column<=5){
				rect.setColor(Color.YELLOW);
			} else if (column<=7){
				rect.setColor(Color.GREEN);
			} else {
				rect.setColor(Color.CYAN);
			}
			
			
			
		}
		}
			
	}

	
	private void buildPaddle() {
		setSize(WIDTH, HEIGHT); 
		double x = (WIDTH -PADDLE_WIDTH)/2;
		double y = (getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		GRect paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
		
	}

	public void mouseMoved(MouseEvent e){
		double x = e.getX();
		if (e.getX() >= 0 && e.getX() < (WIDTH - PADDLE_WIDTH)){
			paddle.setLocation(e.getX(), getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		} else if (e.getX() >= WIDTH) {
			paddle.setLocation(WIDTH - PADDLE_WIDTH, HEIGHT * PADDLE_Y_OFFSET);
		}
		
		
	}
	
	private GPoint last;
	private RandomGenerator rgen = RandomGenerator.getInstance(); 
	private double vx, vy;
	private GRect paddle;

}
