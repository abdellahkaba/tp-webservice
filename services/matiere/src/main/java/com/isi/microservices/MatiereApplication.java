package com.isi.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration.class})
public class MatiereApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatiereApplication.class, args);
	}

}
