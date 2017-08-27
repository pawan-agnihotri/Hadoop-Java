package com.valassis.summarypatterns;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class CountByName extends Configured implements Tool{

	@Override
	public int run(String[] args) throws Exception {
		
		Job job = new Job();
		job.setJarByClass(getClass());
		
		job.setMapperClass(CountByNameMapper.class);
		job.setReducerClass(CountByNameReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		ControlledJob countbyname = new ControlledJob(job,null);
		
		Job job2 = new Job();
		job2.setJarByClass(getClass());
		
		job2.setMapperClass(CountByNameMapper.class);
		job2.setReducerClass(CountByNameReducer.class);
		
		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(IntWritable.class);
		
		FileInputFormat.addInputPath(job2, new Path(args[0]));
		FileOutputFormat.setOutputPath(job2, new Path(args[2]));
		
		//change for distinct
				job2.setMapOutputValueClass(IntWritable.class);
				job2.setOutputValueClass(NullWritable.class);
				job2.setReducerClass(DistinctNamesReducer.class);
				ControlledJob distinctname = new ControlledJob(job2,null);
				distinctname.addDependingJob(countbyname);
				JobControl jc = new JobControl("controled");
				jc.addJob(distinctname);
				jc.addJob(countbyname);
				jc.run();
				
				return 0;
	
	}

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {

		int exitcode = ToolRunner.run(new CountByName(), args);
		System.exit(exitcode);
	}

}
