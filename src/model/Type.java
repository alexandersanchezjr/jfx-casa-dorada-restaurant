package model;

public class Type {
	private String name;
	private boolean availability;
	/**
	 * @param name
	 * @param availability
	 */
	public Type(String name, boolean availability) {
		this.name = name;
		this.availability = availability;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
}
