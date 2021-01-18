package minesweeper;

// just to print out a board that appears the right size for the first turn

public class Cover {
	
	private int rows,cols;

	
	
	public Cover(int rows,int cols) {
		this.rows = rows;
		this.cols = cols;

	}
	
	public void printCover() {
		
		for(int c = 1; c <= this.cols; c++) {
			System.out.print("\t" + c);
		}
		System.out.print("\n\t");
		for(int c = 1; c<= this.cols; c++) {
			System.out.print("__\t");
		}
		
		for (int r = 0; r < this.rows; r++) {
			System.out.print("\n\n");
			System.out.print( (r+1) + "|\t");
			
			for (int c = 0; c < this.cols; c++) {
				
				System.out.print('#' + "\t");		
			}
		}
		System.out.println("\n\n");
	}
	
	
	

}
