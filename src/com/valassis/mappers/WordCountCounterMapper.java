package com.valassis.mappers;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.valasiss.enums.COUNT_ROW_PROCESSED;

public class WordCountCounterMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		StringTokenizer tokens = new StringTokenizer(value.toString());
		while(tokens.hasMoreElements())
		{
			context.write(new Text(tokens.nextToken()),new IntWritable(1));
			context.getCounter(COUNT_ROW_PROCESSED.TOTAL).increment(1);

		}
	//	super.map(key, value, context);
	}
	

}
