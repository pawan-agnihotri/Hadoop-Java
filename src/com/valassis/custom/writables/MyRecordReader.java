package com.valassis.custom.writables;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;
import org.apache.hadoop.mapreduce.lib.input.LineRecordReader;

public class MyRecordReader extends RecordReader<MyKey, MyValue> {
	private MyKey mykey = new MyKey();
	private MyValue myvalue = new MyValue();
	private LineRecordReader record = new LineRecordReader();

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		record.close();
		
	}

	@Override
	public MyKey getCurrentKey() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return mykey;
	}

	@Override
	public MyValue getCurrentValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return myvalue;
	}

	@Override
	public float getProgress() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		return record.getProgress();
	}

	@Override
	public void initialize(InputSplit arg0, TaskAttemptContext arg1)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		record.initialize(arg0, arg1);
	}

	@Override
	public boolean nextKeyValue() throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		boolean linePresent = record.nextKeyValue();
		if(linePresent)
		{
			Text line = record.getCurrentValue();
			String[] tokens = line.toString().split(",");
			mykey.setKey1(new Text(tokens[0]));
			mykey.setKey2(new Text(tokens[1]));
			myvalue.setValue1(new Text(tokens[2]));
			myvalue.setValue2(new Text(tokens[3]));
			
		}
	
		return linePresent;
	}
	

}
