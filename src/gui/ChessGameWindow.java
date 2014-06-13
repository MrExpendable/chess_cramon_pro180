package gui;

import board.Location;

import javax.imageio.ImageIO;
import javax.swing.*;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChessGameWindow extends JFrame
{
	ImageIcon[][] iconBoard = new ImageIcon[BOARD_WIDTH][BOARD_LENGTH];
	private JButton[][] buttonBoard = new JButton[BOARD_WIDTH][BOARD_LENGTH];
	JPanel chessboardPanel;
	GuiInterpreter interpreter;
	private static final int BOARD_LENGTH = 8;
	private static final int BOARD_WIDTH = 8;
	public static int buttonClickCount = 0;
	
	/*
	 * Creates window and sets size
	 */
	public ChessGameWindow(String cmdArgs)
	{
		super("Chess");
		setPreferredSize(new Dimension(1024,768));
		interpreter = new GuiInterpreter(cmdArgs);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*
	 * Creates the components of the gui
	 */
	public void createWindowComponents(final Container con)
	{
		chessboardPanel = new JPanel();
		chessboardPanel.setLayout(new GridLayout(8,8));
		
		Color a = Color.LIGHT_GRAY;
		Color b = Color.DARK_GRAY;
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_WIDTH; j++)
			{		
				buttonBoard[i][j] = new JButton();
				Piece p = interpreter.getChessboard().getSquares()[i][j].getPiece();
				if(p != null)
				{
					buttonBoard[i][j].addActionListener(new ButtonListener(this, interpreter, i, j, p));
				}
				else
				{
					buttonBoard[i][j].addActionListener(new ButtonListener(this, interpreter, i, j, null));
				}
				
				if((i % 2 == 0) && (j % 2 == 1))
				{
					chessboardPanel.add(buttonBoard[i][j]).setBackground(a);
				}
				else if((i % 2 == 1) && (j % 2 == 0))
				{
					chessboardPanel.add(buttonBoard[i][j]).setBackground(a);
				}
				else
				{
					chessboardPanel.add(buttonBoard[i][j]).setBackground(b);
				}
			}
		}
		
		con.add(chessboardPanel);
	}
	
	public void resetComponents()
	{
		this.remove(chessboardPanel);
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new GridLayout(8,8));
		
		Color a = Color.LIGHT_GRAY;
		Color b = Color.DARK_GRAY;
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_WIDTH; j++)
			{		
				buttonBoard[i][j] = new JButton();
				Piece p = interpreter.getChessboard().getSquares()[i][j].getPiece();
				if(p != null)
				{
					buttonBoard[i][j].addActionListener(new ButtonListener(this, interpreter, i, j, p));
				}
				else
				{
					buttonBoard[i][j].addActionListener(new ButtonListener(this, interpreter, i, j, null));
				}
				
				if((i % 2 == 0) && (j % 2 == 1))
				{
					newPanel.add(buttonBoard[i][j]).setBackground(a);
				}
				else if((i % 2 == 1) && (j % 2 == 0))
				{
					newPanel.add(buttonBoard[i][j]).setBackground(a);
				}
				else
				{
					newPanel.add(buttonBoard[i][j]).setBackground(b);
				}
			}
		}
		
		chessboardPanel = newPanel;
		this.add(chessboardPanel);
	}
	
	/*
	 * Get the panel
	 */
	public JPanel getPanel()
	{
		return chessboardPanel;
	}
	
	/*
	 * Gets the 2d array of buttons
	 */
	public JButton[][] getButtonBoard()
	{
		return buttonBoard;
	}
	
	/*
	 * Adds images to board depending on piece locations
	 */
	public void addPieces()
	{
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_LENGTH; j++)
			{
				Piece p = interpreter.getChessboard().getSquares()[i][j].getPiece();
				try
				{
					if(!p.isWhite)
					{
						if(p instanceof Pawn)
						{
							Image darkPawn = ImageIO.read(getClass().getResource("/assets/darkPawn.png"));
							buttonBoard[i][j].setIcon(new ImageIcon(darkPawn));
						}
						else if(p instanceof Knight)
						{
							Image darkKnight = ImageIO.read(getClass().getResource("/assets/darkKnight.png"));
							buttonBoard[i][j].setIcon(new ImageIcon(darkKnight));
						}
						else if(p instanceof Rook)
						{
							Image darkRook = ImageIO.read(getClass().getResource("/assets/darkRook.png"));
							buttonBoard[i][j].setIcon(new ImageIcon(darkRook));
						}
						else if(p instanceof Bishop)
						{
							Image darkBishop = ImageIO.read(getClass().getResource("/assets/darkBishop.png"));
							buttonBoard[i][j].setIcon(new ImageIcon(darkBishop));
						}
						else if(p instanceof Queen)
						{
							Image darkQueen = ImageIO.read(getClass().getResource("/assets/darkQueen.png"));
							buttonBoard[i][j].setIcon(new ImageIcon(darkQueen));
						}
						else if(p instanceof King)
						{
							Image darkKing = ImageIO.read(getClass().getResource("/assets/darkKing.png"));
							buttonBoard[i][j].setIcon(new ImageIcon(darkKing));
						}
					}
					else
					{
						if(p instanceof Pawn)
						{
							Image lightPawn = ImageIO.read(getClass().getResource("/assets/lightPawn.png"));
							buttonBoard[i][j].setIcon(new ImageIcon(lightPawn));
						}
						else if(p instanceof Knight)
						{
							Image lightKnight = ImageIO.read(getClass().getResource("/assets/lightKnight.png"));
							buttonBoard[i][j].setIcon(new ImageIcon(lightKnight));
						}
						else if(p instanceof Rook)
						{
							Image lightRook = ImageIO.read(getClass().getResource("/assets/lightRook.png"));
							buttonBoard[i][j].setIcon(new ImageIcon(lightRook));
						}
						else if(p instanceof Bishop)
						{
							Image lightBishop = ImageIO.read(getClass().getResource("/assets/lightBishop.png"));
							buttonBoard[i][j].setIcon(new ImageIcon(lightBishop));
						}
						else if(p instanceof Queen)
						{
							Image lightQueen = ImageIO.read(getClass().getResource("/assets/lightQueen.png"));
							buttonBoard[i][j].setIcon(new ImageIcon(lightQueen));
						}
						else if(p instanceof King)
						{
							Image lightKing = ImageIO.read(getClass().getResource("/assets/lightKing.png"));
							buttonBoard[i][j].setIcon(new ImageIcon(lightKing));
						}
					}
				}
				catch(Exception e)
				{
					//keep this for now.. do something with this later
				}
			}
		}
	}
	
	public void updateGameState()
	{
		//update the gui, check for endgame
		updateGUI();
		interpreter.changePlayerTurn();
		if(interpreter.detectEndgame())
		{
			this.dispose();
		}
	}
	
	public void resetButtonBackgrounds()
	{
		Color a = Color.LIGHT_GRAY;
		Color b = Color.DARK_GRAY;
		
		for(int i = 0; i < BOARD_LENGTH; i++)
		{
			for(int j = 0; j < BOARD_WIDTH; j++)
			{
				if((i % 2 == 0) && (j % 2 == 1))
				{
					buttonBoard[i][j].setBackground(a);
				}
				else if((i % 2 == 1) && (j % 2 == 0))
				{
					buttonBoard[i][j].setBackground(a);
				}
				else
				{
					buttonBoard[i][j].setBackground(b);
				}
			}
		}
	}
	
	public void updateGUI()
	{
		//doesn't do shit cause swing is a fucktruck
//		revalidate();
		//createWindowComponents(this.getContentPane());
		//addPieces();
		//revalidate();
		//this.remove(getContentPane());
		repaint();
		addPieces();
		repaint();
		resetButtonBackgrounds();
		repaint();
//		createWindowComponents(this.getContentPane());
//		this.remove(getContentPane());
//		this.createWindowComponents(getContentPane());
//		addPieces();
//		repaint();
	}
	
