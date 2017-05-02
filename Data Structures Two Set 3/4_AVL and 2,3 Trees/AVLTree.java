/** Author: Joseph Tassone
 *  Description: Class for a generic AVL tree, with methods for
 *  manipulating data on the tree. 
 */

public class AVLTree <T extends Comparable <? super T>> implements BalancedTree<T>{
	
	//The TreeNode class creates a node which makes up the body of the tree
	//Utilized as an inner class to eliminate the need for getters and setters
	//Height data member is utilized for checking the balance of the tree
	private class TreeNode<T> {
		T item;
		int height;
		TreeNode<T> leftChild;
		TreeNode<T> rightChild;
		
		//A constructor for creating a node with links to children and height set to 1
		public TreeNode(T newItem) {
			height = 1;
			item = newItem;
			leftChild = null;
			rightChild = null;
		}
	}
	
	private TreeNode<T> root;

	//Constructor creates an empty AVL tree
	public AVLTree() {
	}

	//Constructor creates an AVL tree with a single node (root)
	public AVLTree(T rootItem) {
		root = new TreeNode(rootItem);
	}
	
	//Returns true or false to whether the tree is empty
	@Override
	public boolean isEmpty() {
		return root == null;
	}
	
	//Method takes in an item, and adds a node containing the item to the tree
	//Calls the helper add method to do the actual inserting
	@Override
	public void insert(T item) {
		root = insert(item, root);
	}
	
	//Adds the node as the root if it's empty
	//Compares to the root to determine the side
	//Recursively travels to the appropriate position and adds the node
	//Checks the balance once complete and adjusts the order if the tree is out of balance
	private TreeNode<T> insert(T item, TreeNode<T> r){
		if(r == null){
			return new TreeNode(item);
		}
		if(item.compareTo(r.item) == 0) {
			return r;
		}
		if(item.compareTo(r.item) > 0)  
			r.rightChild = insert(item, r.rightChild);
		else {
			r.leftChild = insert(item, r.leftChild);
		}
		r = balanceCheck(r);
		r.height = 1 + Math.max(((r.leftChild != null ? r.leftChild.height: 0)), (r.rightChild != null ? r.rightChild.height : 0));
		return r;
	} 
	
	//Checks the current balance of the tree following a deletion or insertion (based on node height comparison)
	//If out of balance the method utilizes the rotate methods to balance the tree
	private TreeNode<T> balanceCheck(TreeNode<T> r) {
		if(Math.abs(balanceValue(r)) > 1) {
			if(((r.leftChild != null ? r.leftChild.height: 0) < (r.rightChild != null ? r.rightChild.height : 0))) {
				r = rotateLeft(r);
				if(Math.abs(balanceValue(r))>1) {
					r = rotateRight(r);
					r.rightChild = rotateRight(r.rightChild);
					r = rotateLeft(r);
				}
			}
			else {
				r = rotateRight(r);
				if(Math.abs(balanceValue(r))>1) {
					r = rotateLeft(r);
					r.leftChild = rotateLeft(r.leftChild);
					r = rotateRight(r);
				}
			}
		}
		return r;
	}
	
	//Returns the current balance value (based on height) using the node's children 
	private int balanceValue(TreeNode<T> r) {
		return ((r.leftChild != null ? r.leftChild.height: 0) - (r.rightChild != null ? r.rightChild.height : 0));
	}
	
	//Rotates a node and it's children to the left to restore balance to the tree
	private TreeNode<T> rotateLeft(TreeNode<T> r) {
		TreeNode<T> newRoot = r.rightChild;
		TreeNode<T> temp = newRoot.leftChild;
		newRoot.leftChild = r;
		r.rightChild = temp;
		r.height = 1 + Math.max((r.leftChild != null ? r.leftChild.height: 0), (r.rightChild != null ? r.rightChild.height : 0));
		newRoot.height=  1 + Math.max((newRoot.leftChild != null ? newRoot.leftChild.height: 0), (newRoot.rightChild != null ? newRoot.rightChild.height : 0));
		return newRoot; 
	}
	
	//Rotates a node and it's children to the right to restore balance to the tree
	private TreeNode<T> rotateRight(TreeNode<T> r) {
		TreeNode<T> newRoot = r.leftChild;
		TreeNode<T> temp = newRoot.rightChild;
		newRoot.rightChild = r;
		r.leftChild = temp;
		r.height = 1 + Math.max((r.leftChild != null ? r.leftChild.height: 0), (r.rightChild != null ? r.rightChild.height : 0));
		newRoot.height=  1 + Math.max((newRoot.leftChild != null ? newRoot.leftChild.height: 0), (newRoot.rightChild != null ? newRoot.rightChild.height : 0));
		return newRoot; 
	}
	
	//Method takes in an item, and removes the nodes containing the item from the tree
	//Calls the helper delete method to do the actual deleting (4 other methods)
	@Override
	public void delete(T item) {
		root = delete(item, root); 
	}
	
	//Throws an exception if the item doesn't exist in the tree
	//Calls the delete node method to determine the case and delete the node
	//Checks the balance once complete and adjusts if the tree is out of balance
	private TreeNode<T> delete(T item, TreeNode<T> r) throws RuntimeException{
		if(r == null) {
			throw new RuntimeException("Does not exist to delete!");
		} 
		if(r.item.compareTo(item) == 0) {
			r = deleteNode(r);
		}
		else if(r.item.compareTo(item) > 0) { 
			r.leftChild = delete(item, r.leftChild);
		}
		else {
			r.rightChild = delete(item, r.rightChild);
		}
		if(r != null) {
			r = balanceCheck(r);
			r.height = 1 + Math.max(((r.leftChild != null ? r.leftChild.height: 0)), (r.rightChild != null ? r.rightChild.height : 0));
		}
		return r;
	}
	
