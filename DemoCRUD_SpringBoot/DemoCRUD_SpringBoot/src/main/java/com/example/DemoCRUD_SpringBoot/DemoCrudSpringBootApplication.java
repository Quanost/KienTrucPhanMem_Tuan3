package com.example.DemoCRUD_SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DemoCrudSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoCrudSpringBootApplication.class, args);
	}

}
