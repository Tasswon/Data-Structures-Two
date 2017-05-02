/** Author: Joseph Tassone
 *  Course Code: COSC2007
 *  Description: The program manipulates a hashmap of employees, which have 
 *  been created and added to the map. 
 */

public class Test {
	public static void main(String[] args) {
		Hashmap  test = new Hashmap(2);
		
		//20 employees are created 
		Employee one = new Employee(401, "Joe", "Tassone", "Computer Science", 1);
		Employee two = new Employee(1111, "Don", "John", "Computer Science", 1);
		Employee three = new Employee(1234, "Briant", "K", "Computer Science", 1);
		Employee four = new Employee(22222, "Louis", "John", "Computer Science", 1);
		Employee five = new Employee(1604, "Cassy", "Tassone", "Computer Science", 1);
		Employee six = new Employee(24332, "Laura", "John", "Computer Science", 1);
		Employee seven = new Employee(1203, "Edwind", "Tassone", "Computer Science", 1);
		Employee eight = new Employee(2975, "Tyler", "John", "Computer Science", 1);
		Employee nine = new Employee(1074, "Zoro", "Tassone", "Computer Science", 1);
		Employee ten = new Employee(566, "Arnav", "John", "Computer Science", 1);
		Employee eleven = new Employee(956, "Sydney", "Tassone", "Computer Science", 1);
		Employee twelve = new Employee(802, "Billiam", "John", "Computer Science", 1);
		Employee thirteen = new Employee(470, "Cauzwell", "Tassone", "Computer Science", 1);
		Employee fourteen = new Employee(805, "Frank", "John", "Computer Science", 1);
		Employee fifteen = new Employee(757, "Tim", "Tassone", "Computer Science", 1);
		Employee sixteen = new Employee(9999, "Will", "John", "Computer Science", 1);
		Employee seventeen = new Employee(2005, "Steve", "Tassone", "Computer Science", 1);
		Employee eighteen = new Employee(5545, "Dan", "John", "Computer Science", 1);
		Employee nineteen = new Employee(7575, "Mister", "Tassone", "Computer Science", 1);
		Employee twenty = new Employee(2345, "Obelisk", "The", "Tormentor", 1);
	
		//the 20 employees are added to the map and then manipulated
		test.insert(one);
		test.insert(two);
		test.insert(three);
		test.insert(four);
		test.insert(five);
		test.insert(six);
		test.insert(seven);
		test.insert(eight);
		test.insert(nine);
		test.insert(ten);
		test.insert(eleven);
		test.insert(twelve);
		test.insert(thirteen);
		test.insert(fourteen);
		test.insert(fifteen);
		test.insert(sixteen);
		test.insert(seventeen);
		test.insert(eighteen);
		test.insert(nineteen);
		test.insert(twenty);
		
		test.print();
		System.out.println();
		
		System.out.print(test.get(2005));
		System.out.println();
		
		System.out.print(test.get(566));
		System.out.println();
		
		System.out.print(test.get(802));
		System.out.println();
		
		test.delete(566);
		test.delete(802);
		test.delete(401);
		test.delete(5545);
		test.delete(2345);
		
		System.out.println();
		test.print();
	}
}
