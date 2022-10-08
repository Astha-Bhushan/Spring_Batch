package com.example.demo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableBatchProcessing
@Component("com.example.demo")
public class ChunkStepMariadbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChunkStepMariadbApplication.class, args);
	}

}
