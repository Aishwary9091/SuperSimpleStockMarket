package com.jpmorgan.assignment;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class SimpleStockMarketController {

	private static Log log = LogFactory.getLog(SimpleStockMarketController.class);

	@Bean
	Map<String, Stock> exchangeData() {

		LinkedHashMap<String, Stock> exchangeData = new LinkedHashMap<String, Stock>();
		exchangeData.put("TEA", new Stock("TEA", StockType.Common, 0.0, 0.0, 100.0));
		exchangeData.put("POP", new Stock("POP", StockType.Common, 8.0, 0.0, 100.0));
		exchangeData.put("ALE", new Stock("ALE", StockType.Common, 23.0, 0.0, 60.0));
		exchangeData.put("GIN", new Stock("GIN", StockType.Preffered, 8.0, 0.2, 100.0));
		exchangeData.put("JOE", new Stock("JOE", StockType.Common, 13.0, 0.0, 250.0));

		return exchangeData;

	}

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(SimpleStockMarketController.class);

		@SuppressWarnings("unchecked")
		Map<String, Stock> exchangeData = context.getBean("exchangeData", Map.class);

		for (Stock stock : exchangeData.values()) {
			log.debug(stock.getStockSymbol() + "Dividend Yield= " + stock.dividendYield(15.0));
			log.debug(stock.getStockSymbol() + "PE Ratio = " + stock.peRatio(10.0));

			// Record Trade
			for (int i = 0; i < 10; i++) {
				Random r = new Random();
				int rangeMin = 1;
				int rangeMax = 10;

				double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
				stock.buyStock(i, randomValue);
				log.debug(stock.getStockSymbol() + " Bought: " + i + " shares at " + randomValue + " Pounds.");

				randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
				stock.sellStock(i, randomValue);
				log.debug(stock.getStockSymbol() + "Sold: " + i + "shares at " + randomValue + "Pounds.");
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
			log.debug(stock.getStockSymbol() + "price : " + stock.getPrice());
			log.debug(stock.getStockSymbol() + "Volume Weighted Stock Price: " + stock.volumeWeightedStockPrice());

		}

		double gbceShareIndex = GBCEShareIndex.gbceAllShareIndex(exchangeData);
		log.debug("GBCE All Share Index  " + gbceShareIndex);

	}

}
