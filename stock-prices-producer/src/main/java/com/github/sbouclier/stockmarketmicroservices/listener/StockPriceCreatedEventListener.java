package com.github.sbouclier.stockmarketmicroservices.listener;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.github.sbouclier.stockmarketmicroservices.domain.StockPrice;
import com.github.sbouclier.stockmarketmicroservices.event.StockPriceCreatedEvent;

@Component
public class StockPriceCreatedEventListener {

    private static final Logger LOG = LoggerFactory.getLogger(StockPriceCreatedEventListener.class);

    private final AtomicInteger counter = new AtomicInteger(0);
    private StockPrice lastPrice;

    public int getValue() {
        return counter.get();
    }

    public StockPrice getLastPrice() {
        return lastPrice;
    }

    @Async
    @EventListener
    public void onApplicationEvent(StockPriceCreatedEvent stockPriceCreatedEvent) {
        LOG.debug("Received StockPriceCreatedEvent : {}", stockPriceCreatedEvent);
        counter.incrementAndGet();
        lastPrice = stockPriceCreatedEvent.getStockPrice();
    }
}
