package com.wenance.dto;

import java.util.Date;

import org.springframework.context.annotation.PropertySource;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;


public class BTCResponcePriceTimeDTO {
	

	@JsonProperty("date")
	private String date;
	
	@JsonProperty("price")
	private String price;
	
	@JsonProperty("curr1")
	private String curr1;
	
	@JsonProperty("curr2")
	private String curr2;
	
	
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
	public String getCurr1() {
		return curr1;
	}
	public void setCurr1(String curr1) {
		this.curr1 = curr1;
	}
	public String getCurr2() {
		return curr2;
	}
	public void setCurr2(String curr2) {
		this.curr2 = curr2;
	}
	
	
	
}
