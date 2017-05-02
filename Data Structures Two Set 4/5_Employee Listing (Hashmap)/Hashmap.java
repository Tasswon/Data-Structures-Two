/** Author: Joseph Tassone
 *  Description: Creates a hashmap for employee objects,
 *  that allows objects to be added an manipulated.
 */

public class Hashmap {
	
	private Employee item;
	private int mode;
	private int MAX_VALUE = 401;
	private Employee [] table;
	private int size;
	
	//Constructor for hashmap that allows a mode to be selected
	//Throws exception if an invalid mode is selected
	public Hashmap (int mode) throws RuntimeException{
		if(mode < 0 || mode > 2) {
			throw new RuntimeException("Invalid mode (0 - 2)!");
		}
		table = new Employee [MAX_VALUE];
		this.mode = mode;
		size = 0;
	}
	
	//Constructor for hashmap that allows a mode to be selected and a size 
	//Throws exception if an invalid mode is selected
	public Hashmap (int size, int mode) throws RuntimeException{
		if(mode < 0 || mode > 2) {
			throw new RuntimeException("Invalid mode (0 - 2)!");
		}
		table = new Employee [size];
		this.mode = mode;
		size = 0;
	}
	
	//Inserts a value to a specific spot in the map based on the hashed employee ID
	//Handles collisions based on the mode selected during construction
	//If the map is full then throws an exception
	public void insert (Employee item) throws RuntimeException{
		if(size == MAX_VALUE) {
			throw new RuntimeException("The map is full!");
		}
		int ID = item.getID();
		int key = hKey(ID);
		
		//Uses linear probing to handle collisions based on the insert
		//Progresses until it finds a valid location (deleted or empty)
		if(mode == 0) {
			while(true) {
				//Goes to the start of the map if we've hit the end
				if(key >= table.length - 1) {
					key = key % MAX_VALUE;
				}
				if(table[key] == null || table[key].getID() == 999) {
					table[key] = item;
					size++;
					return;
				}
				key++;
			}
		}
		
		//Uses quadratic probing to handle collisions based on the insert
		//Progresses until it finds a valid location (deleted or empty)
		else if (mode == 1) {
			int quadratic = 1;
			while(true) {
				//Goes to the start of the map if we've hit the end
				if(key >= table.length - 1) {
					key = key % MAX_VALUE;
				}
				if(table[key] == null || table[key].getID() == 999) {
					table[key] = item;
					size++;
					return;
				}
				key = key + (int) Math.pow(quadratic, 2);
				quadratic++;
			}
		}
		
		//Uses double hashing to handle collisions based on the insert
		//Progresses until it finds a valid location (deleted or empty)
		else {
			if(table[key] == null) {
				table[key] = item;
				size++;
				return;
			}
			int key2 = ID % 97;
			while(true) {
				//Goes to the start of the map if we've hit the end
				if(key >= table.length - 1) {
					key = key % MAX_VALUE;
				}
				if(table[key] == null || table[key].getID() == 999) {
					table[key] = item;
					size++;
					return;
				}
				key = key + key2;
			}
		}
	}
	
	//Deletes a value from a specific spot in the map based on the hashed employee ID
	//User enters an ID number which is hashed and then compared with the hashed ID in the map
	public void delete (int item) {
		int key = hKey(item);
		
		//Uses linear probing to see where the item ended up after collisions
		//Progresses until it finds the item
		//Sets the deleted item to a blank employee with 999 as the ID
		if(mode == 0) {
			while(true) {
				//Goes to the start of the map if we've hit the end
				if(key == table.length - 1) {
					key = key % MAX_VALUE;
				}
				if(table[key].getID() == item) {
					table[key] = new Employee(999, null, null, null, 0);
					size--;
					return;
				}
				key++;
			}
		}
		
		//Uses quadratic probing to see where the item ended up after collisions
		//Progresses until it finds the item
		//Sets the deleted item to a blank employee with 999 as the ID
		else if (mode == 1) {
			int quadratic = 1;
			while(true) {
				//Goes to the start of the map if we've hit the end
				if(key >= table.length - 1) {
					key = key % MAX_VALUE;
				}
				if(table[key].getID() == item) {
					table[key] = new Employee(999, null, null, null, 0);;
					size--;
					return;
				}
				key = key + (int) Math.pow(quadratic, 2);
				quadratic++;
			}
		}
		
		//Uses double hashing to see where the item ended up after collisions
		//Progresses until it finds the item
		//Sets the deleted item to a blank employee with 999 as the ID
		else {
			if(table[key] == null) {
				table[key] = new Employee(999, null, null, null, 0);;
				size--;
				return;
			}
			int key2 = item % 97;
			while(true) {
				//Goes to the start of the map if we've hit the end
				if(key >= table.length - 1) {
					key = key % MAX_VALUE;
				}
				if(table[key].getID() == item) {
					table[key] = new Employee(999, null, null, null, 0);
					size--;
					return;
				}
				key = key + key2;
			}
		}
	}
	
	//Gets the employee information from a specific spot in the map based on the hashed employee ID
	//User enters an ID number which is hashed and then compared with the hashed ID in the map
	public Employee get (int item) {
		int key = hKey(item);
		
		//Uses linear probing to see where the item ended up after collisions
		//Progresses until it finds the item
		if(mode == 0) {
			while(true) {
				//Goes to the start of the map if we've hit the end
				if(key == table.length - 1) {
					key = key % MAX_VALUE;
				}
				if(table[key].getID() == item) {
					return table[key];
				}
				key++;
			}
		}
		
		//Uses quadratic probing to see where the item ended up after collisions
		//Progresses until it finds the item
		else if (mode == 1) {
			int quadratic = 1;
			while(true) {
				//Goes to the start of the map if we've hit the end
				if(key >= table.length - 1) {
					key = key % MAX_VALUE;
				}
				if(table[key].getID() == item) {
					return table[key];
				}
				key = key + (int) Math.pow(quadratic, 2);
				quadratic++;
			}
		}
		
		//Uses double hashing to see where the item ended up after collisions
		//Progresses until it finds the item
		else {
			int key2 = item % 97;
			while(true) {
				//Goes to the start of the map if we've hit the end
				if(key >= table.length - 1) {
					key = key % MAX_VALUE;
				}
				if(table[key].getID() == item) {
					return table[key];
				}
				key = key + key2;
			}
		}
	}
	
	//Hashes the entered ID by doing a modulus with the size of the array
	private int hKey(int temp) {
		int k = temp % MAX_VALUE;
		return k;
	}
	
	//Prints out the employees from the hash map
	//Only displays deleted and filled spots
	public void print() {
		for(int i = 0; i < table.length; i++) {
			if(table[i] != null) {
				System.out.println(i + ": " + table[i] + ", ");
			}
		}
	}
}