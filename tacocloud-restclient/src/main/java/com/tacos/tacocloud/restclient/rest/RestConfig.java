package com.tacos.tacocloud.restclient.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * 配置RestTemplate和Traverson消费Rest
 */
@Configuration
public class RestConfig {

    @Value("${tacocloud.rest.ip}")
    public String restUrl;

    @Value("${tacocloud.traverson.ip}")
    public String traversonUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Traverson traverson() {
        return new Traverson(
                URI.create(traversonUrl),
                MediaTypes.HAL_JSON
        );
    }


}
