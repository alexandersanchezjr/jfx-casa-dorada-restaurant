package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable {
	
	private static final long serialVersionUID = 1;
	private long id;
	private Date date;
	private Status status;
	private ArrayList<Product> products;
	private Employee employee; //Employee delivering the order 
	private Customer customer;
	private String comments;
	private User creator;
	private User modifier;
	
	/**
	 * @param id
	 * @param date
	 */
	public Order(long id, Date date, String selectedStatus, Employee e, String customerName, String customerSurname, String customerId, String customerAddress, String customerPhoneNumber, String comments, User customerCreator, String customerComments, User creator) {
		this.id = id;
		this.date = date;
		status = (Status.valueOf(selectedStatus));
		products = new ArrayList<Product>();
		employee = e;
		customer = new Customer(customerName, customerSurname, customerId, customerAddress, customerPhoneNumber, customerComments, customerCreator);
		this.comments = comments;
		this.creator = creator;
		modifier = creator;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	public ArrayList<Product> getProducts() {
		return products;
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
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
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
	
}
