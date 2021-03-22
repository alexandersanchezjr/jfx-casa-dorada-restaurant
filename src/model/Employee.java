package model;

import java.io.Serializable;

public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1;
	private String name;
	private String surname;
	private String id;
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
