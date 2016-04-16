package com.jpmorgan.assignment;

import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class Stock {

	private String stockSymbol;
	private StockType type;
	private Double lastDividend;
	private Double fixedDividend;
	private Double parValue;
	private Map<Date, StockTrade> tradeRecord;

	/**
	 * @return the stockSymbol
	 */
	public String getStockSymbol() {
		return stockSymbol;
	}

	/**
	 * @param stockSymbol
	 *            the stockSymbol to set
	 */
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}


	/**
	 * @return the lastDividend
	 */
	public Double getLastDividend() {
		return lastDividend;
	}

	/**
	 * @param lastDividend the lastDividend to set
	 */
	public void setLastDividend(Double lastDividend) {
		this.lastDividend = lastDividend;
	}

	/**
	 * @return the fixedDividend
	 */
	public Double getFixedDividend() {
		return fixedDividend;
	}

	/**
	 * @param fixedDividend
	 *            the fixedDividend to set
	 */
	public void setFixedDividend(Double fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	/**
	 * @return the parValue
	 */
	public Double getParValue() {
		return parValue;
	}

	/**
	 * @param parValue
	 *            the parValue to set
	 */
	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	/**
	 * @return the trade
	 */
	public Map<Date, StockTrade> getTrade() {
		return tradeRecord;
	}

	/**
	 * @param trade
	 *            the trade to set
	 */
	public void setTrade(Map<Date, StockTrade> trade) {
		this.tradeRecord = trade;
	}

	public StockType getType() {
		return type;
	}

	public void setType(StockType type) {
		this.type = type;
	}

	public Stock(String stockSymbol, StockType type, Double lastDividend, Double fixedDividend, Double parValue) {

		this.setStockSymbol(stockSymbol);
		this.setType(type);
		this.setLastDividend (lastDividend);
		this.setFixedDividend(fixedDividend);
		this.setParValue(parValue);
		this.tradeRecord = new TreeMap<Date, StockTrade>();
	}

	

	@Override
	public String toString() {

		return "Stock [Symbol= " + stockSymbol + ", Stock Type = " + type + ", Last Dividend = " + lastDividend
				+ ", Fixed Dividend= " + fixedDividend + ", Par Value = " + parValue + "]";
	}

	public Double dividendYield(Double price) {

		if (this.getType() == StockType.Common) {
			return this.getLastDividend() / price;
		} else if (this.getType() == StockType.Preffered) {
			return (this.fixedDividend * this.parValue) / price;
		} else
			return 0.0;

	}

	public Double peRatio(Double price) {
		return price / this.getLastDividend();
	}

	
	public void buyStock(Integer quantityOfShares, Double price) {
		StockTrade trade = new StockTrade(TradeType.BUY, quantityOfShares, price);
		this.tradeRecord.put(new Date(), trade);
	}

	public void sellStock(Integer quantityOfShares, Double price) {
		StockTrade trade = new StockTrade(TradeType.SELL, quantityOfShares, price);
		this.tradeRecord.put(new Date(), trade);

	}

	public Double volumeWeightedStockPrice(){

		Date date = new Date();
		Date past15Minutes = new Date(date.getTime() - (15 * 60 * 1000));

		Double volumeWeightedStockPrice = 0.0;
		Integer totalQuantityOfShares = 0;

		SortedMap<Date, StockTrade> trades = ((TreeMap<Date,StockTrade>) this.tradeRecord).tailMap(past15Minutes);

		for (StockTrade trade : trades.values()) {

			totalQuantityOfShares += trade.getQuantityOfShares();

			volumeWeightedStockPrice += trade.getPrice() * trade.getQuantityOfShares();

		}
		return volumeWeightedStockPrice / totalQuantityOfShares;

	}

	public Double getPrice() {
		if (this.tradeRecord.size() > 0) {
			//return ((TreeMap<Date, StockTrade>) this.tradeRecord).lastEntry().getValue().getPrice();
			return ((StockTrade) this.tradeRecord).getPrice();
		} else
			return 0.0;

	}

}
