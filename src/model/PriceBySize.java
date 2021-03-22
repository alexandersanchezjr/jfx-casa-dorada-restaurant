package model;

public class PriceBySize {
	private String size;
	private int price;
	
	/**
	 * @param size
	 * @param price
	 */
	public PriceBySize(String size, int price) {
		this.size = size;
		this.price = price;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
