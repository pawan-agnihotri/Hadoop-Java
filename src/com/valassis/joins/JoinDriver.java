package com.valassis.joins;

import java.io.IOException;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.mapred.lib.MultipleInputs;

public class JoinDriver extends Configured implements Tool{

	@Override
	public int run(String[] args) throws IOException, InterruptedException, ClassNotFoundException
	{
		// TODO Auto-generated method stub
		//creating job instance
		Job job = new Job(getConf(),"JoinUserDetails");
		job.setJarByClass(JoinDriver.class);
		//configuring input and output paths and format types
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		
		org.apache.hadoop.mapreduce.lib.input.MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class, UserMapper.class);
		org.apache.hadoop.mapreduce.lib.input.MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class, DelvieryMapper.class);
		FileOutputFormat.setOutputPath(job, new Path(args[2]));
		//setting up mapper and reducer, conbiner and partitions
		job.setReducerClass(JoinReducer.class);
		
		//**its important to set the output key and value 
		//else its default to long writable and text and throw exception
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		//submiting the job
		return job.waitForCompletion(true) ? 0 : 1;

	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		
		int exitCode = ToolRunner.run(new JoinDriver(), args);
		System.exit(exitCode);
	}

}
