package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 1;
	private String id;
	private Date date;
	private Status status;
	private ArrayList<DetailProduct> products;
	private Employee employee; //Employee delivering the order 
	private Customer customer;
	private String comments;
	private User creator;
	private User modifier;
	
	/**
	 * @param id
	 * @param date
	 */
	public Order(long id, String selectedStatus, ArrayList<DetailProduct> products, Employee e, String customerName, String customerSurname, String customerAddress, String customerPhoneNumber, String comments, User customerCreator, String customerComments, User creator) {
		this.id = "#"+String.valueOf(id);
		date = new Date();
		status = (Status.valueOf(selectedStatus));
		this.products = products;
		employee = e;
		customer = new Customer(customerName, customerSurname, customerAddress, customerPhoneNumber, customerComments, customerCreator);
		this.comments = comments;
		this.creator = creator;
		modifier = creator;
		
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = "#"+String.valueOf(id);
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return products
	 */
	public ArrayList<DetailProduct> getProducts() {
		return products;
	}
	
	/**
	 * @param products the products to set
	 */
	public void setProducts(ArrayList<DetailProduct> products) {
		this.products = products;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status.toString();
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = Status.valueOf(status);
	}

	/**
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(User creator) {
		this.creator = creator;
	}

	/**
	 * @return the modifier
	 */
	public User getModifier() {
		return modifier;
	}

	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(User modifier) {
		this.modifier = modifier;
	}
	
	public int getTotal() {
		int total = 0;
		for(int i = 0; i<products.size(); i++) {
			total += products.get(i).getAmount()*products.get(i).getSelectedSize().getPrice();
		}
		return total;
	}
}
