package com.example.demo.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
public class FirstItemProcessor implements ItemProcessor<Integer,Integer>
{

	

	@Override
	public Integer process(Integer item) throws Exception {
		System.out.println("Inside Item Processor");
		
		item = item + 20 ;
		return item;
	}

}
