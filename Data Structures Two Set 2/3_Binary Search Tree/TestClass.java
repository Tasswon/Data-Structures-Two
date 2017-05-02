import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/** Author: Joseph Tassone
 *  Description: The program creates three binary search trees (shorts, floats, and characters),
 *  and manipulates data within the trees.
 */

public class TestClass {
	public static void main(String[] args) {
		
		//Creates three trees: shorts, floats, characters
		BinarySearchTree<Short> test1 = new BinarySearchTree<Short>();
		BinarySearchTree<Float> test2 = new BinarySearchTree<Float>();
		BinarySearchTree<Character> test3 = new BinarySearchTree<Character>();
		
		//Adds 15 shorts to the test1 binary search tree		
		test1.add((short) 6);
		test1.add((short) 3);
		test1.add((short) 49);
		test1.add((short) 17);
		test1.add((short) 25);
		test1.add((short) 5);
		test1.add((short) 58);
		test1.add((short) 18);
		test1.add((short) 42);
		test1.add((short) 31);
		test1.add((short) 22);
		test1.add((short) 7);
		test1.add((short) 29);
		test1.add((short) 1);
		test1.add((short) 37);
		
		//Adds 15 floats to the test2 binary search tree
		test2.add((float) 6.6);
		test2.add((float) 3.4);
		test2.add((float) 49.4);
		test2.add((float) 42.1);
		test2.add((float) 17.5);
		test2.add((float) 25.9);
		test2.add((float) 7.4);
		test2.add((float) 29.5);
		test2.add((float) 5.2);
		test2.add((float) 58.4);
		test2.add((float) 18.7);
		test2.add((float) 31.3);
		test2.add((float) 22.5);
		test2.add((float) 1.2);
		test2.add((float) 37.7);
		
		//Add 15 characters to the test3 binary search tree
		test3.add('M');
		test3.add('L');
		test3.add('A');
		test3.add('P');
		test3.add('C');
		test3.add('O');
		test3.add('Z');
		test3.add('X');
		test3.add('G');
		test3.add('D');
		test3.add('Q');
		test3.add('K');
		test3.add('B');
		test3.add('V');
		test3.add('E');
	
		
		//Queries for the short tree
		System.out.println("Number of nodes: " + test1.count());
		System.out.println("Max Height: " + test1.maxHeight());
		System.out.println("Minimum Height: " + test1.minHeight());
		System.out.println("Is Full: " + test1.isFull());
		System.out.println("Is Balanced: " + test1.isBalanced());
		System.out.println("Is Complete: " + test1.isComplete());
		System.out.print("In Order: ");
		test1.printInOrder();
		System.out.print("Pre Order: ");
		test1.printPreOrder();
		System.out.print("Post Order: ");
		test1.printPostOrder();
		System.out.println("Is a Leaf (7): " + test1.isLeaf((short) 7));
		System.out.println("Is a Leaf (22): " + test1.isLeaf((short) 22));
		System.out.println("Is a Leaf (17): " + test1.isLeaf((short) 17));
		System.out.println("Is a Leaf (18): " + test1.isLeaf((short) 18));
		test1.delete((short) 7);
		test1.delete((short) 22);
		test1.delete((short) 17);
		test1.delete((short) 18);
		System.out.println("Number of nodes: " + test1.count());
		System.out.println("Max Height: " + test1.maxHeight());
		System.out.println("Minimum Height: " + test1.minHeight());
		System.out.println("Is Full: " + test1.isFull());
		System.out.println("Is Balanced: " + test1.isBalanced());
		System.out.println("Is Complete: " + test1.isComplete());
		System.out.print("In Order: ");
		test1.printInOrder();
		System.out.print("Pre Order: ");
		test1.printPreOrder();
		System.out.print("Post Order: ");
		test1.printPostOrder();
		
		System.out.println();
		
		//Queries for the float tree
		System.out.println("Number of nodes: " + test2.count());
		System.out.println("Max Height: " + test2.maxHeight());
		System.out.println("Minimum Height: " + test2.minHeight());
		System.out.println("Is Full: " + test2.isFull());
		System.out.println("Is Balanced: " + test2.isBalanced());
		System.out.println("Is Complete: " + test2.isComplete());
		System.out.print("In Order: ");
		test2.printInOrder();
		System.out.print("Pre Order: ");
		test2.printPreOrder();
		System.out.print("Post Order: ");
		test2.printPostOrder();
		System.out.println("Is a Leaf (22.5): " + test2.isLeaf((float) 22.5));
		System.out.println("Is a Leaf (37.7): " + test2.isLeaf((float) 37.7));
		System.out.println("Is a Leaf (42.1): " + test2.isLeaf((float) 42.1));
		System.out.println("Is a Leaf (6.6): " + test2.isLeaf((float) 6.6));
		test2.delete((float) 22.5);
		test2.delete((float) 37.7);
		test2.delete((float) 42.1);
		test2.delete((float) 6.6);
		System.out.println("Number of nodes: " + test2.count());
		System.out.println("Max Height: " + test2.maxHeight());
		System.out.println("Minimum Height: " + test2.minHeight());
		System.out.println("Is Full: " + test2.isFull());
		System.out.println("Is Balanced: " + test2.isBalanced());
		System.out.println("Is Complete: " + test2.isComplete());
		System.out.print("In Order: ");
		test2.printInOrder();
		System.out.print("Pre Order: ");
		test2.printPreOrder();
		System.out.print("Post Order: ");
		test2.printPostOrder();
		
		System.out.println();
		
		//Queries for the character tree
		System.out.println("Number of nodes: " + test3.count());
		System.out.println("Max Height: " + test3.maxHeight());
		System.out.println("Minimum Height: " + test3.minHeight());
		System.out.println("Is Full: " + test3.isFull());
		System.out.println("Is Balanced: " + test3.isBalanced());
		System.out.println("Is Complete: " + test3.isComplete());
		System.out.print("In Order: ");
		test3.printInOrder();
		System.out.print("Pre Order: ");
		test3.printPreOrder();
		System.out.print("Post Order: ");
		test3.printPostOrder();
		System.out.println("Is a Leaf (K): " + test3.isLeaf('K'));
		System.out.println("Is a Leaf (P): " + test3.isLeaf('V'));
		System.out.println("Is a Leaf (Z): " + test3.isLeaf('Z'));
		System.out.println("Is a Leaf (L): " + test3.isLeaf('L'));
		test3.delete('K');
		test3.delete('V');
		test3.delete('Z');
		test3.delete('L');
		System.out.println("Number of nodes: " + test3.count());
		System.out.println("Max Height: " + test3.maxHeight());
		System.out.println("Minimum Height: " + test3.minHeight());
		System.out.println("Is Full: " + test3.isFull());
		System.out.println("Is Balanced: " + test3.isBalanced());
		System.out.println("Is Complete: " + test3.isComplete());
		System.out.print("In Order: ");
		test3.printInOrder();
		System.out.print("Pre Order: ");
		test3.printPreOrder();
		System.out.print("Post Order: ");
		test3.printPostOrder();
		
		//Saves short trees to files based on traversal order
		//Adds the values in the file to a new tree
		System.out.println();
		test1.saveInOrder();
		test1.savePreOrder();
		test1.savePostOrder();
		BinarySearchTree<Short> short1= treeBuildFromSorted(binaryTreeArray("C:/Users/joeta/Desktop/Files/BSTinorder.txt"));
		BinarySearchTree<Short> short2= treeBuildFromSorted(binaryTreeArray("C:/Users/joeta/Desktop/Files/BSTpreorder.txt"));
		BinarySearchTree<Short> short3= treeBuildFromSorted(binaryTreeArray("C:/Users/joeta/Desktop/Files/BSTpostorder.txt"));
		short1.printInOrder();
		short1.printPreOrder();
		short1.printPostOrder();
		short2.printInOrder();
		short2.printPreOrder();
		short2.printPostOrder();
		short3.printInOrder();
		short3.printPreOrder();
		short3.printPostOrder();
		
		//Saves float tree to files based on traversal order
		//Adds the values in the file to a new tree
		System.out.println();
		test2.saveInOrder();
		test2.savePreOrder();
		test2.savePostOrder();
		BinarySearchTree<Float> float1= treeBuildFromSorted2(binaryTreeArray("C:/Users/joeta/Desktop/Files/BSTinorder.txt"));
		BinarySearchTree<Float> float2= treeBuildFromSorted2(binaryTreeArray("C:/Users/joeta/Desktop/Files/BSTinorder.txt"));
		BinarySearchTree<Float> float3= treeBuildFromSorted2(binaryTreeArray("C:/Users/joeta/Desktop/Files/BSTinorder.txt"));
		float1.printInOrder();
		float1.printPreOrder();
		float1.printPostOrder();
		float2.printInOrder();
		float2.printPreOrder();
		float2.printPostOrder();
		float3.printInOrder();
		float3.printPreOrder();
		float3.printPostOrder();
		
		//Saves character tree to files based on traversal order
		//Adds the values in the file to a new tree
		System.out.println();
		test3.saveInOrder();
		test3.savePreOrder();
		test3.savePostOrder();
		BinarySearchTree<Character> character1= treeBuildFromSorted3(binaryTreeArray("C:/Users/joeta/Desktop/Files/BSTinorder.txt"));
		BinarySearchTree<Character> character2= treeBuildFromSorted3(binaryTreeArray("C:/Users/joeta/Desktop/Files/BSTinorder.txt"));
		BinarySearchTree<Character> character3= treeBuildFromSorted3(binaryTreeArray("C:/Users/joeta/Desktop/Files/BSTinorder.txt"));
		character1.printInOrder();
		character1.printPreOrder();
		character1.printPostOrder();
		character2.printInOrder();
		character2.printPreOrder();
		character2.printPostOrder();
		character3.printInOrder();
		character3.printPreOrder();
		character3.printPostOrder();
	}
	
