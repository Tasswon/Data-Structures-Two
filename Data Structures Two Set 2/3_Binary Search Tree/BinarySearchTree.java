/** Author: Joseph Tassone
 *  Description: Class for a generic binary search tree, with methods for
 *  manipulating data on the tree. 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BinarySearchTree <T extends Comparable <? super T>> implements BSTInterface <T>{
	
	//The TreeNode class creates a node which makes up the body of the tree
	//Utilized as an inner class to eliminate the need for getters and setters
	private class TreeNode<T> {
		T item;
		TreeNode<T> leftChild;
		TreeNode<T> rightChild;
		
		//A constructor for creating a node with an item and no children
		public TreeNode(T newItem) {
			item = newItem;
			leftChild = null;
			rightChild = null;
		}
		
		//A constructor for creating a node with links pointing to a left child and right child
		public TreeNode(T newItem, TreeNode<T> left, TreeNode<T> right) {
			item = newItem;
			leftChild = left;
			rightChild = right;
		}
	}
	
	private TreeNode<T> root;
	
	//Constructor creates an empty binary search tree
	public BinarySearchTree() {
	}
	
	//Constructor creates a binary search tree with a single node (root)
	public BinarySearchTree(T rootItem) {
		root = new TreeNode(rootItem, null, null);
	}
	
	//Returns true or false to whether the tree is empty
	@Override
	public boolean isEmpty() {
		return root == null;
	}
	
	//Method takes in an item, and adds a node containing the item to the tree
	//Calls the helper add method to do the actual adding
	@Override
	public void add(T item) throws BTSException {
		add(item, root);
	}
	
	//Adds the node as the root if it's empty
	//Compares to the root to determine the side
	//Recursively travels to the appropriate position and adds the node
	private void add(T item, TreeNode<T> root) throws BTSException {
		if(isEmpty()) {
			this.root = new TreeNode(item, null, null);
		}
		else if((item.compareTo(root.item)) == 0) {
			throw new BTSException("Attempting to add a duplicate!");
		}
		else if((item).compareTo(root.item) < 0) {
			if(root.leftChild == null) {
				root.leftChild = new TreeNode(item, null, null);
			}
			else {
				add(item, root.leftChild);
			}
		}
		else {
			if(root.rightChild == null) {
				root.rightChild = new TreeNode(item, null, null);
			}
			else {
				add(item, root.rightChild);
			}
		}
	}
	
	//Method takes in an item, and then determines if it's in the tree
	//Calls the helper contains method to do the actual searching
	@Override
	public boolean contains(T item) {
		return contains(item, root);
	}
	
	//Returns false if the tree is empty or the root is null
	//Recursively travels through the tree until it finds the item or doesn't
	private boolean contains(T item, TreeNode<T> root) {
		if(isEmpty() || root == null) {
			return false;
		}
		if(root.item.compareTo(item) == 0) {
			return true;
		}
		if((item).compareTo(root.item) < 0) {
			return contains(item, root.leftChild);
		}
		return contains(item, root.rightChild);
	}
	
	//Method takes in an item, and removes the nodes contianing the item from the tree
	//Calls the helper delete method to do the actual deleting (4 other methods)
	@Override
	public void delete(T item) throws BTSException{
		root = delete(item, root);	
	}
	
	//Throws an exception if the item doesn't exist in the tree
	//Calls the delete node method to determine the case and delete the node
	private TreeNode<T> delete(T item, TreeNode<T> root) throws BTSException{
		if(root == null) {
			throw new BTSException("Does not exist to delete!");
		}
		if(root.item.compareTo(item) == 0) {
			root = deleteNode(root);
		}
		else if(root.item.compareTo(item) > 0) { 
			root.leftChild = delete(item, root.leftChild);
		}
		else
			root.rightChild = delete(item, root.rightChild);
		return root;
	}
	
	//The method checks the cases for deleting the node
	private TreeNode<T> deleteNode(TreeNode<T> root) {
		T replacementItem;
		//Tests if the node is a leaf
		if(isLeaf(root.item)) {
			return null;
		}
		//test for no left child
		else if(root.leftChild == null) {
			return root.rightChild;
		}
		//tests for no right child
		else if(root.rightChild == null) {
			return root.leftChild;
		}
		else {
			//case if there are two children
			//retrieve and delete the inorder successor
			replacementItem = findLeftMost(root.rightChild);
			root.item = replacementItem;
			root.rightChild = deleteLeftMost(root.rightChild);
			return root;
		}
	}
	
	//Method is utilized for the two children deletion case
	//The method finds the left most node for the replacement item
	private T findLeftMost(TreeNode<T> root) {
		if(root.leftChild == null) {
			return root.item;
		} 
		else {
			return findLeftMost(root.leftChild);
		}
	}
	
	//Method is utilized for the two children deletion case
	//The method deletes the left most node 
	private TreeNode <T> deleteLeftMost(TreeNode<T> root) {
		if(root.leftChild == null) {
			return root.rightChild;
		}
		else {
			root.leftChild = deleteLeftMost(root.leftChild);
			return root;
		}
	}
	
	//Removes all the nodes from the tree (clears it)
	@Override
	public void removeAll() {
		root = null;
	}
	
	//Method takes in an item, and then determines if it's a leaf
	//Calls the helper isLeaf method to do the actual checking
	public boolean isLeaf(T item) {
		return isLeaf(item, root);
	}
	
	//Returns false if the tree is empty or the root is null
	//Recursively travels through the tree until it finds the item or doesn't
	//Checks if it has children, and if it doesn't it returns true
	private boolean isLeaf(T item, TreeNode<T> root) {
		if(isEmpty() || root == null) {
			return false;
		}
		if(root.item.compareTo(item) == 0 && (root.leftChild == null && root.rightChild == null)) {
			return true;
		}
		if((item).compareTo(root.item) < 0) {
			return isLeaf(item, root.leftChild);
		}
		return isLeaf(item, root.rightChild);
	}
	
	//Counts the number of nodes within the tree
	//Calls the count helper method and returns the resulting int
	public int count() {
		int count = count(root);
		return count;
	}
	
	//If the tree is empty return 0, else recursively travel through all the nodes
	//Each travel adds one to the count
	private int count(TreeNode<T> root) {
		if(root == null) {
			return 0;
		}
		return count(root.leftChild) + count(root.rightChild) + 1;
	}
	
	//Method calculates the maximum height of the tree
	//Utilizes the helper method to do the actual comparison and calculating
	public int maxHeight() {
		int max = maxHeight(root);
		return max;
	}
	
	//Method calculates the minimum height of the tree
	//Utilizes the helper method to do the actual comparison and calculating
	public int minHeight() {
		int min = minHeight(root);
		return min;
	}
	
	//Method travels through to two sides and returns the greater of the two
	private int maxHeight(TreeNode<T> root) { 
		if(root == null) {
			return 0;
		} 
		return ((maxHeight(root.leftChild) >= maxHeight(root.rightChild) ? maxHeight(root.leftChild) + 1: maxHeight(root.rightChild) + 1));
	}
	
	//Method travels through to two sides and returns the lesser of the two
	private int minHeight(TreeNode<T> root) {
		if(root == null) {
			return 0;
		} 
		return ((minHeight(root.leftChild) <= minHeight(root.rightChild) ? minHeight(root.leftChild) + 1: minHeight(root.rightChild) + 1));
	}
	
	//Methods travels through the tree inorder and prints out the items
	//Utilizes the helper method to do the actual traveling (not printing)
	public void printInOrder() {
		System.out.println(saveInOrderHelper(root));
	}
	
	//Method travels through the tree inorder and saves the items to a file
	//Helper methods travel and save to the file
	public void saveInOrder()  {
		saveInOrder(root);
	}
	
	//Utilizes the helper method to create a string of the items inorder
	//Saves the string to a file and throws an exception if errors occur
	private void saveInOrder(TreeNode<T> root) {
		try {
			String save = saveInOrderHelper(root);
			File file = new File("C:/Users/joeta/Desktop/Files/BSTinorder.txt");
			PrintWriter output = new PrintWriter(file);
			output.print(save);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Travels through the tree inorder and saves each item to the string
	private String saveInOrderHelper(TreeNode<T> root) {
		String save = "";
		if(root == null) {
			return "";
		}
		else {
			save += saveInOrderHelper(root.leftChild);
			save += (root.item + ", ");
			save += saveInOrderHelper(root.rightChild);
		}
		return save;
	}
	
	//Methods travels through the tree in preorder and prints out the items
	//Utilizes the helper method to do the actual traveling (not printing)
	public void printPreOrder() {
		System.out.println(savePreOrderHelper(root));
	}
	
	//Method travels through the tree in pre order and saves the items to a file
	//Helper methods travel and save to the file
	public void savePreOrder()  {
		savePreOrder(root);
	}
	
	//Utilizes the helper method to create a string of the items in pre order
	//Saves the string to a file and throws an exception if errors occur
	private void savePreOrder(TreeNode<T> root){
		try {
			String save = savePreOrderHelper(root);
			File file = new File("C:/Users/joeta/Desktop/Files/BSTpreorder.txt");
			PrintWriter output = new PrintWriter(file);
			output.print(save);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Travels through the tree in preorder and saves each item to the string
	private String savePreOrderHelper(TreeNode<T> root) {
		String save = "";
		if(root == null) {
			return "";
		}
		else {
			save += (root.item + ", ");
			save += savePreOrderHelper(root.leftChild);
			save += savePreOrderHelper(root.rightChild);
		}
		return save;
	}
	
	//Methods travels through the tree in postorder and prints out the items
	//Utilizes the helper method to do the actual traveling (not printing)
	public void printPostOrder() {
		System.out.println(savePostOrderHelper(root));
	}
	
	//Method travels through the tree in post order and saves the items to a file
	//Helper methods travel and save to the file
	public void savePostOrder()  {
		savePostOrder(root);
	}
	
	//Utilizes the helper method to create a string of the items in post order
	//Saves the string to a file and throws an exception if errors occur
	private void savePostOrder(TreeNode<T> root){
		try {
			String save = savePostOrderHelper(root);
			File file = new File("C:/Users/joeta/Desktop/Files/BSTpostorder.txt");
			PrintWriter output = new PrintWriter(file);
			output.print(save);
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Travels through the tree in post order and saves each item to the string
	private String savePostOrderHelper(TreeNode<T> root) {
		String save = "";
		if(root == null) {
			return "";
		}
		else {
			save += savePostOrderHelper(root.leftChild);
			save += savePostOrderHelper(root.rightChild);
			save += (root.item + ", ");
		}
		return save;
	}
	
	//Method determines whether the tree is balanced
	//Passes to helper method to do the actual determining
	public boolean isBalanced() {
		return isBalanced(root);
	}
	
	//Method checks to see if the cases are met for a balanced tree
	//Is empty, maxheight equals the minheight, maxheight less 1 equals the minheight are the case for balanced
	private boolean isBalanced(TreeNode <T> root) {
		if(isEmpty() || maxHeight() == minHeight() || maxHeight() - 1 == minHeight()) {
			return true;
		}
		return false;
	}
	
	//Method determines whether the tree is complete
	//Passes to helper method to do the actual determining
	public boolean isComplete() {
		return isComplete(root);
	}
	
	//Method checks the cases for whether it's a complete tree
	//The tree is balanced or empty, the tree has nodes 
	//All nodes at level h – 2 and above have two children each
	//When a node at level h – 1 has children, all nodes to its left at the same level have two children each
	//When a node at level h – 1 has one child, it is a left child
	private boolean isComplete(TreeNode <T> root) {
		if(isEmpty() || root == null) {
			return true;
		}
		else if(root.leftChild == null && root.rightChild == null){
			return true;
		}
		else if(maxHeight(root.leftChild) == maxHeight(root.rightChild)) {
			return isFull(root.leftChild) && isComplete(root.rightChild);
		}
		else if(maxHeight(root.leftChild) > maxHeight(root.rightChild)) {
			return isComplete(root.leftChild);
		}
		return false;
	}
	
	//Method determines whether the string is full
	//Passes to helper method to do the actual determining
	public boolean isFull() {
		return isFull(root);
	}
	
	//Method checks the case for whether it's a balanced tree
	//Is full if it's empty, farthest right and left are null
	private boolean isFull(TreeNode <T> root) {
		if(minHeight(root) == maxHeight(root)) {
			return true;
		}
		return false;
	}

	//Recursively adds an array to a binary search tree 
	public void recursiveAdd(T [] array, int start, int end) {
		if(start > end) {
			return;
		}
		int mid = (start + end) / 2;
		this.add(array[mid]);
		
		recursiveAdd(array, mid + 1, end);
		recursiveAdd(array, start, mid - 1);
	}
}



