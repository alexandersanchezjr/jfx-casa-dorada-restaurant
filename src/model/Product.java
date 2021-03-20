package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1;
	private String name;
	private long id;
	private ArrayList<Ingredient> ingredients;
	private String[] priceBySize;
	private boolean availability;
	private Type type;
	private User creator;
	private User modifier;
	
	/**
	 * @param name
	 * @param availability
	 */
	public Product(String name, long id, boolean availability, String selectedType, String size, String price, User creator) {  //selectedType = getValue del choiceBox
		this.name = name;
		this.id = id;
		ingredients = new ArrayList<Ingredient>();
		priceBySize = new String[]{size, price};
		this.availability = availability;
		type = new Type(selectedType);
		this.creator = creator;
		setModifier(creator);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public boolean isAvailability() {
		return availability;
	}
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
	/**
	 * @return the type
	 */
	public Type getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}
	/**
	 * @return the priceBySize
	 */
	public String[] getPriceBySize() {
		return priceBySize;
	}
	public void setPriceBySize(String size, String price) {
		priceBySize[0] = size;
		priceBySize[1] = price;
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
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
}