//	/*
//	 * Creates the gui
//	 */
//	public static void main(String[] args)
//	{
//		ChessGameWindow gui;
//		
//		if(args != null)
//		{
////			gui = new ChessGameWindow(args[0]);
////			gui.createWindowComponents(gui.getContentPane());
////			gui.addPieces();
//////			gui.addPawns();
//////			gui.addSpecialPieces();
////			gui.pack();
//			createGUI(args[0]);
//		}
//		else
//		{
//			System.out.println("No filename provided; provide command line arguments");
//		}
//	}
}

/*
 * Class that listens for events from the buttons on the gui
 */
class ButtonListener implements ActionListener
{
	ChessGameWindow window;
	GuiInterpreter interpreter;
	Color defaultBackground;
	Piece currentPiece;
	int col;
	int row;
	
	public ButtonListener(ChessGameWindow window, GuiInterpreter interpreter, int col, int row, Piece p)
	{
		this.window = window;
		this.interpreter = interpreter;
		this.col = col;
		this.row = row;
		currentPiece = p;
		//currentPiece = interpreter.getChessboard().getSquares()[this.col][this.row].getPiece();
	}
	
	public void setBackgroundColor(Color c)
	{
		defaultBackground = c;
	}
	
	public void setIndividualIcons(Piece p)
	{
		try
		{
			if(!p.isWhite)
			{
				if(p instanceof Pawn)
				{
					Image darkPawn = ImageIO.read(getClass().getResource("/assets/darkPawn.png"));
					window.getButtonBoard()[this.col][this.row].setIcon(new ImageIcon(darkPawn));
				}
				else if(p instanceof Knight)
				{
					Image darkKnight = ImageIO.read(getClass().getResource("/assets/darkKnight.png"));
					window.getButtonBoard()[this.col][this.row].setIcon(new ImageIcon(darkKnight));
				}
				else if(p instanceof Rook)
				{
					Image darkRook = ImageIO.read(getClass().getResource("/assets/darkRook.png"));
					window.getButtonBoard()[this.col][this.row].setIcon(new ImageIcon(darkRook));
				}
				else if(p instanceof Bishop)
				{
					Image darkBishop = ImageIO.read(getClass().getResource("/assets/darkBishop.png"));
					window.getButtonBoard()[this.col][this.row].setIcon(new ImageIcon(darkBishop));
				}
				else if(p instanceof Queen)
				{
					Image darkQueen = ImageIO.read(getClass().getResource("/assets/darkQueen.png"));
					window.getButtonBoard()[this.col][this.row].setIcon(new ImageIcon(darkQueen));
				}
				else if(p instanceof King)
				{
					Image darkKing = ImageIO.read(getClass().getResource("/assets/darkKing.png"));
					window.getButtonBoard()[this.col][this.row].setIcon(new ImageIcon(darkKing));
				}
			}
			else
			{
				if(p instanceof Pawn)
				{
					Image lightPawn = ImageIO.read(getClass().getResource("/assets/lightPawn.png"));
					window.getButtonBoard()[this.col][this.row].setIcon(new ImageIcon(lightPawn));
				}
				else if(p instanceof Knight)
				{
					Image lightKnight = ImageIO.read(getClass().getResource("/assets/lightKnight.png"));
					window.getButtonBoard()[this.col][this.row].setIcon(new ImageIcon(lightKnight));
				}
				else if(p instanceof Rook)
				{
					Image lightRook = ImageIO.read(getClass().getResource("/assets/lightRook.png"));
					window.getButtonBoard()[this.col][this.row].setIcon(new ImageIcon(lightRook));
				}
				else if(p instanceof Bishop)
				{
					Image lightBishop = ImageIO.read(getClass().getResource("/assets/lightBishop.png"));
					window.getButtonBoard()[this.col][this.row].setIcon(new ImageIcon(lightBishop));
				}
				else if(p instanceof Queen)
				{
					Image lightQueen = ImageIO.read(getClass().getResource("/assets/lightQueen.png"));
					window.getButtonBoard()[this.col][this.row].setIcon(new ImageIcon(lightQueen));
				}
				else if(p instanceof King)
				{
					Image lightKing = ImageIO.read(getClass().getResource("/assets/lightKing.png"));
					window.getButtonBoard()[this.col][this.row].setIcon(new ImageIcon(lightKing));
				}
			}
		}
		catch(Exception e)
		{
			//uncaught exception, need to fix this
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
		//Orange is currently selected piece
		//Pink is available moves
		Color orange = Color.ORANGE;
		Color pink = Color.PINK;
		Color squareColor = window.getButtonBoard()[this.col][this.row].getBackground();
		int r = squareColor.getRed();
		int b = squareColor.getBlue();
		int g = squareColor.getGreen();
		Color backgroundColor = new Color(r, b, g);
//		Piece p = interpreter.getChessboard().getSquares()[this.col][this.row].getPiece();
		
		//if the square hasn't been clicked on
		if(squareColor != orange)
		{
			if(squareColor == pink)
			{
				interpreter.incrementButtonClickCount();
				//if this is the second time a player clicked, count it as a move
				if(interpreter.getButtonClickCount() == 2)
				{
					System.out.println("Second time clicked, resetting");
					interpreter.resetButtonClickCount();
					interpreter.setMoveChoice(new Location(this.col, this.row));
					window.getButtonBoard()[this.col][this.row].setBackground(backgroundColor);
					
					//Make a move with these two inputs, then update the game state and the gui
					if(interpreter.makeMove())
					{
						//setIndividualIcons(currentPiece);
						window.resetComponents();
						window.updateGameState();
					}
				}
			}
			//If the piece in the space clicked is not null and the piece color matches the player's turn
			else if(currentPiece != null && (currentPiece.isWhite == interpreter.isPlayer1Turn))
			{
				System.out.println("Button column: " + this.col);
				System.out.println("Button row: " + this.row);
				
				//Get all possible moves for this piece, then highlight them in the window
				//switch row and col to see if it works
				ArrayList<Location> possibleMoves = interpreter.getAvailableMoves(new Location(this.col, this.row));
				System.out.println("Available moves: \n");
				
				//if there are any possible moves for a given piece
				if(!possibleMoves.isEmpty())
				{
					interpreter.incrementButtonClickCount();
					window.getPanel().add(window.getButtonBoard()[col][row]).setBackground(orange);
					
					for(Location l : possibleMoves)
					{
						System.out.println(l.toString());
						window.getPanel().add(window.getButtonBoard()[l.getRow()][l.getColumn()]).setBackground(pink);
					}
					
					//if the player clicked once, then count the first click as choosing a piece
					if(interpreter.getButtonClickCount() == 1)
					{
						System.out.println("First time clicked");
						interpreter.setPieceChoice(new Location(this.col, this.row));
					}
					else
					{
						interpreter.resetButtonClickCount();
						window.resetButtonBackgrounds();
					}
				}
				else
				{
					//window.getButtonBoard()[this.col][this.row].setBackground(backgroundColor);
				}
			}
			//interpreter.getAvailableMoves(new Location(col, row));
		}
		//if the square was already clicked on
		else
		{
			interpreter.resetButtonClickCount();
			window.resetButtonBackgrounds();
			//window.getPanel().add(window.getButtonBoard()[col][row]).setBackground(orange);
		}
		//JOptionPane.showMessageDialog(null, "Player 2 is checkmated. Player 1 wins!");
		//window.dispose();
		System.out.println("exiting out of actionlistener and updating");
	}
}

//public class ChessGameWindow extends JFrame
//{
//	private JTextField tfCount;  // Use Swing's JTextField instead of AWT's TextField
//	private int count = 0;
//	 
//	   /** Constructor to setup the GUI */
//	public ChessGameWindow() 
//	{
//		// Retrieve the content-pane of the top-level container JFrame
//	    // All operations done on the content-pane
//	    Container cp = getContentPane();
//	    cp.setLayout(new FlowLayout());
//	 
//	    cp.add(new JLabel("Counter"));
//	    tfCount = new JTextField("0", 10);
//	    tfCount.setEditable(false);
//	    cp.add(tfCount);
//	 
//	    JButton btnCount = new JButton("Count");
//	    cp.add(btnCount);
//	 
//	    // Allocate an anonymous instance of an anonymous inner class that
//	    //  implements ActionListener as ActionEvent listener
//	    btnCount.addActionListener(new ActionListener() 
//	    {
//	    	@Override
//	    	public void actionPerformed(ActionEvent e)
//	    	{
//		        ++count;
//		        tfCount.setText(count + "");
//	         }
//	      });
//	 
//	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
//	      setTitle("Chess");
//	      setSize(800, 800);
//	      setVisible(true);
//	}
//	   
//	public static void main(String[] args)
//	{
//		final String CMDARGS = args[0];
//		SwingUtilities.invokeLater(new Runnable() 
//		{
//		    @Override
//		    public void run() 
//		    {
//		       new ChessGameWindow();
//		       GameManager gm = new GameManager(CMDARGS);
//		    }
//		});
//	}
//}
