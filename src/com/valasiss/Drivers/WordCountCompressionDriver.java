package com.valasiss.Drivers;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.valassis.mappers.WordCountMapper;
import com.valassis.reducer.WordCountReducer;

public class WordCountCompressionDriver extends Configured implements Tool {

	@Override
	public int run(String[] arg) throws Exception {
		// TODO Auto-generated method stub
		//creating job instance
		Job job = new Job(getConf(),"WordCountCompression");
		job.setJarByClass(WordCountCompressionDriver.class);
		//configuring input and output paths and format types
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		FileInputFormat.addInputPaths(job, arg[0]);
		FileOutputFormat.setOutputPath(job, new Path(arg[1]));
		
		//setting up mapper and reducer, conbiner and partitions
		job.setMapperClass(WordCountMapper.class);
		job.setCombinerClass(WordCountReducer.class);
		job.setReducerClass(WordCountReducer.class);
		
		//**its important to set the output key and value 
		//else its default to long writable and text and throw exception
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileOutputFormat.setCompressOutput(job, true);
		FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);
		//submiting the job
		return job.waitForCompletion(true) ? 0 : 1;
		
	}
	
	public static void main(String[] args) throws Exception {
	
		int exitCode = ToolRunner.run(new WordCountCompressionDriver(), args);
		System.exit(exitCode);
	}

}
