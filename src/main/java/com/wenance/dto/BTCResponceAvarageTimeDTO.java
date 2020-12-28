package com.wenance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BTCResponceAvarageTimeDTO {

	@JsonProperty("dateFrom")
	private String dateFrom;
	
	@JsonProperty("dateTo")
	private String dateTo;
	
	@JsonProperty("avarage")
	private String avarage;
	
	@JsonProperty("change")
	private String change;
	
	@JsonProperty("curr1")
	private String curr1;
	
	@JsonProperty("curr2")
	private String curr2;

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getAvarage() {
		return avarage;
	}

	public void setAvarage(String avarage) {
		this.avarage = avarage;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
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
