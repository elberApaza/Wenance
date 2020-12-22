package com.wenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.wenance.service.BTCHistoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableFeignClients
@EnableScheduling
@Configuration
@SpringBootApplication
public class WenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WenanceApplication.class, args);
	}
	
	@Autowired
	private BTCHistoryService btcHitoryService;
	
    @Scheduled(fixedRateString = "${frequency-get}")
    public void getBitcoin() {
        log.info("Iniciando consumo de bitcoin");
        try {
        	btcHitoryService.getBTC();
        } catch (Exception e) {
            log.error("Ocurrio un error inesperado al consumir bitcoin", e);
        } finally {
        	log.info("Fin del consumo de bitcoin");
        }
    }

}
