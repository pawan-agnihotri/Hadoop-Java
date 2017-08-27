package com.valasiss.Drivers;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.OutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.valassis.mappers.SecondarySortMapper;
import com.valassis.mappers.WordCountMapper;
import com.valassis.reducer.SecondarySortReducer;
import com.valassis.reducer.WordCountReducer;
import com.valassis.secondarySort.GroupComparator;
import com.valassis.secondarySort.KeyComparator;
import com.valassis.secondarySort.SortPartioner;
import com.valassis.writables.IntPair;

public class SecondarySortDriver extends Configured implements Tool {
	
	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Configuration conf = new Configuration();
		Job job = new Job(conf, "SecondarySort");
		job.setJarByClass(SecondarySortDriver.class);
		job.setMapperClass(SecondarySortMapper.class);
		job.setReducerClass(SecondarySortReducer.class);
		
		TextInputFormat.addInputPath(job, new Path(args[0]));
		TextOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setOutputKeyClass(IntPair.class);
		job.setOutputValueClass(NullWritable.class);
		
		job.setPartitionerClass(SortPartioner.class);
		job.setSortComparatorClass(KeyComparator.class);
		job.setGroupingComparatorClass(GroupComparator.class);
		return job.waitForCompletion(true) ? 0 : 1;
		
	}

	public static void main(String[] args) throws Exception {
		int exitCode = ToolRunner.run(new SecondarySortDriver(), args);
		System.exit(exitCode);
	}
}
