/** Author: Joseph Tassone
 *  Description: Demonstrate the use of Prim’s algorithm to find the minimum cost
 *  as represented by an adjacency matrix.
 */

public class MinimumSpanningTreeMatrix {
	public static void main(String[] args) {
		
		//The 2d-array representing the matrix itself
		int[][] matrix = {{0, 2145, 3520, 1540, 2475, 2420, 3355, 2585},
						{2145, 0, 1980, 3795, 1815, 2585, 1375, 2530},
						{3520, 1980, 0, 2475, 3795, 3960, 3410, 2255},
						{1540, 3795, 2475, 0, 3575, 2695, 2475, 2035},
						{2475, 1815, 3795, 3575, 0, 3025, 2035, 1210},
						{2420, 2585, 3960, 2695, 3025, 0, 3685, 2585},
						{3355, 1375, 3410, 2475, 2035, 3685, 0, 3080},
						{2585, 2530, 2255, 2035, 1210, 2585, 3080, 0}};
	
		int minCost = 0;
		minCost = prim(matrix,0);
		System.out.println("The minimum cost is: " + minCost);
	}
	
	//Method utilizes Prim's algorithm to determine the cost of going down a particular index
	//Returns the minimum cost of visiting every row from that particular index
	public static int prim(int[][] array, int index) {
		boolean[] visited = new boolean[8];
		visited[index] = true;
		int cost = 0;
		int smallest = Integer.MAX_VALUE;
		int indexSmallest = 0;
		int row = 0;
		
		//Loop continues while the visited array hasn't been filled
		while(!full(visited)) {
			for(int i = 0; i < visited.length; i++) {
				//continues down the path if the index has yet to be explored
				if(visited[i] != false) {
					for(int column = 0; column < array.length; column++) {
						if(array[i][column] < smallest && visited[column] == false) {
							smallest = array[i][column];
							indexSmallest = column;
							row = i;
						}
					}
				}
			}
			//Prints the current row, the next index, and the smallest weight of the row
			System.out.println((row + 1) + "-->" + (indexSmallest + 1) + ": " + smallest);
			cost += smallest;
			smallest = Integer.MAX_VALUE;
			//Adds a true to visited once the index has been explored
			visited[indexSmallest] = true;
		}
		return cost;
	}
	
	//Checks whether the visited array is completely filled (all true)
	public static boolean full(boolean[] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i] == false) {
				return false;
			}
		}
		return true;
	}
	
	//Takes in the matrix and prints it out in the proper format
	public static void printMethod(int[][] array) {
		for(int row = 0; row < array.length; row++) {
			System.out.print("|");
			for(int column = 0; column < array[row].length; column++) {
				System.out.printf("%5d%s", array[row][column], "|");
			}
			System.out.println();
			System.out.println("-------------------------------------------------");
		}
	}
}
