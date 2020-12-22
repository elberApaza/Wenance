package com.wenance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wenance.dto.BTCResponceDTO;

@RestController
@RequestMapping(path = "/api")
public class WenanceController {
	
	
//	@GetMapping(path = "/obtenerBTC/${fecha}")
//	public BTCResponceDTO getObtenetBTC(String fecha) {
//		
//		BTCResponceDTO btcResponce = new BTCResponceDTO();
//		
//		
//		
//		return btcResponce;
//	}
}
