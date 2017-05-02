/** Author: Joseph Tassone
 *  Description: Exception class used for adding duplicate items to the tree
 *  and for deleting an item that doesn't exist on the tree.
 */

public class BTSException extends RuntimeException{
	
	public BTSException(String message) {
		super(message);
	}
}






