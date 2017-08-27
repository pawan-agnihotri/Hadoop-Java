package com.valassis.reducer;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.valassis.writables.IntPair;

public class SecondarySortReducer extends
		Reducer<IntPair, NullWritable, IntPair,NullWritable> {

	@Override
	protected void reduce(IntPair key, Iterable<NullWritable> value,
			Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		context.write(key, NullWritable.get());
	}
	

}
