package com.springbatch.processor;

import org.springframework.batch.item.ItemProcessor;

import com.springbatch.model.Trade;

public class TradeProcessor implements ItemProcessor<Trade, Trade> {

	@Override
	public Trade process(Trade item) throws Exception {
		return item;
	}

	
}
