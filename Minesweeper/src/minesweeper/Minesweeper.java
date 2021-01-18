package minesweeper;

import java.util.Scanner;

/*
 * Minesweeper playable through Eclipse IDE Console
 * 
 * Purpose:
 *  - Practice with OOP 
 * 	- Practice thinking about larger programs
 * 	- Practice with version control on Github
 * 
 * Description:	
 * 	- In main() method, can alter size of board and number of bombs
 * 	- Follow command prompts in Console to play the game
 * 	- Bombs represented by "!"
 * 	- Numbers indicate how many bombs touch that particular space
 * 	- A "0" represents a space not touching any bombs
 * 	- Place flags as necessary to mark bombs
 * 	- Game is won when all non-bomb spaces have been explored
 * 
 * 
 * Next improvements: 	
 * - Add error handling so that only proper commands or valid indexes can be inputed
 * - Use logic and methods implemented here to implement interactive GUI that makes 
 * 	 the game more intuitive/user-friendly to play
 */

public class Minesweeper {
	
	public static int[] makeMove() {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		int command = 0;
		int r = 0;
		int c = 0;
		
		System.out.print("Enter 1 to search, 2 to place/remove marker, 3 to quit: ");
		if(input.hasNextInt()) {
			command = input.nextInt();
		}
		if(command == 3) {						// if quit command is inputed, return immediately
			return new int[] {3,1,1};
		}
		
		System.out.print("Enter row: ");
		if(input.hasNextInt()) {
			r = input.nextInt();
		}
		
		System.out.print("Enter column: ");
		if(input.hasNextInt()) {
			c = input.nextInt();
		}
		
		return new int[]{command,r,c};	
	}
	
	
	public static void main(String[] args) {		
		
		int numRows = 9;
		int numCols = 9;
		int numBombs = 10;
		
		boolean quit = false;
		
		Board b = new Board(numRows,numCols,numBombs);
		Cover c = new Cover(numRows,numCols);
		
		c.printCover();
		
		// first move:
		int[] move = makeMove();
		// generate board based on first move
		b.generateBoard(move[1],move[2]);
		
		if(move[0] == 3) {
			b.revealAll();
			quit = true;
		}
		
		for(int i = 0; i < 50; i++) System.out.println();
		
		b.printBoard();
		
		
		// main while loop
		while(!quit && !b.didWin()) {
			
			
			move = makeMove();
			
			quit = b.updateBoard(move[0],move[1],move[2]);
			if(!quit && !b.didWin()) b.printBoard();
		}
		
		b.revealAll();
		b.printBoard();
		if(b.didWin()) {
			System.out.println("You Win!!");
		}
		
		System.out.println("program ended successfully");

		
		
	}
	

}
