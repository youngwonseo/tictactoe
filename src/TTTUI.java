import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TTTUI extends JFrame {
	private final TTTController TC;

	private final int WINDOW_WIDTH = 300;
	private final int WINDOW_HEIGHT = 300;

	// size of game board
	private final int BOARD_HEIGHT = 250;
	private final int BOARD_WIDTH = 250;

	private final int DESKTOP_WIDTH;
	private final int DESKTOP_HEIGHT;

	private final int ROWS = 3;
	private final int COLS = 3;

	private final int BORDER_SPACE = 15;

	private final int CELL_WIDTH = BOARD_HEIGHT / COLS;
	private final int CELL_HEIGHT = BOARD_HEIGHT / ROWS;
	
	
	public TTTUI(TTTController tc){
		this.TC = tc;

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		DESKTOP_WIDTH = (int) screenSize.getWidth();
		DESKTOP_HEIGHT = (int) screenSize.getHeight();
		
		setLocation(DESKTOP_WIDTH/2 - WINDOW_WIDTH/2,DESKTOP_HEIGHT/2 - WINDOW_HEIGHT/2);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setLayout(new BorderLayout());
		add(new GameBoardUI(), "Center");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	
	}
	
	
	

	
	
	class GameBoardUI extends JPanel {

		public GameBoardUI() {

			this.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e) {
					
					
					if (TC.isMyTurn()) {
						
						// board is from 0,0 to 9,9
						int x = (e.getX() / CELL_WIDTH);
						int y = (e.getY() / CELL_HEIGHT);

						
						if (x >= 0 && y >= 0 && x < COLS && y < ROWS) {
							TC.doTurn(true, x, y);
							TC.doAITurn();
						
							repaint();
						}
					}
				}

				public void mouseExited(MouseEvent e) {
				}

				public void mouseEntered(MouseEvent e) {
				}

				public void mousePressed(MouseEvent e) {
				}

				public void mouseReleased(MouseEvent e) {
				}
			});

			

		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);

			
			

			this.setBackground(new Color(204, 102, 0, 255));
			g.setColor(Color.BLACK);

			// g.drawString(postString, POST_X, POST_Y);

			for (int i = 0; i <= ROWS; i++) {
				g.drawLine(BORDER_SPACE, BORDER_SPACE + i
						* (BOARD_HEIGHT / ROWS), BORDER_SPACE + BOARD_WIDTH,
						BORDER_SPACE + i * (BOARD_HEIGHT / COLS));

				g.drawLine(BORDER_SPACE + i * (BOARD_WIDTH / ROWS),
						BORDER_SPACE, BORDER_SPACE + i * (BOARD_WIDTH / COLS),
						BORDER_SPACE + BOARD_HEIGHT);
			}

			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLS; j++) {
					if (TC.getXYInfo(j, i) == 1) {
						g.setColor(Color.BLACK);
						g.fillOval(j * CELL_WIDTH + BORDER_SPACE, i
								* CELL_HEIGHT + BORDER_SPACE, CELL_WIDTH,
								CELL_HEIGHT);
					} else if (TC.getXYInfo(j, i) == 2) {
						g.setColor(Color.WHITE);
						g.fillOval(j * CELL_WIDTH + BORDER_SPACE, i
								* CELL_HEIGHT + BORDER_SPACE, CELL_WIDTH,
								CELL_HEIGHT);
					}
				}
			}
		}
	}

}
