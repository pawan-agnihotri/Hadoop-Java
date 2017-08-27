package com.valassis.secondarySort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import com.valassis.writables.IntPair;

public class SortPartioner extends Partitioner<IntPair, NullWritable> {

	@Override
	public int getPartition(IntPair key, NullWritable value, int numPartitions) {
		
		// TODO Auto-generated method stub
		return Math.abs(Integer.parseInt(key.getValue().toString()) * 127) % numPartitions;
	}

}
