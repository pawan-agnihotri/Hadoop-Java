package com.valassis.joins;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class UserMapper extends Mapper<LongWritable, Text, Text, Text>{
	private Text mobileNumber = new Text();
	private Text userName = new Text();
	
	@Override
 protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException	{
	 String[] tokens = value.toString().split(",");
	 mobileNumber.set(tokens[0]);
	 userName.set("CD:" + tokens[1]);
	 context.write(mobileNumber, userName);
	 

 }
}
