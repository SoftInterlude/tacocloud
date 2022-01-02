package com.tacos.tacocloud.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tacos.tacocloud.data")
@EntityScan(basePackages = "com.tacos.tacocloud.domain")
public class TacoCloudApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApiApplication.class, args);
	}

}
