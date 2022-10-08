package com.example.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan("com.example.demo")
public class ChunkStepCsvToConsoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChunkStepCsvToConsoleApplication.class, args);
	}

}
