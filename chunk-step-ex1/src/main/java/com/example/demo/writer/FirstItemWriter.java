package com.example.demo.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class FirstItemWriter implements ItemWriter<Integer>
{

	@Override
	public void write(List<? extends Integer> items) throws Exception {
		
		System.out.println("Inside Item Writer");
		items.forEach(System.out::println);
		
	}

}
