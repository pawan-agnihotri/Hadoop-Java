package com.valassis.mrunit;
	import java.io.IOException;

	import org.apache.hadoop.io.IntWritable;
	import org.apache.hadoop.io.Text;
	import org.apache.hadoop.mapreduce.Reducer;

	public class MaxTemperatureReducer extends Reducer<Text, IntWritable, Text, IntWritable>{

		@Override
		protected void reduce(Text key, Iterable<IntWritable> value,
				Context context)
				throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			//super.reduce(arg0, arg1, arg2);
			int sum = 0;
			for (IntWritable val : value)
			{
				sum = sum + val.get();
			}
			context.write(key, new IntWritable(sum));
		}



		
	}

