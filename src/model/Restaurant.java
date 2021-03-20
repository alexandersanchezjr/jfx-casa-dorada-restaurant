package model;

import java.util.ArrayList;

public class Restaurant {
	private ArrayList<Employee> employees;
	private ArrayList<Product> products;
	private ArrayList<Customer> customers;
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
	
	public boolean addProduct(String name, boolean availability, String selectedType, String size, String price, User creator) {
		boolean added = false;
		added = products.add(new Product(name,(identifier++), availability, selectedType, size, price, creator));
		return added;
	}
	
	public boolean updateProduct() {
		boolean added = false;
		return added;
	}
	
	public boolean deleteProduct() {
		boolean added = false;
		return added;
	}
	
	public boolean disableProduct() {
		boolean added = false;
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
	
}
