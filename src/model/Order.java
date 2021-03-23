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
	private Employee employee;
	private Customer customer;
	private String comments;
	
	/**
	 * @param id
	 * @param date
	 */
	public Order(long id, Date date, String selectedStatus, String employeeName, String employeeSurname, String employeeId, User employeeCreator, String customerName, String customerSurname, String customerId, String customerAddress, String customerPhoneNumber, String comments) {
		this.id = id;
		this.date = date;
		status = (Status.valueOf(selectedStatus));
		products = new ArrayList<Product>();
		employee = new Employee(employeeName, employeeSurname, employeeId, employeeCreator);
		customer = new Customer(customerName, customerSurname, customerId, customerAddress, customerPhoneNumber);
		this.comments = comments;
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
	
}
