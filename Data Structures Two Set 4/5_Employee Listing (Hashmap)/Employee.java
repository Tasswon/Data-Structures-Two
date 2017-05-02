/** Author: Joseph Tassone
 *  Description: Class for an employee object, that includes an ID key
 *  for comparing objects and first/last name.
 */

public class Employee implements Comparable<Employee>{
	
	private int ID;
	private String firstName;
	private String lastName;
	private String department;
	private int yearsOfService;
	
	//Person object constructor of ID, firstName, lastName
	public Employee(int ID, String firstName, String lastName, String department, int yearsOfService) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.yearsOfService = yearsOfService;
	}

	//Returns the ID for the object
	//Doesn't include a setter (shouldn't change)
	public int getID() {
		return ID;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getYearsOfService() {
		return yearsOfService;
	}

	public void setYearsOfService(int yearsOfService) {
		this.yearsOfService = yearsOfService;
	}

	//Overrides toString so that the object prints the appropriate structure
	//[ID: firstName lastName]
	public String toString(){
		return "[" + this.ID + ": " + firstName + " " + lastName + ": " + department + ": " + yearsOfService + "]";
	}
	
	//Overrides the compare to, so that the ID is the piece compared to for the object
	public int compareTo(Employee e) {
		return this.getID() - e.getID();
	}
}
