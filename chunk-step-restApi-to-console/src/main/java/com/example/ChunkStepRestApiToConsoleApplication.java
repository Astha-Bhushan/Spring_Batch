package com.example;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableBatchProcessing
@ComponentScan({"com.infybuzz.config", "com.infybuzz.listener", 
	"com.infybuzz.reader", "com.infybuzz.processor", 
	"com.infybuzz.writer", "com.infybuzz.service"})
public class ChunkStepRestApiToConsoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChunkStepRestApiToConsoleApplication.class, args);
	}

}
