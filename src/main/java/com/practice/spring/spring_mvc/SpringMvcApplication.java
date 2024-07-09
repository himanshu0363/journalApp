package com.practice.spring.spring_mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.MongoDbFactoryParser;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// it does
// @Configuration - ,
// @ComponentScan - scans the components in the package in which this java class is present,
// @EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement // to tell the application that we need to enable the transaction management for the db
public class SpringMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager createManager(MongoDatabaseFactory mongoDatabaseFactory) {
		return new MongoTransactionManager(mongoDatabaseFactory);
	}
}
