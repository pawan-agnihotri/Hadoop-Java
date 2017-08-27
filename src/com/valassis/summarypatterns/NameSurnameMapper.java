package com.valassis.summarypatterns;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class NameSurnameMapper extends Mapper<LongWritable, Text, Text, Text>
{

	private Text first = new Text();
	private Text second = new Text();

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String[] line = value.toString().split(",");
		first.set(line[1]);
		second.set(line[2]);
		context.write(first,second);
	}
 

}
