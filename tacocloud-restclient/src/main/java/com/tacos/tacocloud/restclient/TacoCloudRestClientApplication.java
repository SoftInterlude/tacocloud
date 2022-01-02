package com.tacos.tacocloud.restclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.tacos.tacocloud.domain")
public class TacoCloudRestClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(TacoCloudRestClientApplication.class, args);
    }
}
