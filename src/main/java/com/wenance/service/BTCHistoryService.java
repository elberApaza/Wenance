package com.wenance.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wenance.client.WenanceClient;
import com.wenance.pojo.BTCData;

import lombok.AllArgsConstructor;



@Service
@AllArgsConstructor
public class BTCHistoryService {
	private WenanceClient wenanceClient;
	
	private final List<BTCData> historyList = new ArrayList<BTCData>();
	
	public void getBTC() {
		String btcDataString = wenanceClient.getLastPriceBTCUSD();
		
		System.out.println(btcDataString);
	}

	public void addHistory(BTCData btcData) {
		synchronized (historyList) {
			historyList.add(btcData);
		}
	}

	public double averageBTC(Date dateFrom, Date dateTo) {
		synchronized (historyList) {
			return historyList.stream().filter((btcData) -> this.btcFilter(dateFrom, dateTo, btcData))
					.mapToDouble(this::convertToDoubleBTC).average().getAsDouble();
		}
	}

	public double getValueBTC(Date date) {
		synchronized (historyList) {
			return historyList.stream().filter((btcData) -> this.btcFilter(date, btcData))
					.mapToDouble(this::convertToDoubleBTC).findFirst().getAsDouble();
		}
	}

	public Double maxValueBTC() {
		synchronized (historyList) {			
			return historyList.stream().mapToDouble(this::convertToDoubleBTC).max().getAsDouble();
		}
	}

	private double convertToDoubleBTC(BTCData btcData) {
		return Double.valueOf(btcData.getLprice());
	}

	private boolean btcFilter(Date dateFrom, Date dateTo, BTCData data) {
		return data.getDateRequest().after(dateFrom) && data.getDateRequest().before(dateTo);
	}

	private boolean btcFilter(Date date, BTCData data) {
		Date dateFrom = new Date(date.getTime() - 5000L);
		Date dateTo = new Date(date.getTime() + 5000L);

		return data.getDateRequest().after(dateFrom) && data.getDateRequest().before(dateTo);

	}

}
