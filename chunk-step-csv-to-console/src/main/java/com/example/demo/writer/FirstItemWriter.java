package com.example.demo.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.example.demo.model.StudentCsv;

@Component
public class FirstItemWriter implements ItemWriter<StudentCsv>
{

	@Override
	public void write(List<? extends StudentCsv> items) throws Exception {
		
		System.out.println("Inside Item Writer");
		items.forEach(System.out::println);
		
	}
}
