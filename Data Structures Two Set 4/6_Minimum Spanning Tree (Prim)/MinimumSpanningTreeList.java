/** Author: Joseph Tassone
 *  Description: Demonstrate the use of Prim’s algorithm to find the minimum cost
 *  as represented by an adjacency list.
 */

import java.util.ArrayList;

public class MinimumSpanningTreeList {
	public static void main(String[] args) {
		
		Integer[][] matrix = {{0, 2145, 3520, 1540, 2475, 2420, 3355, 2585},
							{2145, 0, 1980, 3795, 1815, 2585, 1375, 2530},
							{3520, 1980, 0, 2475, 3795, 3960, 3410, 2255},
							{1540, 3795, 2475, 0, 3575, 2695, 2475, 2035},
							{2475, 1815, 3795, 3575, 0, 3025, 2035, 1210},
							{2420, 2585, 3960, 2695, 3025, 0, 3685, 2585},
							{3355, 1375, 3410, 2475, 2035, 3685, 0, 3080},
							{2585, 2530, 2255, 2035, 1210, 2585, 3080, 0}};
		
		//Node array holds the nodes (technically the row number of the matrix)
		//Creates eight nodes, one for each row number
		Node[] array = new Node[8];
		for(int i = 0; i < array.length; i++) {
			Node node = new Node();
			array[i] = node;
		}
		
		//Adds all the weights in the row to the arraylist within that row
		for(int row = 0; row < matrix.length; row++) {
			for(int column = 0; column < matrix[row].length; column++) {
				array[row].addToList(matrix[row][column]);
			}
		}
		
		//Calls the prim method to determine the lowest cost 
		int minCost = 0;
		minCost = prim(array,0);
		System.out.println("The minimum cost is: " + minCost);
	}
	
	//Method utilizes Prim's algorithm to determine the cost of going down a particular index
	//Returns the minimum cost of visiting every row from that particular index
	public static int prim(Node[] array, int index) {
		int cost = 0;
		int smallest = Integer.MAX_VALUE;
		int indexSmallest = 0;
		int row = 0;
		array[0].setVisited(true);
		
		//Loop continues until every node has been visited
		while(!full(array)) {
			for(int i = 0; i < array.length; i++) {
				//continues down the path if the node has yet to be explored
				if(array[i].getVisited() != false) {
					for(int j = 0; j < 8; j++) {
						if(array[i].get(j) < smallest && array[j].getVisited() == false) {
							smallest = array[i].get(j);
							indexSmallest = j;
							row = i;
						}
					}
				}
			}
			//Prints the current row, the next index, and the smallest weight of the row
			System.out.println((row + 1) + "-->" + (indexSmallest + 1) + ": " + smallest);
			cost += smallest;
			array[indexSmallest].setVisited(true);
			smallest = Integer.MAX_VALUE;
		}
		return cost;
	}

	//Checks whether every node has been visited
	public static boolean full(Node[] array) {
		for(int i = 0; i < array.length; i++) {
			if(array[i].getVisited() == false) {
				return false;
			}
		}
		return true;
	}
}
