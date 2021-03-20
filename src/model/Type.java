package model;

public class Type {
	private String name;
	/**
	 * @param name
	 * @param availability
	 */
	public Type(String name) {
		this.name = name;
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
}
