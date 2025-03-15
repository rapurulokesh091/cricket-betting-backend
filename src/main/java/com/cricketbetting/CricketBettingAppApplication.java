package com.cricketbetting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.cricketbetting")
public class CricketBettingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricketBettingAppApplication.class, args);
	}

}
