Developed a command-line Tic-Tac-Toe game in Java, featuring both human and AI players. Implemented game logic for move validation, win/draw conditions, and board management. Utilized object-oriented principles to design a flexible structure with abstract and concrete player classes. Added basic AI functionality using random move generation.


package TicTacToeGame;

import java.util.Random;
import java.util.Scanner;


//This class handles the game board, checks for win conditions, and manages the game state.
/* @Static Variable:

char[][] board: A 3x3 character array representing the Tic-Tac-Toe board.  */
/*@Constructor:

TicTacToe(): Initializes the board array and calls the initBoard() method to set up the board. */

/* -> code start form here<- */

class TicTacToe
{
	static char[][] board;

public TicTacToe()
{
	 board=new char[3][3];
	initBoard();
}





/* @void initBoard():
Initializes the 3x3 board with spaces ' ' indicating that the cells are empty.
*//* -> code start form here<- */
void initBoard()
{
	for(int i=0;i<board.length;i++)
	{
		for(int j=0;j<board[i].length;j++)
		{
			board[i][j] = ' '; 
		}
	}
}





/* @static void displayBoard():
Prints the current state of the board in a readable format with row and column separators.*/
/* -> code start form here<- */

static void displayBoard()
{
	System.out.println("-------------");
	for(int i=0;i<board.length;i++)
	{
		System.out.print("| ");
		for(int j=0;j<board[i].length;j++)
		{
			System.out.print(board[i][j]+ " | ");
		}
		System.out.println();
		System.out.println("-------------");
	}
}



/* static void placeMark(int row, int col, char mark):
Places a mark ('X' or 'O') on the board at the specified row and column if the position is valid.*/
/* -> code start form here<- */


static void placeMark(int row , int col, char mark)
{
	if(row>=0 && row <=2 && col>=0 && col<=2)
	{
		board[row][col]=mark;
	}
	else
	{
		System.err.println("Invalid Position");
	}
}





/* @static boolean colWin():
Checks if any of the columns have the same mark ('X' or 'O') across all three rows, indicating a win.*/
/* -> code start form here<- */


static boolean colWin()
{
	for(int j=0;j<=2;j++)
	{
		if(board[0][j]!=' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j])
		{
			return true;
		}
	}
	return false;
}



/* @static boolean rowWin():
Checks if any of the rows have the same mark ('X' or 'O') across all three columns, indicating a win.*/
/* -> code start form here<- */

static boolean rowWin()
{
	for(int i=0;i<=2;i++)
	{
		if(board[i][0]!=' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2])
		{
			return true;
		}
	}
	return false;
}




/* @static boolean diagionalWin():
Checks if either of the diagonals have the same mark ('X' or 'O'), indicating a win.*/
/* -> code start form here<- */


static boolean diagionalWin()
{
	if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1] == board[2][2]
			|| board[2][0]!=' ' && board[2][0] == board[1][1] && board[1][1]==board[0][2])
	{
		return true;
	}
	return false;
	
}



/* @static boolean checkDraw():
Checks if the board is full (no empty spaces left) without any player winning, indicating a draw.*/
/* -> code start form here<- */


static boolean checkDraw()
{
	for(int i=0;i<=2;i++)
	{
		for(int j=0;j<=2;j++)
		{
			if(board[i][j]== ' ')
			{
				return false;
			}
		}
	}
	return true;
}
}



/* @Player Class (Abstract)
This is an abstract class that serves as a blueprint for both human and AI players. It defines common attributes and methods that all player types will share.*/
/* @Attributes:

String name: The player's name.
char mark: The mark ('X' or 'O') that the player uses on the board.*/
/* @Abstract Method:

abstract void makeMove():
An abstract method that forces subclasses to implement their own version of making a move.
 */
/* @boolean isValidMove(int row, int col):
Checks if the selected row and column are within the valid range and if the cell at that position is empty.*/	
/* -> code start form here<- */

abstract class Player
{
	String name;
	char mark;
	
	abstract void makeMove();
	
	
	boolean isValidMove(int row, int col)
	{
		if(row>=0 && row<=2 && col>=0 && col<=2)
		{
			if(TicTacToe.board[row][col]==' ')
			{
				return true;
			}
		}
		return false;
	}
}





/*HumanPlayer Class
This class extends Player and represents a human player. It implements the makeMove() method, allowing a human to input their move.

Constructor:

HumanPlayer(String name, char mark):
Initializes the name and mark attributes for the human player.
Method:

void makeMove():
Prompts the user to enter the row and column for their move.
Continues prompting until a valid move is made.
Calls TicTacToe.placeMark() to place the mark on the board.*/
/* -> code start form here<- */

class HumanPlayer extends Player
{
	
	public HumanPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	void makeMove()
	{
		Scanner sc = new Scanner(System.in);
		int row=0;
		int col=0;
		do 
		{
			System.err.println(name + " Enter the row and colunm ");
			row=sc.nextInt();
			col=sc.nextInt();
		}
		while(!isValidMove(row, col));
		
		TicTacToe.placeMark(row, col, mark);
		
	}
}







/*AiPlayer Class
This class extends Player and represents an AI player. It implements the makeMove() method by generating random moves.

Constructor:

AiPlayer(String name, char mark):
Initializes the name and mark attributes for the AI player.
Method:

void makeMove():
Generates random row and column values using the Random class until a valid move is found.
Calls TicTacToe.placeMark() to place the mark on the board.*/
/* -> code start form here<- */

class AiPlayer extends Player
{
	
	public AiPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	void makeMove()
	{
		Scanner sc = new Scanner(System.in);
		int row=0;
		int col=0;
		do 
		{
			System.err.println(name + " Enter the row and colunm");
			Random r=new Random();
			row=r.nextInt(3);
			col=r.nextInt(3);
		}
		while(!isValidMove(row, col));
		
		TicTacToe.placeMark(row, col, mark);
	}
}






/* @TicTacToeLunch Class (Main Class)
This is the main class where the game is executed. It contains the main method and the game loop.

Main Method:
public static void main(String[] args):
Creates an instance of the TicTacToe game.
Creates a HumanPlayer (e.g., "Bob") with the mark 'X' and an AiPlayer with the mark 'O'.
Sets the current player (cp) to the human player (p1).
Enters a game loop:
The current player (cp) makes a move.
The board is displayed.
The game checks if there's a winner using rowWin(), colWin(), or diagionalWin(). If so, the loop breaks and the winner is announced.
If there's no winner, the game checks for a draw using checkDraw(). If the board is full and there's no winner, the game announces a draw and breaks the loop.
If neither a win nor a draw occurs, the current player is switched (alternating between p1 and p2).*/

/* -> code start form here<- */


public class TicTacToeLunch {

	public static void main(String[] args) {

		TicTacToe t=new TicTacToe();
		HumanPlayer p1=new HumanPlayer("Bob", 'X');
		AiPlayer p2=new AiPlayer("Ai", '0');
		
		Player cp;
		
		cp=p1;
	while(true)
	{
		System.out.println(cp.name+" Turn");
		cp.makeMove();
		TicTacToe.displayBoard();
		if(TicTacToe.rowWin() || TicTacToe.colWin() || TicTacToe.diagionalWin())
		{
			System.err.println(cp.name + " WIns");
			break;
		}
		else if(TicTacToe.checkDraw())
		{
			System.err.println("Game Draw");
			break;
		}
		else
		{
			if(cp==p1)
			{
				cp=p2;
			}
			else
			{
				cp=p1;
			}
		}
	}	
	
}
}