	//Takes in a file and saves the values to an object array
	public static Object[] binaryTreeArray(String location) {
		try {
			File file = new File(location);
			Scanner input = new Scanner(file);
			String save = null;
			while(input.hasNext()) {
				save = input.nextLine();
			}
			Object[] array = save.split(", ");
			return array;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//Creates a new binary search tree from an array of shorts
	public static BinarySearchTree<Short> treeBuildFromSorted(Object[] items) {
		BinarySearchTree<Short> temp = new BinarySearchTree<Short>();
		Short [] array = new Short[items.length];
		for(int i = 0; i < items.length; i++) {
			array[i] = Short.parseShort((String) items[i]);
		}
		Arrays.sort(array);
		temp.recursiveAdd(array, 0, array.length - 1);
		return temp;
	}
	
	//Creates a new binary search tree from an array of floats
	public static BinarySearchTree<Float> treeBuildFromSorted2(Object[] items) {
		BinarySearchTree<Float> temp = new BinarySearchTree<Float>();
		Float [] array = new Float[items.length];
		for(int i = 0; i < items.length; i++) {
			array[i] = Float.parseFloat((String) items[i]);
		}
		Arrays.sort(array);
		temp.recursiveAdd(array, 0, array.length - 1);
		return temp;
	}

	//Creates a new binary search tree from an array of characters
	public static BinarySearchTree<Character> treeBuildFromSorted3(Object[] items) {
		BinarySearchTree<Character> temp = new BinarySearchTree<Character>();
		Character [] array = new Character[items.length];
		for(int i = 0; i < items.length; i++) {
			array[i] = ((String)items[i]).charAt(0);
		}
		Arrays.sort(array);
		temp.recursiveAdd(array, 0, array.length - 1);
		return temp;
	}
}
