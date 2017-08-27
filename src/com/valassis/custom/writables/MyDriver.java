package com.valassis.custom.writables;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

public class MyDriver extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		
		Job job = new Job(new Configuration());
		job.setJarByClass(getClass());
		job.setNumReduceTasks(0);
		job.setMapperClass(MyMapper.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setInputFormatClass(MyInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		// TODO Auto-generated method stub
		return job.waitForCompletion(true) ? 0 :1;
	}
	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new MyDriver(), args);
		System.exit(exitCode);
	}

}
