/** Author: Joseph Tassone
 *  Description: Class for a generic 2-3 tree, with methods for
 *  manipulating data on the tree. 
 */

public class TTTree <T extends Comparable <? super T>> implements BalancedTree<T>{
	
	//The TTNode class creates a node which makes up the body of the tree
	//Utilized as an inner class to eliminate the need for getters and setters
	//Data members consist of arrays based on position
	private class TTNode<T> {
		Object[] items = new Object [3];
		TTNode<T>[] child = new TTNode[4];
		
		//A constructor for creating a node with links to left and right children
		public TTNode(T i, TTNode<T> left, TTNode<T> right) {
			this.items[0] = i;
			child[0] = left;
			child[1] = right;
		}

		//A constructor for creating a node with links to left, right, and middle children (2 values required)
		public TTNode(T i1, T i2, TTNode<T> left, TTNode<T> middle, TTNode<T> right) {
			this.items[0] = i1;
			this.items[1] = i2;
			child[0] = left;
			child[1] = middle;
			child[2] = right;
		}
	}

	private TTNode<T> root;
	
	//Constructor creates an empty TTree
	public TTTree() {
		root = null;
	}
	
	//Returns true or false to whether the tree is empty
	@Override
	public boolean isEmpty() {
		return root == null;
	}
	
	//Method takes in an item, and adds a node containing the item to the tree
	//Insert a node if there is no root
	//Calls the helper add method other inserting scenarios
	@Override
	public void insert(T val) {
		if(root == null) {
			root = new TTNode(val, null, null);
		}
		else {
			insert(val, root);
		}
	}

	//Compares to the root to determine the side
	//Recursively travels to the appropriate position and adds the node
	//Nodes are only added to leaves
	private void insert(T i, TTNode<T> r) {
		if(r.child[0] == null) {
			add2array(r.items, i);
		}
		//Compares, adds, and spilts down the left child if comparison is met
		else if(i.compareTo((T) r.items[0]) < 0) {
			insert(i, r.child[0]);
			if(r.child[0].items[2] != null) {
				T middle = (T) r.child[0].items[1];
				TTNode<T> newLink = split(r.child[0]);
				r.items[2] = r.items[1];
				r.items[1] = r.items [0];
				r.child[3] = r.child[2];
				r.child[2] = r.child[1];
				r.items[0] = middle;
				r.child[1] = newLink;
			}
		}
		//Compares, adds, and spilts down the right child if comparison is met
		else if(r.items[1] != null && i.compareTo((T) r.items[1]) > 0){
			insert(i, r.child[2]);
			if(r.child[2].items[2] != null) {
				T middle = (T) r.child[2].items[1];
				TTNode newLink = split(r.child[2]);
				r.items[2] = middle;
				r.child[3] = newLink;
			}
		}
		else {
			//Compares, adds, and spilts down the middle child if comparison is met
			insert(i, r.child[1]);
			if(r.child[1].items[2] != null) {
				T middle = (T) r.child[1].items[1];
				TTNode<T> newLink = split(r.child[1]);
				r.items[2] = r.items[1];
				r.child[3] = r.child[2];
				r.items[1] = middle;
				r.child[2] = newLink;
			}
		}
		//Adds if the node is always the root
		if(r == root && root.items[2] != null) {
			root = new TTNode(r.items[1], null, null);
			TTNode<T> newLink = split(r);
			root.child[0] = r;
			root.child[1] = newLink;
		}
	}
	
	//Adds the new item to the item array based on node placement
	private void add2array(Object[] items, T i) {
		if(i.compareTo((T) items[0]) < 0) {
			items[2] = items[1];
			items[1] = items[0];
			items[0] = i;
		}
		else if (items[1] != null && ((T)items[1]).compareTo(i) < 0) {
			items[2] = i;
		}
		else {
			items[2] = items[1];
			items[1] = i;
		}
	}
	
	//Splits the node for the case of three items in a node (can't occur)
	//Middle item pops upward toward parent
	private TTNode<T> split(TTNode<T> r){
		TTNode<T> newNode = new TTNode(r.items[2], r.child[2], r.child[3]);
		r.items[1] = null; r.items[2] = null;
		r.child[2] = null; r.child[3] = null;
		return newNode;
	}
	
	//Method takes in an item, and removes the nodes containing the item from the tree
	//Calls the helper delete method to do the actual deleting 
	@Override
	public void delete(T item) throws RuntimeException{
	}
	
	//The method checks the cases for deleting the node
	//Note: couldn't finish however, will need a method for merging instead of splitting
	private TTNode<T> deleteNode(TTNode<T> r) {
		T replacementItem;
		//Tests if the node is a leaf
		if(r == null) {
			return null;
		}
		//test for no left child
		else if(r.child[0] == null) {
			return r.child[1];
		}
		else if(r.child[1] == null) {
			return r.child[2];
		}
		else {
			//case if there are two children
			//retrieve and delete the inorder successor
			//replacementItem = findLeftMost(r.child[2]);
			//r.items[0] = replacementItem;
			//r.rightChild = deleteLeftMost(r.rightChild);
			return r;
		}
	}
		
