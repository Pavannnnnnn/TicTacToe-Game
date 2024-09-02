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
		HumanPlayer p2=new HumanPlayer("pop", '0');
		
		HumanPlayer cp;
		
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
