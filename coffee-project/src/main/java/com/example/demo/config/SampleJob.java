package com.example.demo.config;

import javax.sql.DataSource;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.example.demo.model.Coffee;
import com.example.demo.processor.FirstItemProcessor;

@Configuration
public class SampleJob {
	
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory ;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory ;
	
	@Autowired
	private FirstItemProcessor itemProcessor ;
	
	@Autowired
	private DataSource datasource;
	
	@Bean
	public Job firstJob()
	{
		System.out.println("Trying to start job");
		
		return jobBuilderFactory.get("Coffee Job")
				.incrementer(new RunIdIncrementer())
				.start(firstStep())
				.build() ;
	}
	
	private Step firstStep()
	{
		System.out.println("Trying to start step");
		return stepBuilderFactory.get("Coffee Step")
				.<Coffee,Coffee>chunk(3)
				.reader(flatFileItemReader(null))
				//.processor(itemProcessor)
				.writer(jdbcBatchItemWriter())
				.build() ;
	
	}

	@StepScope
	@Bean
	public FlatFileItemReader<Coffee> flatFileItemReader(
			@Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource) {
		
		System.out.println("Trying to start reader");
		FlatFileItemReader<Coffee> flatFileItemReader = 
				new FlatFileItemReader<Coffee>();
		
		flatFileItemReader.setResource(fileSystemResource);

		DefaultLineMapper<Coffee> defaultLineMapper = 
				new DefaultLineMapper<Coffee>();
		
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		
		delimitedLineTokenizer.setNames("id","brand","origin","characteristics");
		
		defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
		
		BeanWrapperFieldSetMapper<Coffee> fieldSetMapper = 
				new BeanWrapperFieldSetMapper<Coffee>();
		
		fieldSetMapper.setTargetType(Coffee.class);
		
		defaultLineMapper.setFieldSetMapper(fieldSetMapper);
		
		flatFileItemReader.setLineMapper(defaultLineMapper);
		
		
		flatFileItemReader.setLinesToSkip(1);
		
		System.out.println("Inside item reader");
		
		return flatFileItemReader;
	}

	@Bean
	public JdbcBatchItemWriter<Coffee> jdbcBatchItemWriter() {
		
		System.out.println("Inside item writer");
		
		JdbcBatchItemWriter<Coffee> jdbcBatchItemWriter = 
				new JdbcBatchItemWriter<Coffee>();
		
		jdbcBatchItemWriter.setDataSource(datasource);
		jdbcBatchItemWriter.setSql(
				"insert into coffee(id,brand,origin,characteristics) "
				+ "values (:id , :brand, :origin, :characteristics)");
		
		jdbcBatchItemWriter.setItemSqlParameterSourceProvider(
				new BeanPropertyItemSqlParameterSourceProvider<Coffee>());
		
		return jdbcBatchItemWriter;
	}
	
	
	

}
