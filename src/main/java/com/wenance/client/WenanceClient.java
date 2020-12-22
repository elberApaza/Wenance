package com.wenance.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wenance.configuration.ClientConfiguration;


@FeignClient(name = "BTCWenance", url = "${wenance-url}", configuration = ClientConfiguration.class)
public interface WenanceClient {
	
	@GetMapping("/last_price/BTC/USD")
    String getLastPriceBTCUSD();
}
