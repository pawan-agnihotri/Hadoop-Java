package com.valassis.mappers;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.valassis.writables.IntPair;

public class SecondarySortMapper extends
		Mapper<LongWritable, Text, IntPair, NullWritable> {

	@Override
	protected void map(LongWritable key, Text value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//super.map(key, value, context);
		StringTokenizer tokens = new StringTokenizer(value.toString());
		while(tokens.hasMoreElements())
		{
			context.write(new IntPair(new Text(tokens.nextToken()),new IntWritable(1)), NullWritable.get());
		}
			
	}

	

}
