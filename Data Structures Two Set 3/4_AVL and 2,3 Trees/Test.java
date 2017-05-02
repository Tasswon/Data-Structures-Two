/** Author: Joseph Tassone
 *  Description: The program allows the user to pick either an AVL or 2-3 tree
 *  and then data from a file is put into the tree for manipulation.
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		
		//Prompts the user to select a tree to store the data in
		System.out.print("Select a tree (2-3 Tree(1), AVL tree(2)): ");
		int choice = input.nextInt();
		
		//Utilized if the user select the 2-3 tree
		if(choice == 1) {
			TTTree test = new TTTree();
			//Takes in a file and puts the values into a string
			String inputString = new String(Files.readAllBytes(Paths.get("C:/Users/joeta/Desktop/23Tree_Test.txt")));

			//Splits the above string based on new lines
			String[] tempArray = inputString.split("\n");
			
			//Loops through the entire temp array performing actions
			for(int i = 0; i < tempArray.length; i++) {
				//Splits each element in the tempArray further based on space
				String[] tempSubArray = tempArray[i].split(" ");	
				//If the first cell of the new array has an 'I'  insert the second cell into the 2-3 tree
				if(tempSubArray[0].charAt(0) == 'I') {
					String temp = tempSubArray[1].replaceAll("\r","");
					int tempNum = Integer.parseInt(temp);
					test.insert(tempNum);
				}
				//If the first cell of the new array has an 'D', delete the second cell value from the 2-3 tree
//				else if(tempSubArray[0].charAt(0) == 'D') {
//					String temp = tempSubArray[1].replaceAll("\r","");
//					int tempNum = Integer.parseInt(temp);
//					test.delete(tempNum);
//				}
				//If the first cell of the new array has an 'R' the program reports the current tree's state
				else if(tempSubArray[0].charAt(0) == 'R'){
					//Returns if everything has been deleted from the tree
					if(test.isEmpty()) {
						System.out.println("Delete all the nodes");
					}
					else {
						System.out.print(test.isBalancedTree() == true ? "Balanced, " : "Not Balanced, ");
						System.out.print(test.nodeCount() + " Nodes, ");
						System.out.print("Height " + test.treeHeight() + "\n");
						System.out.println();
					}
				}
			}
		}
		
		//Utilized if the user select the AVL tree
		else if(choice == 2) {
			AVLTree test = new AVLTree();
			//Takes in a file and puts the values into a string
			String inputString = new String(Files.readAllBytes(Paths.get("C:/Users/joeta/Desktop/AVLTree_Test.txt")));

			//Splits the above string based on new lines
			String[] tempArray = inputString.split("\n");

			//Loops through the entire temp array performing actions
			for(int i = 0; i < tempArray.length; i++) {
				//Splits each element in the tempArray further based on space
				String[] tempSubArray = tempArray[i].split(" ");	
				//If the first cell of the new array has an 'I'  insert the second cell into the AVL tree
				if(tempSubArray[0].charAt(0) == 'I') {
					String temp = tempSubArray[1].replaceAll("\r","");
					int tempNum = Integer.parseInt(temp);
					test.insert(tempNum);
				}
				//If the first cell of the new array has an 'D', delete the second cell value from the AVL tree
				else if(tempSubArray[0].charAt(0) == 'D') {
					String temp = tempSubArray[1].replaceAll("\r","");
					int tempNum = Integer.parseInt(temp);
					test.delete(tempNum);
				}
				//If the first cell of the new array has an 'R', the program reports the current tree's state
				else if(tempSubArray[0].charAt(0) == 'R'){
					if(test.isEmpty()) {
						//Returns if everything has been deleted from the tree
						System.out.println("Delete all the nodes \n");
					}
					else {
						System.out.print(test.isBalancedTree() == true ? "Balanced, " : "Not Balanced, ");
						System.out.print(test.isFullTree() == true ? "Full, " : "Not Full, ");
						System.out.print("Height " + test.treeHeight() + ", ");
						System.out.print(test.nodeCount() + " Nodes \n");
						System.out.println();
					}
				}
			}
		}
		
		//Exits the program if the user didn't make a proper choice
		else {
			System.out.println("Invalid Entry!");
			System.exit(0);
		}
	}
}

