/** Author: Joseph Tassone
 *  Description: Tests whether an infix equation is correctly 
 *  formatted. 
 */

import java.util.Scanner;

public class GrammarProblem {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        //Allows the user to enter an expression
        System.out.print("Enter an expression: ");
        String expression = input.next();
        
        //Calls the findExpression method, and prints whether it is a valid
        if(findExpression(expression, 0, expression.length() - 1) != expression.length() - 1) {
            System.out.println("The following expression isn't valid: " + expression);
        }
        else {
            System.out.println("The following expression is valid: " + expression);
        }
    }
    
    //Method specifies that an expression is a single term or a term followed by a + or -
    public static int findExpression(String expression, int start, int end) {
    	
        start = findTerm(expression, start, end);
        
        //Checks whether start has become invalid, and then if start + 1 is a + or -
        //Continues the chain if it proves valid
        if(start < end && start != -1) {
        	if(expression.charAt(start + 1) == '+' || expression.charAt(start + 1) == '-') {
        		return findTerm(expression, start + 2, end);
        	}
        }
        return start;
    }
    
    //Method specifies that a term is either a single factor or a factor followed by a * or /
    private static int findTerm(String expression, int start, int end) {
    	
        start = findFactor(expression, start, end);
        
        //Checks whether start has become invalid, and then if start + 1 is a * or /
        //Sets start value if the checks proves valid
        if(start < end && start != -1) {
        	if(expression.charAt(start + 1) == '*' || expression.charAt(start + 1) == '/') {
        		start = findFactor(expression, start + 2, end); 
        	}
        }
        return start;
    }
    
    //The method specifies that a factor is either a single letter or an expression in parenthesis
    private static int findFactor(String expression, int start, int end) {
    	
    	//Checks if the index represented by start is a letter
        if(Character.isLetter(expression.charAt(start))) {
            return start;
        }
        
        //Checks if the index represented by start is a bracket
        if(expression.charAt(start) == '(') { 
            start = findExpression(expression, start + 1, end);
            //Checks whether start has become invalid, and then if start + 1 is a closing bracket
            if(start < end && start != -1) {
            	if(expression.charAt(start + 1) == ')') {
            		return start + 1; 
            	}
            } 
        }
        return -1;
    }
}