package gui;

import game.GameManager;
import board.Chessboard;
import board.Location;

import javax.imageio.ImageIO;
import javax.swing.*;

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
				buttonBoard[i][j].addActionListener(new ButtonListener(this, interpreter, i, j));
				
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
	 * Adds pawn images to buttons
	 */
	public void addPawns()
	{
		for(int i = 0; i < iconBoard.length; i++)
		{
			try
			{
				Image darkPawn = ImageIO.read(getClass().getResource("/assets/darkPawn.png"));
			    buttonBoard[1][i].setIcon(new ImageIcon(darkPawn));
			    
			    Image lightPawn = ImageIO.read(getClass().getResource("/assets/lightPawn.png"));
			    buttonBoard[6][i].setIcon(new ImageIcon(lightPawn));
			}
			catch(Exception e)
			{
				System.out.println("Can't find or read that image");
			}
		}
	}
	
	/*
	 * Adds the other piece images to the buttons
	 */
	public void addSpecialPieces()
	{
		try
		{
			//Rook
			Image darkRook = ImageIO.read(getClass().getResource("/assets/darkRook.png"));
		    buttonBoard[0][0].setIcon(new ImageIcon(darkRook));
		    buttonBoard[0][7].setIcon(new ImageIcon(darkRook));
		    
		    Image lightRook = ImageIO.read(getClass().getResource("/assets/lightRook.png"));
		    buttonBoard[7][0].setIcon(new ImageIcon(lightRook));
		    buttonBoard[7][7].setIcon(new ImageIcon(lightRook));
		    
		    //Knight
		    Image darkKnight = ImageIO.read(getClass().getResource("/assets/darkKnight.png"));
		    buttonBoard[0][1].setIcon(new ImageIcon(darkKnight));
		    buttonBoard[0][6].setIcon(new ImageIcon(darkKnight));
		    
		    Image lightKnight = ImageIO.read(getClass().getResource("/assets/lightKnight.png"));
		    buttonBoard[7][1].setIcon(new ImageIcon(lightKnight));
		    buttonBoard[7][6].setIcon(new ImageIcon(lightKnight));
		    
		    //Bishop
		    Image darkBishop = ImageIO.read(getClass().getResource("/assets/darkBishop.png"));
		    buttonBoard[0][2].setIcon(new ImageIcon(darkBishop));
		    buttonBoard[0][5].setIcon(new ImageIcon(darkBishop));
		    
		    Image lightBishop = ImageIO.read(getClass().getResource("/assets/lightBishop.png"));
		    buttonBoard[7][2].setIcon(new ImageIcon(lightBishop));
		    buttonBoard[7][5].setIcon(new ImageIcon(lightBishop));
		    
		    //Queen
		    Image darkQueen = ImageIO.read(getClass().getResource("/assets/darkQueen.png"));
		    buttonBoard[0][3].setIcon(new ImageIcon(darkQueen));
		    
		    Image lightQueen = ImageIO.read(getClass().getResource("/assets/lightQueen.png"));
		    buttonBoard[7][3].setIcon(new ImageIcon(lightQueen));
		    
		    //King
		    Image darkKing = ImageIO.read(getClass().getResource("/assets/darkKing.png"));
		    buttonBoard[0][4].setIcon(new ImageIcon(darkKing));
		    
		    Image lightKing = ImageIO.read(getClass().getResource("/assets/lightKing.png"));
		    buttonBoard[7][4].setIcon(new ImageIcon(lightKing));
		}
		catch(Exception e)
		{
			System.out.println("Can't find or read that image");
		}
		
		//Add queen
		iconBoard[0][3] = new ImageIcon("/assets/darkQueen.png");
		iconBoard[7][3] = new ImageIcon("/assets/lightQueen.png");
		
		//Add king
		iconBoard[0][4] = new ImageIcon("/assets/darkKing.png");
		iconBoard[7][4] = new ImageIcon("/assets/lightKing.png");
	}
	
	/*
	 * Creates the gui
	 */
	public static void main(String[] args)
	{
		ChessGameWindow gui;
		
		if(args != null)
		{
			gui = new ChessGameWindow(args[0]);
			gui.createWindowComponents(gui.getContentPane());
			gui.addPawns();
			gui.addSpecialPieces();
			gui.pack();
		}
		else
		{
			System.out.println("No filename provided; provide command line arguments");
		}
	}
}

/*
 * Class that listens for events from the buttons on the gui
 */
class ButtonListener implements ActionListener
{
	ChessGameWindow window;
	GuiInterpreter interpreter;
	Color defaultBackground;
	int col;
	int row;
	
	public ButtonListener(ChessGameWindow window, GuiInterpreter interpreter, int col, int row)
	{
		this.window = window;
		this.interpreter = interpreter;
		this.col = col;
		this.row = row;
	}
	
	public void setBackgroundColor(Color c)
	{
		defaultBackground = c;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//interpreter gets coordinates of button clicked
		//gets available moves for that piece
		//displays it on screen, repaint
		//user clicks end location
		//move the piece
		
		
		//Orange is currently selected piece
		//Pink is available moves
		Color oldBackground = this.defaultBackground;
		Color orange = Color.ORANGE;
		Color pink = Color.PINK;
		
		if(window.getPanel().getBackground() != orange)
		{
			if(interpreter.getChessboard().getSquares()[col][row].getPiece() != null)
			{
				System.out.println("Button column: " + this.col);
				System.out.println("Button row: " + this.row);
				
				//switch row and col to see if it works
				ArrayList<Location> possibleMoves = interpreter.getAvailableMoves(new Location(this.col, this.row));
				System.out.println("Available moves: \n");
				if(!possibleMoves.isEmpty())
				{
					window.getPanel().add(window.getButtonBoard()[col][row]).setBackground(orange);
					
					for(Location l : possibleMoves)
					{
						System.out.println(l.toString());
						window.getButtonBoard()[l.getRow()][l.getColumn()].setBackground(pink);
					}
				}
				else
				{
					window.getButtonBoard()[this.row][this.col].setBackground(oldBackground);
				}
			}
			//interpreter.getAvailableMoves(new Location(col, row));
		}
		else
		{
			System.out.println("lol");
		}
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
