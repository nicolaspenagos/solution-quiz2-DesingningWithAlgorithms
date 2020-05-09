
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/

package ui;

import processing.core.PApplet;

public class Square implements Runnable{
	
	//------------------------------------- 
	// Constants 
	//------------------------------------- 
	public final static char RED = 'R';
	public final static char WHITE = 'W';
	public final static char BLACK = 'B';
	
	public final static char UP = 'U';
	public final static char MIDDLE = 'M';
	public final static char DOWN = 'D';
	public final static char GO_UP = 'A';
	public final static char GO_DOWN = 'B';
	
	//-------------------------------------
    // Atributtes
    //-------------------------------------
	private int posX;
	private int posY;
	private boolean stop;
	private char position;
	private char color;
	private char direction;
	private boolean parity;
	private PApplet app;
	private int size;
	
	//-------------------------------------
	// Constructor
	//-------------------------------------
	public Square(int posX, int posY,boolean parity,int size, char color ,PApplet app) {
		
		this.posX   = posX;
		this.posY   = posY;
		this.app    = app;
		this.parity = parity;
		this.size   = size;
		this.color = color;
		this.direction = GO_UP;
		this.position = MIDDLE;
		this.stop = false;
		
		
	}
	
	//-------------------------------------
	// Methods
	//-------------------------------------
	public void toDraw() {
		
		app.fill(255, 0, 0);
		if(color == BLACK) {
			app.fill(0);
		}else if(color == WHITE) {
			app.fill(255);
		}
		app.noStroke();
		app.rect(posX, posY, size, size);
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(!stop) {
				Thread.sleep(1000);
				simpleMove();
			}	

		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
	}
	
	public void simpleMove() {
		
		if(!stop) {
			if(position == MIDDLE) {
				posY += 20;
				position = UP;
			}else {
				posY -= 20;
				position = MIDDLE;
			}
		}
		
	}
	
	/*
	 * This method is an alternative of movement since in the statement it is not clear when specifying how the movement should be.
	 * To be used, the line 83 must be modified simpleMove() --> complexMove()
	 */
	public void complexMove() {
		
		if(position == MIDDLE) {
			if(direction == GO_UP) {
				posY += 20;
				position = UP;
			}else {
				posY -= 20;
				position = DOWN;
			}
			
			if(position == MIDDLE) {
				if(direction == GO_DOWN) {
					posY -= 20;
					position = DOWN;
				}else {
					posY += 20;
					position = MIDDLE;
				}
			}
			
		}else if(position == UP) {
			posY -= 20;
			position = MIDDLE;
			direction = GO_DOWN;
		}else {
			posY += 20;
			position = MIDDLE;
			direction = GO_UP;
		}
	}
	
	//-------------------------------------
	// Getters and Setters
	//-------------------------------------
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

	public boolean getParity() {
		return parity;
	}
	
	public void setColor(char color) {
		this.color = color;
	}
	
	public char getColor() {
		return color;
	}
	
}
