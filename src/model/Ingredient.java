package model;

public class Ingredient {
	private String name;
	private long id;
	private User creator;
	private User modifier;
	
	public Ingredient(String name, long id, User creator) {
		this.setName(name);
		this.setId(id);
		this.creator = creator;
		setModifier(creator);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	 * @return the creator
	 */
	public User getCreator() {
		return creator;
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
