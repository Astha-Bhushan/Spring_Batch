package com.example.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"com.example.demo", "com.example.demo.config","com.example.model"})
public class CoffeeProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeProjectApplication.class, args);
	}

}

// It is a project which transfers data from csv to database