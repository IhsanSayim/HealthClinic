package com.hastane;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.hastane")
public class CerrahpasaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CerrahpasaApplication.class, args);
	}
}
