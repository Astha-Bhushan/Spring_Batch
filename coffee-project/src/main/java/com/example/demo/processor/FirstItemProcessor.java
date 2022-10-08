package com.example.demo.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.model.Coffee;


@Component
public class FirstItemProcessor implements ItemProcessor<Coffee, Coffee> {

	@Override
	public Coffee process(Coffee item) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
