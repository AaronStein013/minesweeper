package minesweeper;

public class Board {

	
	private final int rows,cols,numBombs;
	private int spacesExplored;
	public Number[][] board;
	
	
	public Board(int rows,int cols,int numBombs){
		this.rows = rows;
		this.cols = cols;
		this.numBombs = numBombs;
		this.board = new Number[this.rows][this.cols];
		this.spacesExplored = 0;
		
		for(int i = 0; i < rows; i++){						// initialize the array of Numbers
			for(int j = 0; j < cols; j++) {
				board[i][j] = new Number(i,j);
				if(i == 0) {
					board[i][j].isTop = true;
				}
				if(i == rows - 1) {
					board[i][j].isBottom = true;
				}
				if(j == 0) {
					board[i][j].isLeft = true;
				}
				if(j == cols - 1) {
					board[i][j].isRight = true;
				}
			}
		}
		
	}													
	
	
	// place the bombs and set numbers surrounding bombs
	public void generateBoard(int firstRow,int firstCol) {
		
		firstRow--;
		firstCol--;
		
		// first search will ALWAYS be a 0
		board[firstRow][firstCol].setValue(0);
		
		
		
		// placing the bombs
		// want a number between 0 and rows/cols - 1
		int p = 0;
		int q = 0;
		
		for(int i = 0; i < numBombs; i++) {
			
			p = (int)(Math.random() * rows);
			q = (int)(Math.random() * cols);
			
			while(board[p][q].isBomb || (p==firstRow&&q==firstCol) || (p==firstRow-1 && q==firstCol) ||
					(p==firstRow-1&&q==firstCol+1)||(p==firstRow&&q==firstCol+1)||(p==firstRow+1&&q==firstCol+1)||
					(p==firstRow+1&&q==firstCol)||(p==firstRow+1&&q==firstCol-1)||(p==firstRow&&q==firstCol-1)||
					(p==firstRow-1&&q==firstCol-1)) {
				
				p = (int)(Math.random() * rows);
				q = (int)(Math.random() * cols);
			}
			board[p][q].setBomb();
		}
		
		
		// now calculate numbers around the bombs
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if(board[r][c].isBomb) {
					
					// starts at noon, goes clockwise around
					if(!board[r][c].isTop) 
						board[r-1][c].value++;			// up
					if(!board[r][c].isTop && !board[r][c].isRight) 
						board[r-1][c+1].value++;		// up-right
					if(!board[r][c].isRight) 
						board[r][c+1].value++;			// right
					if(!board[r][c].isRight && !board[r][c].isBottom) 
						board[r+1][c+1].value++;		// down-right
					if(!board[r][c].isBottom) 
						board[r+1][c].value++;			// down
					if(!board[r][c].isBottom && !board[r][c].isLeft) 
						board[r+1][c-1].value++;		// down-left
					if(!board[r][c].isLeft) 
						board[r][c-1].value++;			// left
					if(!board[r][c].isLeft && !board[r][c].isTop)
						board[r-1][c-1].value++;		// up-left
				}
			}
		}	
		
		reveal(firstRow,firstCol);
	}
	
	
	// given an x-coordinate,y-coordinate,and new valid number -- update the board
	public boolean updateBoard(int command, int r, int c) {
		r--;
		c--;
		
		if(command == 1) {
			if(board[r][c].isBomb) {
				System.out.println("You lose! Bomb at: ("+(r+1)+","+(c+1)+")");
				return true;
			}
			else if(board[r][c].value == 0){
				reveal(r,c);
				return false;
			}
			else {
				board[r][c].isRevealed = true;
				this.spacesExplored++;
				return false;
			}
		}
		
		
		else if(command == 2) {
			if(!board[r][c].isFlag) {
				board[r][c].isFlag = true;
			}
			else {
				board[r][c].isFlag = false;
			}
			
			return false;
		}
		else if(command == 3) {
			return true;
		}
		else {
			System.out.println("Invalid command!");
			return false;
		}
		
		
	}
	
	public void revealAll() {
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < cols; j++) {
				if(board[i][j].isRevealed == false) {
					board[i][j].isRevealed = true;
					this.spacesExplored++;
				}
			}
		}
	}
	
	
	// recursive function to clear all 0 spaces if one is hit
	public void reveal(int r,int c) {
		board[r][c].isRevealed = true;
		this.spacesExplored++;
		
		if(board[r][c].value == 0) {
			
			if(!board[r][c].isTop && !board[r-1][c].isRevealed) 
				reveal(r-1,c);			// up
			if(!board[r][c].isTop && !board[r][c].isRight && !board[r-1][c+1].isRevealed) 
				reveal(r-1,c+1);		// up-right
			if(!board[r][c].isRight && !board[r][c+1].isRevealed) 
				reveal(r,c+1);			// right
			if(!board[r][c].isRight && !board[r][c].isBottom && !board[r+1][c+1].isRevealed) 
				reveal(r+1,c+1);		// down-right
			if(!board[r][c].isBottom && !board[r+1][c].isRevealed) 
				reveal(r+1,c);			// down
			if(!board[r][c].isBottom && !board[r][c].isLeft && !board[r+1][c-1].isRevealed) 
				reveal(r+1,c-1);		// down-left
			if(!board[r][c].isLeft && !board[r][c-1].isRevealed) 
				reveal(r,c-1);			// left
			if(!board[r][c].isLeft && !board[r][c].isTop && !board[r-1][c-1].isRevealed)
				reveal(r-1,c-1);		// up-left
			
		}
		
	}
	
	
	
	// draw the board in console nicely
	public void printBoard() {
		
		for(int c = 1; c <= this.cols; c++) {
			System.out.print("\t" + c);
		}
		System.out.print("\n\t");
		for(int c = 0; c < this.cols; c++) {
			System.out.print("__\t");
		}
		
		for (int r = 0; r < this.rows; r++) {
			System.out.print("\n\n");
			System.out.print( (r+1) + "|\t");
			
			for (int c = 0; c < this.cols; c++) {
				
				board[r][c].printNumber();
				System.out.print("\t");
				
			}
		}
		System.out.println("\n\n");
	}
	
	
	public boolean didWin() {
		if((rows*cols) - (this.spacesExplored + this.numBombs) == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
}
