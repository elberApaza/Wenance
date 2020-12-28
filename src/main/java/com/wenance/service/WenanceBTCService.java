package com.wenance.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.wenance.dto.BTCResponcePriceTimeDTO;
import com.wenance.dto.BTCResponseClientDTO;
import com.wenance.pojo.BTCData;
import com.wenance.util.Utils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WenanceBTCService {

	private final List<BTCData> historyList = new ArrayList<BTCData>();
	// private WenanceClient wenanceClient;

	//Obtengo bitcoin y genero un historial
	public void getBTC() {
		BTCData btcData;

//		String btcDataString = wenanceClient.getLastPriceBTCUSD();
		
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://cex.io/api/last_price/BTC/USD";
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		BTCResponseClientDTO btcResponseClientDTO = new Gson().fromJson(response.getBody(), BTCResponseClientDTO.class);

		this.addHistory(this.mapBTCData(btcResponseClientDTO));

		System.out.println(historyList);
	}

	public BTCData mapBTCData(BTCResponseClientDTO btcResponseClientDTO) {
		BTCData btcData = new BTCData();
		
		btcData.setCurr1(btcResponseClientDTO.getCurr1());
		btcData.setCurr2(btcResponseClientDTO.getCurr2());
		btcData.setLprice(Double.parseDouble(btcResponseClientDTO.getLprice()));
		btcData.setDateRequest(new Date());

		return btcData;
	}
	
	public BTCResponcePriceTimeDTO getBTCForTime(String dateString) {
		BTCResponcePriceTimeDTO btcResponceDTO = new BTCResponcePriceTimeDTO();
		
		Date date = Utils.convertStringToDate(dateString, "yyyy-MM-dd HH:mm:ss");
		
		double price = this.getValueBTC(date);
		
		btcResponceDTO.setDate(dateString);
		btcResponceDTO.setPrice(String.valueOf(price));
		
		return btcResponceDTO;
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

	public double maxValueBTC() {
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
