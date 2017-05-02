/** Author: Joseph Tassone
 *  Description: A generic interface that contains methods for 
 *  a balanced binary search tree. 
 */

public interface BalancedTree <T> {
	
	boolean isEmpty();
	void insert(T item);
	void delete(T item);
	boolean contains(T item);
	boolean isFullTree();
	boolean isBalancedTree();
	int nodeCount();
	int treeHeight();
}
