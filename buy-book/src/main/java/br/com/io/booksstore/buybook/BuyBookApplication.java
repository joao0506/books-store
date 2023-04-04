package br.com.io.booksstore.buybook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class BuyBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuyBookApplication.class, args);
	}

}
