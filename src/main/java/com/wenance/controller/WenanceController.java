package com.wenance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wenance.dto.BTCResponceAvarageTimeDTO;
import com.wenance.dto.BTCResponcePriceTimeDTO;
import com.wenance.service.WenanceBTCService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WenanceController {
	
	private final WenanceBTCService wenanceBTCService;
	
//	1. Obtener el precio del bitcoin en cierto timestamp.
	@GetMapping("/btc/price/{date}")
	public BTCResponcePriceTimeDTO getObtenerBTC(@PathVariable final String date) {
		
		return wenanceBTCService.getBTCForTime(date);
	}
	
//	2. Conocer el promedio de valor entre dos timestamps así como la diferencia porcentual entre ese valor promedio 
//	y el valor máximo almacenado para toda la serie temporal disponible.
	@GetMapping("btc/avarage/{sDateFrom}/{sDateTo}")
	public BTCResponceAvarageTimeDTO getBTCAvarageTime(@PathVariable final String sDateFrom, @PathVariable final String sDateTo) {
		return wenanceBTCService.getBTCAvarageTime(sDateFrom, sDateTo);
	}
}
