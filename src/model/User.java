package model;

public class User extends Employee {
	
	private static final long serialVersionUID = 1;
	private String username;
	private String password;
	private User creator;
	private User modifier;
	
	/**
	 * @param name
	 * @param surname
	 * @param id
	 * @param user
	 * @param password
	 */
	public User(String name, String surname, String id, User employeeCreator, String username, String password, User creator) {
		super(name, surname, id, employeeCreator);
		this.username = username;
		this.password = password;
		this.creator = creator;
		modifier = creator;
	}

	/**
	 * @return the user
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param user the user to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
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
