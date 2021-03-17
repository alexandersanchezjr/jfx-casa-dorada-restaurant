package model;

public class DetailProduct {
	private Product product;
	private int amount;
	
	/**
	 * @param product
	 * @param amount
	 */
	public DetailProduct(Product product, int amount) {
		super();
		this.product = product;
		this.amount = amount;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
