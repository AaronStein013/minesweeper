package minesweeper;

public class Number {
	
	
	public boolean isBomb,isFlag;
	public int value;
	public boolean isRevealed,isTop,isBottom,isLeft,isRight;
	private int row,col;
	
	
	
	public Number(int row,int col) {
		this.isBomb = this.isFlag = false;
		this.isRevealed = false;
		this.isTop = this.isBottom = this.isLeft = this.isRight = false;
		this.value = 0;
		this.row = row;
		this.col = col;
	}
	
	
	public void setBomb() {
		this.isBomb = true;
		this.value = -50;		// arbitrarily negative value
	}
	
	public void setValue(int val) {
		this.value = val;
	}
	
	public int getValue() {
		return this.value;
	}
	
	
	
	public void printNumber() {
		
		if(this.isFlag) {
			System.out.print('F');
		}
		else if(!this.isRevealed) {
			System.out.print('#');
		}
		else if(this.isBomb) {
			System.out.print('!');
		}
		else {
			System.out.print(this.value);
		}
	}
	
	
	public int getRow() {
		return this.row;
	}
	
	public int getCol() {
		return this.col;
	}
	
	
	
	

}
