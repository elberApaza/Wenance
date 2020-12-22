package com.wenance.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class ClientConfiguration {

	@Bean
    public ClientRequestInterceptor requestInterceptor() {
        return new ClientRequestInterceptor();
    }

    class ClientRequestInterceptor implements RequestInterceptor {

        @Override
        public void apply(RequestTemplate template) {
            template.removeHeader("Authorization");
        }

    }
}