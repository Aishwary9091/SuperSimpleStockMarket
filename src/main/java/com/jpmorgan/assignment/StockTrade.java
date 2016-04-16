package com.jpmorgan.assignment;

public class StockTrade  {

	private TradeType type;
	private Integer quantityOfShares;
	private Double price;

	/**
	 * @return the type
	 */
	public TradeType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(TradeType type) {
		this.type = type;
	}

	/**
	 * @return the quantityOfShares
	 */
	public Integer getQuantityOfShares() {
		return quantityOfShares;
	}

	/**
	 * @param quantityOfShares
	 *            the quantityOfShares to set
	 */
	public void setQuantityOfShares(Integer quantityOfShares) {
		this.quantityOfShares = quantityOfShares;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	public StockTrade(TradeType type, Integer quantityOfShares, Double price) {
		this.type = type;
		this.quantityOfShares = quantityOfShares;
		this.price = price;

	}

	
	@Override
	public String toString() {

		return "Stock trade values {Trade type = " + type + ", Quantity of Shares = " + quantityOfShares + ", Price ="
				+ price + "}";
	}

}
