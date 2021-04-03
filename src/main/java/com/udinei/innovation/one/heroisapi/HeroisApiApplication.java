package com.udinei.innovation.one.heroisapi;


import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamoDBRepositories
public class HeroisApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroisApiApplication.class, args);

		System.out.println("Super Poderes com WebFlux");

	}


}
