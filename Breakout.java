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
	
	private static final double GRAVITY = 3.0;
	
	private static final int DELAY = 50;
	
	private RandomGenerator rgen = RandomGenerator.getInstance(); 
	private double vx, vy;
	private double vySTART = +3.0;
	private double vxSTART = 0.0;
	

	
	private GRect paddle;
	
	private GRect brick;
	
	private GOval ball;
	
	private GObject collObj;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		buildGame();
		playGame();
	}
	
	private void buildGame() {
		
		setSize(WIDTH, HEIGHT);
		buildBricks(0, BRICK_Y_OFFSET);
		buildPaddle();
		buildBall();
	}
	
	private void buildBricks(double ax, double ay) {
		Color color= null;
		for (int row = 0; row <NBRICK_ROWS; row++) {
		for (int column =0; column < NBRICKS_PER_ROW; column++){
			
	double x = ax + (BRICK_WIDTH + BRICK_SEP) * row;
	double y = ay + (BRICK_HEIGHT + BRICK_SEP) * column;
		
	GRect brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
	brick.setFilled(true);
	add(brick);
			if(column<2){
				brick.setColor(Color.RED);
			} else if (column <=3) {
				brick.setColor(Color.ORANGE);
			} else if (column<=5){
				brick.setColor(Color.YELLOW);
			} else if (column<=7){
				brick.setColor(Color.GREEN);
			} else {
				brick.setColor(Color.CYAN);
			}
				
			
		}
		}
			
	}

	
	private void buildPaddle() {
		setSize(WIDTH, HEIGHT); 
		double x = (WIDTH -PADDLE_WIDTH)/2;
		double y = (getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		paddle = new GRect(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
		addMouseListeners();
		
	}

	public void mouseMoved(MouseEvent e){
		double y = (getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		if (e.getX() >= 0 && e.getX() < (getWidth() - PADDLE_WIDTH)){
		paddle.setLocation(e.getX(), y);
		} else if (e.getX() > WIDTH) {
			paddle.setLocation(WIDTH, y);
		}
		
		
	}
	private void buildBall() {
		ball = new GOval(getWidth()/2, getHeight()/2, 10, 10);
		ball.setFilled(true);
		add(ball);
	}
	
	
	private void playGame() {
		while (ball.getX() < getWidth() && ball.getY() < getHeight()) {  
			moveBall();
			checkforCollision();
			pause(DELAY);
		}
	
			
	}
	
	

	private void moveBall() {
		//vy = vySTART;
		vx = rgen.nextDouble(1.0, 3.0);     
		if (rgen.nextBoolean(0.5)) vx = -vx;
		vy += GRAVITY;
		ball.move(0, vy);

		
		
		
	}

	private void checkforCollision() {
		collidewithwall();
		collidewithPaddle();
		collidewithBrick();
		
		
	}
	
	private void collidewithwall() {
		if (ball.getY() > getHeight() - BALL_RADIUS){
			vy = -vy;
			double diff = ball.getY() - (getHeight() - BALL_RADIUS);
			ball.move(vx, -2 * diff); 
		}

		
	}
	
	private void collidewithBrick() {
		collObj = getElementAt(ball.getX(), ball.getY());
		if (collObj == brick) {
		remove(brick);
		}
		
	}

	private void collidewithPaddle(){
		collObj = getElementAt(ball.getX(), ball.getY());
		if (collObj == paddle) {
			vy = -vy;
			
		}
		
	}
		

	private void pause() {
		
		
	}

}
