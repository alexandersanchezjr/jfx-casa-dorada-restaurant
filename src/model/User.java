package model;

public class User extends Employee {
	
	private static final long serialVersionUID = 1;
	private String user;
	private String password;
	
	/**
	 * @param name
	 * @param surname
	 * @param id
	 * @param user
	 * @param password
	 */
	public User(String name, String surname, String id, String user, String password) {
		super(name, surname, id);
		this.user = user;
		this.password = password;	
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
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

}