	//Method takes in an item, and then determines if it's in the tree
	//Calls the helper contains method to do the actual searching
	@Override
	public boolean contains(T val) {
		return contains (val, root);
	}

	//Returns false if the tree is empty or the root is null
	//Recursively travels through the tree until it finds the item or doesn't
	private boolean contains (T i, TTNode<T> r) {
		if(r == null) {
			return false;
		}
		if(r.items[0] == i) {
			return true;
		}
		if(r.items[1] != null && r.items[1].equals(i)) {
			return true;
		}
		if(((T)r.items[0]).compareTo(i) > 0){
			return contains(i, r.child[0]);
		}
		else if(r.items[1] != null && ((T)r.items[1]).compareTo(i) < 0) {
			return contains (i, r.child[2]);
		}
		return contains(i, r.child[1]);
	}
	
	//Method determines whether the string is full
	//Passes to helper method to do the actual determining
	@Override
	public boolean isFullTree() {
		return isFullTree(root);
	}
	
	//Method checks the case for whether it's a full tree
	//Is full if it's empty, or the the height of the lowest children are the same
	private boolean isFullTree(TTNode<T> r) {
		if(isEmpty() || (root.child[0] == null && root.child[1] == null)) { 
			return true;
		}
		//Case if the r has three children
		if(r.child[0] != null && r.child[1] != null && r.child[2] != null) {
			if(maxHeight(r.child[0]) == maxHeight(r.child[1]) && maxHeight(r.child[1]) == maxHeight(r.child[2]) && maxHeight(r.child[1]) == maxHeight(r.child[2])) {
				return true;
			}
		}
		//Case if the r has two children
		if(r.child[0] != null && r.child[1] != null) {
			if(maxHeight(r.child[0]) == maxHeight(r.child[1])) {
				return true;
			}
		}
		return false;
	}

	//Method determines whether the tree is balanced
	//Passes to helper method to do the actual determining
	@Override
	public boolean isBalancedTree() {
		return isBalancedTree(root);
	}
	
	//Method checks to see if the cases are met for a balanced tree
	//Is empty, maxheight equals the minheight, maxheight less 1 equals the minheight are the case for balanced
	private boolean isBalancedTree(TTNode<T> r) {
		if(isEmpty() || (root.child[0] == null && root.child[1] == null)) { 
			return true;
		}
		//Case for if r has three children
		if(r.child[0] != null && r.child[1] != null && r.child[2] != null) {
			if(maxHeight(r.child[0])- maxHeight(r.child[1]) == 1 || maxHeight(r.child[0]) - maxHeight(r.child[1]) == -1 || maxHeight(r.child[0])- maxHeight(r.child[1]) == 0
					&& maxHeight(r.child[1]) - maxHeight(r.child[2]) == 1 || maxHeight(r.child[1]) - maxHeight(r.child[2]) == -1 || maxHeight(r.child[1]) - maxHeight(r.child[2]) == 0 
					&& maxHeight(r.child[0]) - maxHeight(r.child[2]) == 1 || maxHeight(r.child[0]) - maxHeight(r.child[2]) == -1 || maxHeight(r.child[0]) - maxHeight(r.child[2]) == 0) {
				return true;
			}
		}
		//Case for if r has two children
		if(r.child[0] != null && r.child[1] != null) {
			if(maxHeight(r.child[0]) - maxHeight(r.child[1]) == 1 || maxHeight(r.child[0]) - maxHeight(r.child[1]) == -1 
					|| maxHeight(r.child[0]) - maxHeight(r.child[1]) == 0) {
				return true;
			}
		}
		return false;
	}

	//Method calculates the height of the tree
	//Utilizes the helper method to do the actual comparison and calculating
	@Override
	public int treeHeight() {
		int heightVar = maxHeight(root);
		return heightVar;
	}
	
	//Only checks the height down one side as 2-3 tree is always full, and therefore has same height on all sides
	private int maxHeight(TTNode<T> r) { 
		if(r == null) {
			return 0;
		} 
		return maxHeight(r.child[0]) + 1;
	}
	
	//Counts the number of nodes within the tree
	//Calls the count helper method and returns the resulting int
	@Override
	public int nodeCount() {
		int count = nodeCount(root);
		return count;
	}
	
	//If the tree is empty return 0, else recursively travel through all the nodes
	//Each travel adds one to the count
	private int nodeCount(TTNode<T> r) {
		if(r == null) {
			return 0;
		}
		return nodeCount(r.child[0]) + nodeCount(r.child[1]) + nodeCount(r.child[2]) + 1;
	}
	
	//Methods travels through the tree inorder and prints out the items
	//Utilizes the helper method to do the actual traveling
	public void printInOrder() {
		printInOrder(root);
		System.out.println();
	}
	
	//Travels through the tree inorder and prints each node's item
	private void printInOrder(TTNode <T> r) {
		if(r == null) return;
		printInOrder(r.child[0]);
		System.out.print(r.items[0] + ", ");
		printInOrder(r.child[1]);
		if(r.items[1] != null) {
			System.out.print(r.items[1] + ", ");
			printInOrder(r.child[2]);
		}
	}
}


