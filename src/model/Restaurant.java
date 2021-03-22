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
	
	public boolean addProduct(String name, ArrayList<Ingredient> ingredients, ArrayList<PriceBySize> pricesBySizes, boolean availability, String selectedType, String size, String price, User creator) {
		boolean added = false;
		added = products.add(new Product(name,(identifier++), ingredients, pricesBySizes, availability, selectedType, size, price, creator));
		return added;
	}
	
	public void updateProduct(Product p, String name, boolean availability, String selectedType, String size, String price) {
		p.setName(name);
		p.setAvailability(availability);
		p.setType(selectedType);
		p.setModifier(loggedUser);
	}
	
	public boolean deleteProduct(Product p) {
		boolean deleted = false;
		deleted = products.remove(p);
		return deleted;
	}
	
	public boolean disableProduct(Product p) {
		boolean added = false;
		p.setAvailability(false);
		return added;
	}
	
	public boolean addEmployee(String name, String surname, String id) {
		boolean added = false;
		Employee thisEmployee = new Employee(name, surname,id);
		if(!employees.contains(thisEmployee)) {
			added = employees.add(thisEmployee);
		}
		return added;
	}
	
	public boolean updateEmployee() {
		boolean added = false;
		return added;
	}
	
	public boolean deleteEmployee() {
		boolean added = false;
		return added;
	}
	
	public boolean addEmployee(String name, String surname, String id, String user, String password) {
		boolean added = false;
		Employee thisUser = new User(name, surname, id, user, password);
		if(!employees.contains(thisUser)) {
			added = employees.add(thisUser);
		}
		return added;
	}
	
	public boolean updateUser() {
		boolean added = false;
		return added;
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
	
}
