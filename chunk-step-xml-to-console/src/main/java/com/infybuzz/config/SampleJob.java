package com.infybuzz.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.infybuzz.model.StudentXml;
import com.infybuzz.processor.FirstItemProcessor;
import com.infybuzz.writer.FirstItemWriter;

@Configuration
public class SampleJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private FirstItemProcessor firstItemProcessor;

	@Autowired
	private FirstItemWriter firstItemWriter;

	@Bean
	public Job chunkJob() {
		return jobBuilderFactory.get("Chunk Job").incrementer(new RunIdIncrementer()).start(firstChunkStep()).build();
	}

	private Step firstChunkStep() {
		return stepBuilderFactory.get("First Chunk Step").<StudentXml, StudentXml>chunk(3)
				.reader(staxEventItemReader(null))
				// .processor(firstItemProcessor)
				.writer(firstItemWriter).build();
	}

	@StepScope
	@Bean
	public StaxEventItemReader<StudentXml> staxEventItemReader(
			@Value("#{jobParameters['inputFile']}") FileSystemResource fileSystemResource) {
		StaxEventItemReader<StudentXml> staxEventItemReader = new StaxEventItemReader<StudentXml>();

		staxEventItemReader.setResource(fileSystemResource);
		staxEventItemReader.setFragmentRootElementName("student");
		staxEventItemReader.setUnmarshaller(new Jaxb2Marshaller() {
			{
				setClassesToBeBound(StudentXml.class);
			}
		});

		return staxEventItemReader;
	}
}
