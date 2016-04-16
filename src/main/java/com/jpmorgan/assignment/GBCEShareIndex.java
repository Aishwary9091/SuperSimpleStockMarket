package com.jpmorgan.assignment;

import java.util.Map;

public class GBCEShareIndex {

	public static Double gbceAllShareIndex(Map<String, Stock> stocks) {

		Double gbceAllShareIndex = 0.0;

		for (Stock stock : stocks.values()) {

			gbceAllShareIndex = gbceAllShareIndex + stock.getPrice();

		}

		return Math.pow(gbceAllShareIndex, 1.0 / stocks.size());

	}

}
