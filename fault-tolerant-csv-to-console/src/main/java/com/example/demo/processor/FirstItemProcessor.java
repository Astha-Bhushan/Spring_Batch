package com.example.demo.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.model.StudentCsv;


@Component
public class FirstItemProcessor implements ItemProcessor<StudentCsv,StudentCsv>
{

	@Override
	public StudentCsv process(StudentCsv item) throws Exception {
		System.out.println("In Processor");
		// TODO Auto-generated method stub
		if(item.getId() == 3)
		{
			throw new NullPointerException() ;
		}
		return item ;
	}

	


}
