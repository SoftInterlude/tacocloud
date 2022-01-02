package com.tacos.tacocloud.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.tacos.tacocloud.domain")
public class TacoCloudDataApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudDataApplication.class, args);
    }

}
