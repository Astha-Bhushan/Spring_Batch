package com.example.demo.listener;

import java.io.File;
import java.io.FileWriter;

import org.springframework.batch.core.annotation.OnSkipInProcess;
import org.springframework.batch.core.annotation.OnSkipInRead;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.stereotype.Component;

import com.example.demo.model.StudentCsv;

@Component
public class SkipListener {

	@OnSkipInRead
	public void skipInRead(Throwable th) {
		if (th instanceof FlatFileParseException) {
			String path = "C:\\dev\\Academy Project\\Spring_Batch_Basics\\fault-tolerant-csv-to-console\\Chunk Job\\First Chunk Step\\reader\\skipped.csv";
			String data = ((FlatFileParseException) th).getInput() ;
            createFile(path , data);
		}
	}
	
	@OnSkipInProcess
	public void skipInProcess(StudentCsv item ,Throwable th) // here item datatype is datatype of data that we read
	{
		
		if (th instanceof NullPointerException) {
			String path = "C:\\dev\\Academy Project\\Spring_Batch_Basics\\fault-tolerant-csv-to-console\\Chunk Job\\First Chunk Step\\processor\\skipped.csv";
			String data = item.toString();
            createFile(path , data);
		}
	}
	
	@OnSkipInWrite
	public void skipInWriter(StudentCsv item ,Throwable th) // here item datatype is datatype of data that we read
	{
		
		if (th instanceof NullPointerException) {
			String path = "C:\\dev\\Academy Project\\Spring_Batch_Basics\\fault-tolerant-csv-to-console\\Chunk Job\\First Chunk Step\\processor\\skipped.csv";
			String data = item.toString();
            createFile(path , data);
		}
	}

	
	public void createFile(String filePath , String data){
		try(FileWriter fileWriter = new FileWriter( new File(filePath) ))
		{
	      fileWriter.write(data + "\n");
			
		}
		catch(Exception e)
		{
			
		}
	}

}
