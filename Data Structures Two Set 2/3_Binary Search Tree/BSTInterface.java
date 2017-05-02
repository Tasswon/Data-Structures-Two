/** Author: Joseph Tassone
 *  Description: A generic interface that contains methods for 
 *  a binary search tree. 
 */

public interface BSTInterface <T> {
	
	public boolean isEmpty();
	public void add(T item) throws BTSException;
	public boolean contains(T item);
	public void delete(T item) throws BTSException;
	public void removeAll();
}







