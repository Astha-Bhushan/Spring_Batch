package com.example.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


import com.example.model.StudentCsv;

@Component
public class FirstItemProcessor implements ItemProcessor<StudentCsv, StudentCsv> {

	@Override
	public StudentCsv process(StudentCsv item) throws Exception {
		System.out.println("Inside Item Processor");
		StudentCsv studentCsv = new StudentCsv();
		studentCsv.setId(item.getId());
		studentCsv.setFirstName(item.getFirstName());
		studentCsv.setLastName(item.getLastName());
		studentCsv.setEmail(item.getEmail());
		
		return studentCsv;
	}

}
