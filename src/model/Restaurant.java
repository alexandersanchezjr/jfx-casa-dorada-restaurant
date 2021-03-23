package model;

import java.util.ArrayList;

public class Restaurant {
	private ArrayList<Employee> employees;
	private ArrayList<Product> products;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Type> types;
	private ArrayList<Customer> customers;
	private ArrayList<User> admins;
	private User loggedUser;
	private long identifier;
	
	public Restaurant() {
		employees = new ArrayList<Employee>();
		products = new ArrayList<Product>();
		customers = new ArrayList<Customer>();
		setLoggedUser(null);
		identifier = 0;
	}

	/**
	 * @return the employees
	 */
	public ArrayList<Employee> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(ArrayList<Employee> employees) {
		this.employees = employees;
	}

	/**
	 * @return the products
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}

	/**
	 * @param products the products to set
	 */
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	/**
	 * @return the customers
	 */
	public ArrayList<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param customers the customers to set
	 */
	public void setCustomers(ArrayList<Customer> customers) {
		this.customers = customers;
	}
	
	/**
	 * @return the admins
	 */
	public ArrayList<User> getAdmins() {
		return admins;
	}

	/**
	 * @return the ingredients
	 */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	/**
	 * @param ingredients the ingredients to set
	 */
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	/**
	 * @return the types
	 */
	public ArrayList<Type> getTypes() {
		return types;
	}

	/**
	 * @param types the types to set
	 */
	public void setTypes(ArrayList<Type> types) {
		this.types = types;
	}
	
	/**
	 * @return the loggedUser
	 */
	public User getLoggedUser() {
		return loggedUser;
	}

	/**
	 * @param loggedUser the loggedUser to set
	 */
	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	//Product MANAGEMENT

	public boolean addProduct(String name, ArrayList<Ingredient> ingredients, ArrayList<PriceBySize> pricesBySizes, boolean availability, String selectedType, String size, String price) {
		boolean added = false;
		added = products.add(new Product(name,(identifier++), ingredients, pricesBySizes, availability, selectedType, size, price, loggedUser));
		return added;
	}
	
	public void updateProduct(Product p, String name, boolean availability, String selectedType, ArrayList<Ingredient> ingredients) {
		p.setName(name);
		p.setAvailability(availability);
		p.setType(selectedType);
		p.setModifier(loggedUser);
		p.setIngredients(ingredients);
	}
	
	public boolean deleteProduct(Product p) {
		boolean deleted = false;
		deleted = products.remove(p);
		return deleted;
	}
	
	public boolean disableProduct(Product p) {
		boolean disabled = false;
		if(p.isAvailability()) {
			p.setAvailability(false);
			disabled = true;
		}
		return disabled;
	}
	
	public boolean enableProduct(Product p) {
		boolean disabled = false;
		if(!p.isAvailability()) {
			p.setAvailability(true);
			disabled = true;
		}
		return disabled;
	}
	
	//Employee MANAGEMENT
	
	public boolean addEmployee(String name, String surname, String id, User creator) {
		boolean added = false;
		Employee thisEmployee = new Employee(name, surname,id, loggedUser);
		if(!employees.contains(thisEmployee)) {
			added = employees.add(thisEmployee);
		}
		return added;
	}
	
	public void updateEmployee(Employee e, String name, String surname, String id) {
		e.setName(name);
		e.setSurname(surname);
		e.setId(id);
		e.setModifier(loggedUser);
	}
	
	public boolean deleteEmployee(Employee e) {
		boolean deleted = false;
		deleted = employees.remove(e);
		return deleted;
	}
	
	public boolean enableEmployee(Employee e) {
		boolean disabled = false;
		if(!e.isAvailability()) {
			e.setAvailability(true);
			disabled = true;
		}
		return disabled;
	}
	
	public boolean disableEmployee(Employee e) {
		boolean disabled = false;
		if(e.isAvailability()) {
			e.setAvailability(false);
			disabled = true;
		}
		return disabled;
	}
	
	//User MANAGEMENT
	
	public boolean addUser(String name, String surname, String id, User employeeCreator, String username, String password, User creator) {
		boolean added = false;
		Employee thisUser = new User(name, surname, id, employeeCreator, username, password, loggedUser);
		if(!employees.contains(thisUser)) { // If contains() generate error, change with a normal search with a for loop
			added = employees.add(thisUser);
		}
		return added;
	}
	
	public void updateUser(User u, String username, String password) {
		u.setUsername(username);
		u.setPassword(password);
		u.setModifier(loggedUser);
	}
	
	public boolean deleteUser(User u) {
		boolean deleted = false;
		deleted = employees.remove(u);
		return deleted;
	}
	
	public boolean enableUser(User u) {
		boolean disabled = false;
		if(!u.isAvailability()) {
			u.setAvailability(true);
			disabled = true;
		}
		return disabled;
	}
	
	public boolean disableUser(User u) {
		boolean disabled = false;
		if(u.isAvailability()) {
			u.setAvailability(false);
			disabled = true;
		}
		return disabled;
	}
	
	// AdminUser MANAGEMENT
	
	public boolean addAdminUser(String name, String surname, String id, User employeeCreator, String username, String password, User creator) {
		boolean added = false;
		User thisAdmin = new User(name, surname, id, employeeCreator, username, password, loggedUser);
		if(!employees.contains(thisAdmin)) { // If contains() generate error, change with a normal search with a for loop
			added = admins.add(thisAdmin);
		}
		return added;
	}
	
	public boolean deletedAdminUser(User u) {
		boolean deleted = false;
		deleted = admins.remove(u);
		return deleted;
	}
	
	//Ingredients MANAGEMENT
	
	public boolean addIngredient(String name) {
		boolean added = false;
		Ingredient thisIngredient = new Ingredient(name, (identifier++), loggedUser);
		if(!ingredients.contains(thisIngredient)) {	
			added = ingredients.add(thisIngredient);
		}
		return added;
	}
	
	
}
