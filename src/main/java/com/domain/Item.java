package com.domain;

public class Item {

	private final int quantity;
	
	private final String itemDescription;
	
	private final double price;
	
	private final boolean isExcluded;
	
	private final boolean isImported;
	
	private double importTax;
	
	private double salesTax;
	
	public Item(int qty, String desc, double price, boolean isExcluded, boolean isImported) {
		this.quantity = qty;
		this.itemDescription = desc;
		this.price = price;
		this.isExcluded = isExcluded;
		this.isImported = isImported;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public double getPrice() {
		return price;
	}

	public boolean isExcluded() {
		return isExcluded;
	}

	public boolean isImported() {
		return isImported;
	}

	public double getTotalTax(){
		return this.salesTax + this.importTax;
	}
	
	public double getImportTax() {
		return importTax;
	}

	public double getSalesTax() {
		return salesTax;
	}

	public void setImportTax(double importTax) {
		this.importTax = importTax;
	}

	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isExcluded ? 1231 : 1237);
		result = prime * result + (isImported ? 1231 : 1237);
		result = prime * result
				+ ((itemDescription == null) ? 0 : itemDescription.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (isExcluded != other.isExcluded)
			return false;
		if (isImported != other.isImported)
			return false;
		if (itemDescription == null) {
			if (other.itemDescription != null)
				return false;
		} else if (!itemDescription.equals(other.itemDescription))
			return false;
		if (Double.doubleToLongBits(price) != Double
				.doubleToLongBits(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return quantity + "" + itemDescription +": "+  price;
	}
}
