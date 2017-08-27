package com.valassis.secondarySort;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import com.valassis.writables.IntPair;

public class GroupComparator extends WritableComparator {

	protected GroupComparator() {
		super(IntPair.class, true);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		// TODO Auto-generated method stub
		IntPair i = (IntPair)a;
		IntPair j = (IntPair)b;
		
		return i.getKey().compareTo(j.getKey());
	}

}
