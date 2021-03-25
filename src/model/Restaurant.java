package model;

import java.util.ArrayList;

public class Restaurant {
	private ArrayList<Employee> employees;
	private ArrayList<User> operatorsUsers;
	private ArrayList<User> admins;
	private ArrayList<Product> products;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<Type> types;
	private ArrayList<Customer> customers;
	private ArrayList<Order> orders;
	private User loggedUser;
	private long identifier;
	
	public Restaurant() {
		employees = new ArrayList<Employee>();
		setOperatorsUsers(new ArrayList<User>());
		admins = new ArrayList<User>();
		products = new ArrayList<Product>();
		ingredients = new ArrayList<Ingredient>();
		types = new ArrayList<Type>();
		customers = new ArrayList<Customer>();
		orders = new ArrayList<Order>();
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
	 * @return the operatorsUsers
	 */
	public ArrayList<User> getOperatorsUsers() {
		return operatorsUsers;
	}

	/**
	 * @param operatorsUsers the operatorsUsers to set
	 */
	public void setOperatorsUsers(ArrayList<User> operatorsUsers) {
		this.operatorsUsers = operatorsUsers;
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

	public boolean addProduct(String name, ArrayList<Ingredient> ingredients, ArrayList<PriceBySize> pricesBySizes, boolean availability, String selectedType, boolean typeAvailability, User typeCreator, String size, String price) {
		boolean added = false;
		Product newProduct = new Product(name,(identifier++), ingredients, pricesBySizes, availability, selectedType, typeAvailability, typeCreator, size, price, loggedUser);
		if(!products.contains(newProduct)) {	
			added = products.add(newProduct);
		}
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
		for(int i = 0; i<orders.size(); i++) {
			if(!orders.get(i).getProducts().contains(p)) {
				deleted = products.remove(p);
			}
		}
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
		boolean found = false;
		for(int i = 0; i<orders.size() && !found; i++) {
			if(orders.get(i).getEmployee().equals(e)) {
				found = true;
			}
		}
		if(found == false) {
			deleted = employees.remove(e);
		}
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
		User thisUser = new User(name, surname, id, employeeCreator, username, password, loggedUser);
		if(!operatorsUsers.contains(thisUser)) { // If contains() generate error, change with a normal search with a for loop
			added = operatorsUsers.add(thisUser);
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
		deleted = operatorsUsers.remove(u);
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
		if(!admins.contains(thisAdmin)) { // If contains() generate error, change with a normal search with a for loop
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
	
	public boolean addIngredient(String name, boolean availability) {
		boolean added = false;
		Ingredient thisIngredient = new Ingredient(name, availability, (identifier++), loggedUser);
		if(!ingredients.contains(thisIngredient)) {	
			added = ingredients.add(thisIngredient);
		}
		return added;
	}
	
	public boolean deleteIngredient(Ingredient ingredient) {
		boolean deleted = false;
		for(int i = 0; i<products.size(); i++) {
			if(!products.get(i).getIngredients().contains(ingredient)) {
				deleted = ingredients.remove(ingredient);
			}
		}
		return deleted;
	}
	
	public void updateIngredient(Ingredient i, String name) {
		i.setName(name);
		i.setModifier(loggedUser);
	}
	
	public boolean enableIngredient(Ingredient ingredient) {
		boolean enabled = false;
		if(!ingredient.isAvailability()) {
			ingredient.setAvailability(true);
			enabled = true;
		}
		return enabled;
	}
	
	public boolean disableIngredient(Ingredient ingredient) {
		boolean disabled = false;
		if(ingredient.isAvailability()) {
			ingredient.setAvailability(false);
			disabled = true;
		}
		return disabled;
	}
	
	//Order MANAGEMENT
	
	//Customer MANAGEMENT
	public boolean addCustomer(String name, String surname, String id, String address, String phoneNumber, String comments) {
		boolean added = false;
		Customer thisCustomer = new Customer(name, surname, id, address, phoneNumber, comments, loggedUser);
		if(!customers.contains(thisCustomer)) {
			added = customers.add(thisCustomer);
		}
		return added;
	}
	
	public boolean deleteCustomer(Customer c) {
		boolean deleted = false;
		for(int i = 0; i<orders.size(); i++) {
			if((!orders.get(i).getStatus().equalsIgnoreCase("CANCELADO")) && (!orders.get(i).getCustomer().equals(c))) {
				deleted = customers.remove(c);
			}
		}
		return deleted;
	}
	
	public void updateCustomer(Customer c, String name, String surname, String id, String address, String phoneNumber, String comments) {
		c.setName(name);
		c.setSurname(surname);
		c.setId(id);
		c.setAddress(address);
		c.setPhoneNumber(phoneNumber);
		c.setComments(comments);
		c.setModifier(loggedUser);
	}
	
	public boolean enableCustomer(Customer c) {
		boolean enabled = false;
		if(!c.isAvailability()) {
			c.setAvailability(true);
			enabled = true;
		}
		return enabled;
	}
	
	public boolean disableCustomer(Customer c) {
		boolean disabled = false;
		if(c.isAvailability()) {
			c.setAvailability(false);
			disabled = true;
		}
		return disabled;
	}
}
