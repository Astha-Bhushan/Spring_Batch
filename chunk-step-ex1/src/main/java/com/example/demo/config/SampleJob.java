package com.example.demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.service.ThirdTasklet;

@Configuration
public class SampleJob {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory ;
	
	@Autowired 
	private StepBuilderFactory stepBuilderFactory ;
	
	@Autowired
	private ItemReader itemReader ;
	
	@Autowired
	private ItemProcessor itemProcessor ;
	
	@Autowired
	private ItemWriter itemWriter ;
	
	@Bean
	public Job secondJob() {
		
		return jobBuilderFactory.get("Second Job")
				.incrementer(new RunIdIncrementer())
				.start(firstChunkStep())
				.build();
              
	}
	
	private Step firstChunkStep()
	{
		return stepBuilderFactory.get("First Chunk Step")
				 .<Integer,Integer>chunk(3)
				 .reader(itemReader)
				 .processor(itemProcessor)
				 .writer(itemWriter)
				 .build() ;
   }
	

	
}
