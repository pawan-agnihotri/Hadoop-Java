package com.valasiss.Drivers;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Counters.Counter;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.valasiss.enums.COUNT_ROW_PROCESSED;
import com.valassis.mappers.WordCountCounterMapper;
import com.valassis.mappers.WordCountMapper;
import com.valassis.reducer.WordCountReducer;

public class WordCountCounterDriver extends Configured implements Tool {

	@Override
	public int run(String[] arg) throws Exception {
		// TODO Auto-generated method stub
		//creating job instance
		Job job = new Job(getConf(),"WordCount");
		job.setJarByClass(WordCountCounterDriver.class);
		//configuring input and output paths and format types
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPaths(job, arg[0]);
		FileOutputFormat.setOutputPath(job, new Path(arg[1]));
		
		//setting up mapper and reducer, conbiner and partitions
		job.setMapperClass(WordCountCounterMapper.class);
		job.setCombinerClass(WordCountReducer.class);
		job.setReducerClass(WordCountReducer.class);
		
		//**its important to set the output key and value 
		//else its default to long writable and text and throw exception
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		//submiting the job
		job.waitForCompletion(true);
		Counters counters = job.getCounters();
		counters.findCounter(COUNT_ROW_PROCESSED.TOTAL).getDisplayName();
		counters.findCounter(COUNT_ROW_PROCESSED.TOTAL).getName();
		counters.findCounter(COUNT_ROW_PROCESSED.TOTAL).getValue();
		return 0;
		
	}
	
	public static void main(String[] args) throws Exception {
	
		int exitCode = ToolRunner.run(new WordCountCounterDriver(), args);
		System.exit(exitCode);
	}

}
