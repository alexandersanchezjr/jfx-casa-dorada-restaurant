package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1;
	private final String[] TYPES = {"Principal", "Adicional", "Bebida", "Otro"};
	private String name;
	private ArrayList<Ingredient> ingredients;
	private int[] pricesBySize;
	private boolean availability;
	private Type type;
	
	/**
	 * @param name
	 * @param availability
	 */
	public Product(String name, boolean availability, String selectedType) {  //selectedType = getValue del choiceBox
		this.name = name;
		ingredients = new ArrayList<Ingredient>();
		this.availability = availability;
		
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
}
