package model;

import java.io.Serializable;

public class Employee implements Serializable {
	
	private static final long serialVersionUID = 1;
	private String name;
	private String surname;
	private String id;
	
	/**
	 * @param name
	 * @param surname
	 * @param id
	 */
	public Employee(String name, String surname, String id) {
		super();
		this.name = name;
		this.surname = surname;
		this.id = id;
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
	
	
}
