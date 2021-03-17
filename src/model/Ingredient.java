package model;

public class Ingredient {
	private String name;
	private boolean availability;
	
	public Ingredient(String name, boolean availability) {
		this.setName(name);
		this.setAvailability(availability);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}
}
