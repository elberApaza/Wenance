package com.wenance.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "wenanceBTC", url = "${wenance-url}")
public interface WenanceClient {
	
	@GetMapping("api/last_price/BTC/USD")
	//@RequestMapping(method = RequestMethod.GET, value = "/last_price/BTC/USD")
    String getLastPriceBTCUSD();
}
