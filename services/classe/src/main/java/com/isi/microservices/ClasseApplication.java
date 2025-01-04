package com.isi.microservices;

import graphql.schema.GraphQLScalarType;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ClasseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClasseApplication.class, args);
	}

	@Bean
	public GraphQLScalarType extendedScalarLong() {
		return graphql.scalars.ExtendedScalars.GraphQLLong;
	}

}
