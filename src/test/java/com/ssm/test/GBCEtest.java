package com.ssm.test;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.Test;

import com.jpmorgan.assignment.GBCEShareIndex;
import com.jpmorgan.assignment.Stock;
import com.jpmorgan.assignment.StockType;

public class GBCEtest {

	@Test
	public void test() {

		LinkedHashMap<String, Stock> exchangeDataTest = new LinkedHashMap<String, Stock>();
		exchangeDataTest.put("TEA", new Stock("TEA", StockType.Common, 0.0, 0.0, 100.0));
		exchangeDataTest.put("POP", new Stock("POP", StockType.Common, 8.0, 0.0, 100.0));
		exchangeDataTest.put("ALE", new Stock("ALE", StockType.Common, 23.0, 0.0, 60.0));
		exchangeDataTest.put("GIN", new Stock("GIN", StockType.Preffered, 8.0, 0.2, 100.0));
		exchangeDataTest.put("JOE", new Stock("JOE", StockType.Common, 13.0, 0.0, 250.0));
		for (Stock testStock : exchangeDataTest.values()) {
			// Record some trades
			for (int i = 1; i <= 10; i++) {

				testStock.buyStock(1, 1.0);
				testStock.sellStock(1, 1.0);

			}
		}
		Double testGBCEallShareIndex = GBCEShareIndex.gbceAllShareIndex(exchangeDataTest);
		assertEquals(1.379729661461215, testGBCEallShareIndex, 0.0);
	}
}
