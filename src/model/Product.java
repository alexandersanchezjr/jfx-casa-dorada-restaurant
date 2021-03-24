package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
	
	private static final long serialVersionUID = 1;
	private String name;
	private long id;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<PriceBySize> pricesBySizes;
	private boolean availability;
	private Type type;
	private User creator;
	private User modifier;
	
	/**
	 * @param name
	 * @param availability
	 */
	public Product(String name, long id, ArrayList<Ingredient> ingredients, ArrayList<PriceBySize> pricesBySizes, boolean availability, String selectedType, boolean typeAvailability, User typeCreator, String size, String price, User creator) {  //selectedType = getValue del choiceBox
		this.name = name;
		this.id = id;
		this.ingredients = ingredients;
		pricesBySizes = new ArrayList<PriceBySize>();
		this.availability = availability;
		type = new Type(selectedType, typeAvailability, typeCreator);
		this.creator = creator;
		modifier = creator;
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
	
	public boolean addIngredient(Ingredient i) {
		boolean added = false;
		added = ingredients.add(i);
		return added;
	}
	
	public boolean deleteIngredient(Ingredient i) {
		boolean deleted = false;
		deleted = ingredients.remove(i);
		return deleted;
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
	public void setType(String newType) {
		type.setName(newType);
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

	/**
	 * @return the pricesBySizes
	 */
	public ArrayList<PriceBySize> getPricesBySizes() {
		return pricesBySizes;
	}
	
	public boolean addPriceBySize(PriceBySize pbs) {
		boolean added = false;
		added = pricesBySizes.add(pbs);
		return added;
	}
	
	public boolean deletePriceBySize(PriceBySize pbs) {
		boolean deleted = false;
		deleted = pricesBySizes.remove(pbs);
		return deleted;
	}
}
