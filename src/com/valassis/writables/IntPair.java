package com.valassis.writables;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class IntPair implements WritableComparable<IntPair> {

	private Text key;
	private IntWritable value;

	public IntPair()
	{
		key = new Text();
		value = new IntWritable();
	}
	public Text getKey() {
		return key;
	}
	public void setKey(Text key) {
		this.key = key;
	}
	public IntWritable getValue() {
		return value;
	}
	public void setValue(IntWritable value) {
		this.value = value;
	}
	public IntPair(Text key, IntWritable value)
	{
		this.key = key;
		this.value = value;
	}

	public IntPair(String key, String value)
	{
		this.key.set(key);
		this.value = new IntWritable(Integer.parseInt(value));
	}

	@Override
	public void readFields(DataInput arg0) throws IOException {
		// TODO Auto-generated method stub
		key.readFields(arg0);
		value.readFields(arg0);
		
	}

	@Override
	public void write(DataOutput arg0) throws IOException {
		key.write(arg0);
		value.write(arg0);
		// TODO Auto-generated method stub
		
	}

	@Override
	public int compareTo(IntPair o) {
		// TODO Auto-generated method stub
		int cmp = this.value.compareTo(o.value);
		if(cmp != 0)
		{
			cmp = this.key.compareTo(o.key);
		}
		return cmp;
	}
@Override
public String toString() {
	// TODO Auto-generated method stub
	return key.toString() + "\t" + value.toString();
}
}
