
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
* @author Nicolás Penagos Montoya
* nicolas.penagosm98@gmail.com
**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import myExceptions.DisabledThreadException;
import processing.core.PApplet;

public class Main extends PApplet {

	// -------------------------------------
	// Atributtes
	// -------------------------------------
	private Square[][] squares;
	private boolean firstClick;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("ui.Main");
	}

	// -------------------------------------
	// Processing Methods
	// -------------------------------------
	public void settings() {

		size(600, 400);

	}

	public void setup() {

		firstClick = true;
		squares = new Square[30][20];
		loadSquares();

	}

	public void draw() {

		background(255);

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[0].length; j++) {

				Square currentSq = squares[i][j];
				currentSq.toDraw();

			}
		}

	}

	// -------------------------------------
	// Methods
	// -------------------------------------
	public void loadSquares() {

		for (int i = 0; i < squares.length; i++) {

			boolean parity = (i % 2 == 0) ? true : false;

			for (int j = 0; j < squares[0].length; j++) {

				char color;

				if (parity) {
					if (j % 2 == 0) {
						color = Square.BLACK;
					} else {
						color = Square.WHITE;
					}
				} else {
					if (j % 2 == 0) {
						color = Square.WHITE;
					} else {
						color = Square.BLACK;
					}
				}

				squares[i][j] = new Square(i * 20, j * 20, parity, 20, color, this);
			}
		}
	}

	public void mousePressed() {

		if (firstClick) {
			loadThreads();
			firstClick = false;
		} else {
			try {
				stopSquares(mouseX, mouseY);
			} catch (DisabledThreadException e) {
				// TODO Auto-generated catch block
			
				System.out.println(e.getMessage());
			}
		}

	}

	public void loadThreads() {

		for (int i = 0; i < squares.length; i++) {
			for (int j = 0; j < squares[0].length; j++) {

				Square sq = squares[i][j];
				if (!sq.getParity() && j % 2 != 0) {
					Thread t = new Thread(sq);
					t.start();
				}

			}
		}
	}

	public void stopSquares(int mouseX, int mouseY) throws DisabledThreadException {

		for (int i = 1; i < squares.length; i += 2) {
			for (int j = 1; j < squares[0].length; j += 2) {

				Square sq = squares[i][j];
				int posX = sq.getPosX();
				int posY = sq.getPosY();

				if (posX <= mouseX && posX + 20 >= mouseX && posY <= mouseY && posY + 20 >= mouseY) {
					
					if (sq.getColor()==Square.RED) {
						throw new DisabledThreadException(i,j);
					}
					sq.setStop(true);
					sq.setColor(Square.RED);

				}

			}
		}
	}
}
