package com.valassis.summarypatterns;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountByNameMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private Text name = new Text();
	private IntWritable count = new IntWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		String[] line = value.toString().split(",");
		//1	Pawan   Agnihotri       Hartford
		name.set(line[1]);
		context.write(name, count);
	}

	
}
