/*TicTacToe Class:

Manages the 3x3 board, initializes it with empty spaces, and provides methods to display the board and place marks.
Win Conditions:
colWin(), rowWin(), and diagWin() check for a win by columns, rows, or diagonals, respectively.
Draw Condition:
isDraw() checks if the board is full and no player has won, indicating a draw.
Player Class (Abstract):

Represents a player with a name and a mark ('X' or 'O').
Defines an abstract method makeMove() for making a move.
isValid(int row, int col) checks if a move is within bounds and on an empty cell.
HumanPlayer Class:

Extends Player and allows a human player to input their move via the console.
Repeatedly prompts the user for a valid row and column, then places the mark on the board.
AiPlayer Class:

Extends Player and generates random valid moves for the AI.
Continuously generates random positions until a valid move is found and places the mark on the board.
Launch Class (Main Class):

The main game loop alternates turns between two human players (p1 and p2), displaying the board after each move.
After each move, the game checks for a win or draw using the TicTacToe methods.
If a player wins or the game ends in a draw, the loop breaks and the result is printed.
*/


   /*
    Code Start from here
    */



package TicTacToeGAMEE;
import java.util.Random;
import java.util.Scanner;

class TicTacToe
{
	static char board[][];
	
	public TicTacToe()
	{
		board=new char[3][3];
		initBoard();
	}
	
	
	void initBoard()
	{
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				board[i][j]=' ';
			}
		}
	}
	
	
	
	static void display()
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
	
	
	static void placeMark(int row, int col, char mark)
	{
		if(row>=0 && row<=2 && col>=0 && col<=2)
		{
			board[row][col]=mark;
		}
		else
		{
			System.out.println("Not valid");
		}
	}
	
	
	static boolean colWin()
	{
		for(int j=0;j<=2;j++)
		{
			if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j])
			{
				return true;
			}
		}
		return false;
	}
	
	
	static boolean rowWin()
	{
		for(int i=0;i<=2;i++)
		{
			if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2])
			{
				return true;
			}
		}
		return false;
	}
	
	
	static boolean diagWin()
	{
		if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2]
				||board[1][1]!=' ' && board[0][2]==board[1][1] && board[1][1]==board[2][0])
		{
			return true;
		}
		return false;
	}
	
	
	static boolean isDraw()
	{
		for(int i=0;i<=2;i++)
		{
			for(int j=0;j<=2;j++)
			{
				if(board[i][j]==' ')
				{
					return false;
				}
			}
		}
		return true;
	}
}
abstract class Player
{
	String name;
	char mark;
	
	abstract void makeMove();
	
	boolean isValid(int row, int col)
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
class HumanPlayer extends Player
{
	public HumanPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	@Override
	void makeMove()
	{
		Scanner sc = new Scanner(System.in);
		int row=0;
		int col=0;
		do {
			row=sc.nextInt();
			col=sc.nextInt();
		} while (!isValid(row,col));
		
		TicTacToe.placeMark(row, col, mark);
	}
}
class AiPlayer extends Player
{
	public AiPlayer(String name, char mark)
	{
		this.name=name;
		this.mark=mark;
	}
	@Override
	void makeMove()
	{
		Scanner sc = new Scanner(System.in);
		int row=0;
		int col=0;
		do {
			Random r=new Random();
			row=r.nextInt(3);
			col=r.nextInt(3);
			
		} while (!isValid(row, col));
		
		TicTacToe.placeMark(row, col, mark);
	}
}
public class Launch {

	public static void main(String[] args) {
		TicTacToe t=new TicTacToe();
		
		HumanPlayer p1=new HumanPlayer("bob", 'X');
		AiPlayer p2=new AiPlayer("Ai", '0');
		
		Player cp;
		
		cp=p1;
		while(true)
		{
			System.out.println(cp.name+" Turn");
		cp.makeMove();
		TicTacToe.display();
		if(TicTacToe.colWin() || TicTacToe.rowWin() || TicTacToe.diagWin())
		{
			System.out.println(cp.name + " Wins");
			break;
		}
		else if(TicTacToe.isDraw())
		{
			System.out.println("Game is Draw");
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
