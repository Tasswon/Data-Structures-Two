/** Author: Joseph Tassone
 *  Description: Takes in a jumbo sudoku puzzle as a file, solves the puzzle
 *  through backtracking, and saves the solved puzzle to a file.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Sudoku {
	public static void main (String[] args) throws ClassNotFoundException, IOException {
		
		//Reads the puzzle in, and saves it to a 2d array
		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("C:/Users/joeta/Desktop/puzzle1.dat"));) {
			int[][] puzzle = (int[][])(input.readObject());
			
			printPuzzle(puzzle);
			System.out.println();
			solve(puzzle);
			System.out.println();
			printPuzzle(puzzle);
			
			//Saves the solved puzzle to a new dat file
			try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("C:/Users/joeta/Desktop/puzzleUpdated.dat"))) {
				output.writeObject(puzzle);
			}
		}
	}
	
	//Method progresses through the puzzle row by row
	//It tests if a value is valid at a particular spot and inserts it
	//If the method ends if there is no solution or all the spaces are filled
	public static boolean solve(int[][] puzzle) {
		
		for(int i = 0; i < puzzle.length; i++) {
			for(int j = 0; j < puzzle[i].length; j++) {
				if(puzzle[i][j] == 0) { 
					for(int val = 1; val <= 16; val++) {
						if(verify(puzzle, i, j, val)) {
							puzzle[i][j] = val;
							if(solve(puzzle)) {
								return true;
							}
							puzzle[i][j] = 0;
						}	
					}
					//Backtracks if the solution at this point is invalid
					return false;
				}
			}
		}
		return true;
	}
	
	//The method verifies if a value is valid at a particular point in the puzzle
	//Test row, columns, and squares
	private static boolean verify(int[][] puzzle, int row, int col, int val) {
		
		//Saves the corner value of the current square
		int currRow = row / 4 * 4;
		int currCol = col / 4 * 4;
		
		//Tests whether the value at the current square is valid
		for(int i = currRow; i < currRow + 4; i++) {
			for (int j = currCol; j < currCol + 4; j++) {
				if (puzzle[i][j] == val) {
						return false;
				}
			}
		}
		
		//Tests whether the value at the current row is valid
		for(int i = 0; i < 16; i++) {
			if (puzzle[i][col] == val) {
				return false;
			}
		}
		
		//Tests whether the value at the current column is valid
		for(int i = 0; i < 16; i++) {
			if (puzzle[row][i] == val) {
				return false;
			}
		}
		//If all tests prove true it returns true
		return true;
	}
	
	//The method prints out the puzzle in it's proper configuration
	public static void printPuzzle(int[][] puzzle) {
		
		for(int row = 0; row < puzzle.length; row++) {
			System.out.print("[ ");
			for(int column = 0; column < puzzle[row].length; column++) {
				if(column == 4 || column == 8 || column == 12) {
					System.out.print("| ");
				}
				//Converts values over 9 to letters as per the rules of the jumbo sudoku puzzle
				if(puzzle[row][column] > 9 && puzzle[row][column] < 16) {
					System.out.print(Integer.toHexString(puzzle[row][column]).toUpperCase() + " ");
				}
				else if(puzzle[row][column] > 9 && puzzle[row][column] == 16) {
					System.out.print("G" + " ");
				}
				else if(puzzle[row][column] == 0) {
					System.out.print(". ");
				}
				else {
					System.out.print(puzzle[row][column] + " ");
				}
			}
			System.out.println("]");
			if(row == 3 || row == 7 || row == 11) {
				System.out.println("-----------------------------------------");
			}
		}
	}
}

