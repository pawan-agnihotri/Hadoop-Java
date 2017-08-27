package com.valassis.mrunit;
import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.valasiss.enums.COUNT_ROW_PROCESSED;

public class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	protected void map(LongWritable key, Text value,
			Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		StringTokenizer tokens = new StringTokenizer(value.toString());
		while(tokens.hasMoreElements())
		{
			context.write(new Text(tokens.nextToken()),new IntWritable(1));
			// complie time error if string is used
			//context.write(tokens.nextToken().toString(),new IntWritable(1));
		}
	//	super.map(key, value, context);
	}
	

}
