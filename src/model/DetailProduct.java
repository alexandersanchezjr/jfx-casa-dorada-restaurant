package model;

public class DetailProduct {
	private Product product;
	private int amount;
	private PriceBySize selectedSize;
	
	/**
	 * @param product
	 * @param amount
	 */
	public DetailProduct(Product product, int amount, PriceBySize selectedSize) {
		super();
		this.product = product;
		this.amount = amount;
		this.selectedSize = selectedSize;
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
	
	public String getCategory () {
		return product.getType().getName();
	}

	/**
	 * @return the selectedSize
	 */
	public PriceBySize getSelectedSize() {
		return selectedSize;
	}

	/**
	 * @param selectedSize the selectedSize to set
	 */
	public void setSelectedSize(PriceBySize selectedSize) {
		this.selectedSize = selectedSize;
	}
	
	public String getProductName () {
		return product.getName();
	}
	
	public String getSize () {
		return selectedSize.getSize();
	}
	
	public String getTotalPrice () {
		return Integer.toString(amount * selectedSize.getPrice());
	}
	
	public String getAmountToString () {
		return Integer.toString(amount);
	}
	
}
