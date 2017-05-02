/** Author: Joseph Tassone
 *  Description: Creates a node which will keep show whether
 *  it's been visited and hold an arraylist of integers. 
 */

import java.util.ArrayList;

public class Node {
	
	//Defaults visited to false as it's unvisited at the start
	private ArrayList<Integer> list;
	boolean visited = false;

	//Constructor creates a new arraylist 
	public Node() {
		list = new ArrayList<>();
	}

	//Add an integer to the arraylist
	public void addToList(int weight) {
		list.add(weight);
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public boolean getVisited() {
		return this.visited;
	}
	
	//Returns the value at the index in the arraylist
	public int get(int index) {
		return list.get(index);
	}
}
