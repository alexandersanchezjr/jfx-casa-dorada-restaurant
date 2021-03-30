package model;

import java.io.Serializable;

public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1;
	private String name;
	private String surname;
	private String id;
	private int ordersCont;
	private int totalSum;
	private boolean availability;
	private User creator;
	private User modifier;
	
	/**
	 * @param name
	 * @param surname
	 * @param id
	 */
	public Employee(String name, String surname, String id, User creator) {
		this.name = name;
		this.surname = surname;
		this.id = id;
		setAvailability(true);
		this.creator = creator;
		setModifier(creator);
	}
	
	
	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the ordersCont
	 */
	public int getOrdersCont() {
		return ordersCont;
	}

	/**
	 * @param ordersCont the ordersCont to set
	 */
	public void setOrdersCont(int ordersCont) {
		this.ordersCont = ordersCont;
	}
	
	/**
	 * @return the totalSum
	 */
	public int getTotalSum() {
		return totalSum;
	}
	
	public void addOrderValue (int valueToAdd) {
		totalSum += valueToAdd;
	}


	/**
	 * @param totalSum the totalSumm to set
	 */
	public void setTotalSumm(int totalSum) {
		this.totalSum = totalSum;
	}


	/**
	 * @param ordersCont the ordersCont to set
	 */
	public void addOrder() {
		ordersCont++;
	}


	/**
	 * @return the availability
	 */
	public boolean isAvailability() {
		return availability;
	}


	/**
	 * @param availability the availability to set
	 */
	public void setAvailability(boolean availability) {
		this.availability = availability;
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
