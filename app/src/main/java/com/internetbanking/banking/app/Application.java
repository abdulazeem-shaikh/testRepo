package com.internetbanking.banking.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.internetbanking.app.customer.properties.InternetBankingProperties;

@SpringBootApplication
@ComponentScan( basePackages = "com.internetbanking.app")
@EnableConfigurationProperties(InternetBankingProperties.class)
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
