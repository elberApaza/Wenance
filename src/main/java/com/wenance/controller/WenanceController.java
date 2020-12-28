package com.wenance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wenance.dto.BTCResponcePriceTimeDTO;
import com.wenance.service.WenanceBTCService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WenanceController {
	
	private final WenanceBTCService wenanceBTCService;
	

	@GetMapping("/btc/price/{date}")
	public BTCResponcePriceTimeDTO getObtenerBTC(@PathVariable final String date) {
		
		return wenanceBTCService.getBTCForTime(date);
	}
	
//	@GetMapping("/obtenerBTC/${dateString}")
//	public BTCResponcePriceTimeDTO getObtenetBTC(@PathVariable(value="dateString") String dateString) {
//		
//		return wenanceBTCService.getBTCForTime(dateString);
//		
//	}
	
}
