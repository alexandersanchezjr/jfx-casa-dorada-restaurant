package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1;
	private String name;
	private ArrayList<Ingredient> ingredients;
	private int[] pricesBySize;
	private boolean availability;
	private Type type;
	
	/**
	 * @param name
	 * @param availability
	 */
	public Product(String name, boolean availability, String selectedType, boolean availabilityType) {  //selectedType = getValue del choiceBox
		this.name = name;
		ingredients = new ArrayList<Ingredient>();
		this.availability = availability;
		type = new Type(selectedType, availabilityType);
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
	public int[] getPricesBySize() {
		return pricesBySize;
	}
	public void setPricesBySize(int[] pricesBySize) {
		this.pricesBySize = pricesBySize;
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
}
