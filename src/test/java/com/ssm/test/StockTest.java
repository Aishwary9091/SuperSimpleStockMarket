package com.ssm.test;

import static org.junit.Assert.*;
//import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.Map;

import org.junit.Test;

import com.jpmorgan.assignment.Stock;
import com.jpmorgan.assignment.StockTrade;
import com.jpmorgan.assignment.StockType;
import com.jpmorgan.assignment.TradeType;

public class StockTest {

	@Test
	public void test() {

		Stock stockTestPOP = new Stock("POP", StockType.Common, 8.0, 0.0, 100.0);

		Double dividendYield = stockTestPOP.dividendYield(2.0);
		Double expectedDividendYield = stockTestPOP.getLastDividend() / 2.0;
		assertEquals(expectedDividendYield, dividendYield);

		Stock stockTestGIN = new Stock("GIN", StockType.Preffered, 8.0, 0.02, 100.0);
		Double dividendYieldGIN = stockTestGIN.dividendYield(2.0);
		Double expectedDividendYieldGIN = (stockTestGIN.getFixedDividend() * stockTestGIN.getParValue()) / 2.0;
		assertEquals(expectedDividendYieldGIN, dividendYieldGIN);

	}

	@Test
	public void peRatioTest() {

		Stock stockTestJOE = new Stock("JOE", StockType.Common, 13.0, 0.0, 250.0);
		Double peRatio = stockTestJOE.peRatio(26.0);
		Double expectedpeRatio = 26.0 / stockTestJOE.getLastDividend();
		assertEquals(expectedpeRatio, peRatio);
	}

	@Test
	public void buyTradeRecordTest() {

		
		Stock stockBuy = new Stock("POP", StockType.Common, 8.0, 0.0, 100.0);
		stockBuy.buyStock(2, 10.0);
		Map<Date, StockTrade> trade = stockBuy.getTrade();
		assertNotNull(trade);

	}
	@Test
	public void sellTradeRecordTest(){
		Stock stockSell = new Stock("POP", StockType.Common, 8.0, 0.0, 100.0);
		stockSell.sellStock(1, 5.0);
		Map<Date,StockTrade> trade = stockSell.getTrade();
		assertNotNull(trade);
		
	}
	@Test
	public void volumeWeightedStockPrice(){

		Stock stockPOP = new Stock("POP", StockType.Common, 8.0, 0.0, 100.0);
		stockPOP.buyStock(2, 10.0);
		stockPOP.sellStock(2, 10.0);		
		Double volumeWeightedStockPriceTest = stockPOP.volumeWeightedStockPrice();
		assertEquals(10.0, volumeWeightedStockPriceTest, 0.0);
		Date now = new Date();
		// Date 20 minutes ago
		Date startTime = new Date(now.getTime() - (20 * 60 * 1000));
		
		stockPOP.getTrade().put(startTime, new StockTrade(TradeType.BUY, 1, 20.0));
		
		volumeWeightedStockPriceTest = stockPOP.volumeWeightedStockPrice();
		assertEquals(10.0, volumeWeightedStockPriceTest, 0.0);
	}
	@Test
	public void testGetPrice() {
		Stock stockPOP = new Stock("POP", StockType.Common, 8.0, 0.0, 100.0);
		stockPOP.buyStock(1, 10.0);
		stockPOP.sellStock(1, 11.0);
		
		assertEquals(11.0, stockPOP.getPrice(), 0.0);
	}
	
}
