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

//	Lista de los BTC que se consumen
	private final List<BTCData> historyBTC = new ArrayList<BTCData>();
	// private WenanceClient wenanceClient;

	// Obtengo bitcoin y genero un historial
	public void getBTC() {

//		String btcDataString = wenanceClient.getLastPriceBTCUSD();

//		problemas con el client tuve que aplicar lo siguiente para poder consumir el servicio
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://cex.io/api/last_price/BTC/USD";
		HttpHeaders headers = new HttpHeaders();
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		BTCResponseClientDTO btcResponseClientDTO = new Gson().fromJson(response.getBody(), BTCResponseClientDTO.class);

		this.addHistory(this.mapBTCData(btcResponseClientDTO));

		System.out.println(historyBTC);
	}

//	1. Obtener el precio del bitcoin en cierto timestamp.
	public BTCResponcePriceTimeDTO getBTCForTime(String dateString) {
		BTCResponcePriceTimeDTO btcResponcePriceTimeDTO = new BTCResponcePriceTimeDTO();

		Date date = Utils.convertStringToDate(dateString, "yyyy-MM-dd HH:mm:ss");

		double price = this.getValueBTC(date);
		btcResponcePriceTimeDTO.setDate(dateString);
		btcResponcePriceTimeDTO.setPrice(String.valueOf(price));

		BTCData btcData = this.getBTCForDate(date);

		btcResponcePriceTimeDTO.setDate(dateString);
		btcResponcePriceTimeDTO.setPrice(String.valueOf(btcData.getLprice()));
		btcResponcePriceTimeDTO.setCurr1(btcData.getCurr1());
		btcResponcePriceTimeDTO.setCurr2(btcData.getCurr2());

		return btcResponcePriceTimeDTO;
	}

	public void addHistory(BTCData btcData) {
		synchronized (historyBTC) {
			historyBTC.add(btcData);
		}
	}

//	Obtengo el promedio del valor del BTC, de todos los que exiten entre 2 fechas
	public double averageBTC(Date dateFrom, Date dateTo) {
		synchronized (historyBTC) {
			return historyBTC.stream().filter((btcData) -> this.btcFilter(dateFrom, dateTo, btcData))
					.mapToDouble(this::convertToDoubleBTC).average().getAsDouble();
		}
	}

//	Obtengo solo el valor del BTC por la fecha
	public double getValueBTC(Date date) {
		synchronized (historyBTC) {
			return historyBTC.stream().filter((btcData) -> this.btcFilter(date, btcData))
					.mapToDouble(this::convertToDoubleBTC).findFirst().getAsDouble();
		}
	}

//	Obtengo el BTC por la fecha
	public BTCData getBTCForDate(Date date) {
		synchronized (historyBTC) {
			return historyBTC.stream().filter((btcData) -> this.btcFilter(date, btcData)).findFirst().get();
		}
	}

//	Obtengo el valor maximo
	public double maxValueBTC() {
		synchronized (historyBTC) {
			return historyBTC.stream().mapToDouble(this::convertToDoubleBTC).max().getAsDouble();
		}
	}

	
	private double convertToDoubleBTC(BTCData btcData) {
		return Double.valueOf(btcData.getLprice());
	}

//	Controlo que existan BTC entre 2 fechas
	private boolean btcFilter(Date dateFrom, Date dateTo, BTCData data) {
		return data.getDateRequest().after(dateFrom) && data.getDateRequest().before(dateTo);
	}

//	Controlo que exista un BTC en una fecha especifica, le sumo y le resto 5 segundos a la fecha porque en 10 segundos solo hay un registro
	private boolean btcFilter(Date date, BTCData data) {
		Date dateFrom = new Date(date.getTime() - 5000L);
		Date dateTo = new Date(date.getTime() + 5000L);

		return data.getDateRequest().after(dateFrom) && data.getDateRequest().before(dateTo);

	}
	
	public BTCData mapBTCData(BTCResponseClientDTO btcResponseClientDTO) {
		BTCData btcData = new BTCData();

		btcData.setCurr1(btcResponseClientDTO.getCurr1());
		btcData.setCurr2(btcResponseClientDTO.getCurr2());
		btcData.setLprice(Double.parseDouble(btcResponseClientDTO.getLprice()));
		btcData.setDateRequest(new Date());

		return btcData;
	}

}