	//The method checks the cases for deleting the node
	private TreeNode<T> deleteNode(TreeNode<T> r) {
		T replacementItem;
		//Tests if the node is a leaf
		if(isLeaf(r.item)) {
			return null;
		}
		//test for no left child
		else if(r.leftChild == null) {
			return r.rightChild;
		}
		//tests for no right child
		else if(r.rightChild == null) {
			return r.leftChild;
		}
		else {
			//case if there are two children
			//retrieve and delete the inorder successor
			replacementItem = findLeftMost(r.rightChild);
			r.item = replacementItem;
			r.rightChild = deleteLeftMost(r.rightChild);
			return r;
		}
	}
	
	//Method is utilized for the two children deletion case
	//The method finds the left most node of the right child for the replacement item
	private T findLeftMost(TreeNode<T> r) {
		if(r.leftChild == null) {
			return r.item;
		} 
		else {
			return findLeftMost(r.leftChild);
		}
	}
	
	//Method is utilized for the two children deletion case
	//The method deletes the left most node 
	private TreeNode <T> deleteLeftMost(TreeNode<T> r) {
		if(r.leftChild == null) {
			return r.rightChild;
		}
		else {
			r.leftChild = deleteLeftMost(r.leftChild);
			return r;
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
	private boolean contains(T item, TreeNode<T> r) {
		if(isEmpty() || r == null) {
			return false;
		}
		if(r.item.compareTo(item) == 0) {
			return true;
		}
		else if((item).compareTo(r.item) < 0) {
			return contains(item, r.leftChild);
		}
		return contains(item, r.rightChild);
	}
	
	//Method takes in an item, and then determines if it's a leaf
	//Calls the helper isLeaf method to do the actual checking
	public boolean isLeaf(T item) {
		return isLeaf(item, root);
	}
	
	//Returns false if the tree is empty or the root is null
	//Recursively travels through the tree until it finds the item or doesn't
	//Checks if it has children, and if it doesn't it returns true
	private boolean isLeaf(T item, TreeNode<T> r) {
		if(isEmpty() || r == null) {
			return false;
		}
		if(r.item.compareTo(item) == 0 && (r.leftChild == null && r.rightChild == null)) {
			return true;
		}
		if((item).compareTo(r.item) < 0) {
			return isLeaf(item, r.leftChild);
		}
		return isLeaf(item, r.rightChild);
	}

	//Method determines whether the string is full
	//Passes to helper method to do the actual determining
	@Override
	public boolean isFullTree() {
		return isFullTree(root);
	}

	//Method checks the case for whether it's a full tree
	//Is full if it's empty, or the the height of the lowest children is the same
	private boolean isFullTree(TreeNode<T> r) {
		if(r == null) {
			return true;
		}
		if(r.rightChild == null && r.leftChild == null) {
			return true;
		}
		else if ((r.leftChild != null && r.rightChild != null)) {
			return isFullTree(r.leftChild) && isFullTree(r.rightChild);
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
	private boolean isBalancedTree(TreeNode<T> r) {
		if(isEmpty() || (root.rightChild == null && root.leftChild == null)) { 
			return true;                 
		}
		if(r.leftChild != null && r.rightChild != null) {
			if(maxHeight(r.leftChild) - maxHeight(r.rightChild) == 1 || maxHeight(r.leftChild) - maxHeight(r.rightChild) == -1 
					|| maxHeight(r.leftChild) - maxHeight(r.rightChild) == 0) {
				return true;
			}
		}
		return false;
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
	private int nodeCount(TreeNode <T> r) {
		if(r == null) {
			return 0;
		}
		return nodeCount(r.leftChild) + nodeCount(r.rightChild) + 1;
	}
	
	//Method calculates the height of the tree
	//Utilizes the helper method to do the actual comparison and calculating
	@Override
	public int treeHeight() {
		int max = maxHeight(root);
		return max;
	}
	
	//Method travels through to two children at every level and returns the greater of the two
	private int maxHeight(TreeNode<T> r) { 
		if(r == null) {
			return 0;
		}
		return ((maxHeight(r.leftChild) >= maxHeight(r.rightChild) ? maxHeight(r.leftChild) + 1: maxHeight(r.rightChild) + 1));
	}
	
	//Methods travels through the tree inorder and prints out the items
	//Utilizes the helper method to do the actual traveling
	public void printInOrder() {
		printInOrder(root);
	}
	
	//Travels through the tree inorder and prints each node's item
	private void printInOrder(TreeNode <T> r) {
		if(r == null) {
			return;
		}
		printInOrder(r.leftChild);  
		System.out.print("[" + r.item + "]"); 
		printInOrder(r.rightChild); 
	}
	
	//Methods travels through the tree in preorder and prints out the items
	//Utilizes the helper method to do the actual traveling
	public void printPreOrder() {
		printPreOrder(root);
	}
	
	//Travels through the tree in preorder and prints each node's item
	private void printPreOrder(TreeNode<T> r) {
		if(r == null) {  
			return;
		} 
		System.out.print("[" + r.item + "]"); 
		printPreOrder(r.leftChild);  
		printPreOrder(r.rightChild);  
	}
}





