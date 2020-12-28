package com.wenance.dto;

import java.util.Date;

import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


public class BTCResponcePriceTimeDTO {
	
	@JsonProperty("Date")
	private String date;
	@JsonProperty("Price")
	private String price;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
