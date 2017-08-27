package com.valassis.custom.writables;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

public class MyValue implements WritableComparable {
	private Text value1,value2;
	public MyValue()
	{
		value1 = new Text();
		value2 = new Text();
	}

	@Override
	public int compareTo(Object o) {
		
		MyValue myvalue = (MyValue)o;
		int cmp = value1.compareTo(myvalue.value1);
		if(cmp==0)
		{
			cmp = value2.compareTo(myvalue.value2);
		}
		return cmp;
	}

	public Text getValue1() {
		return value1;
	}

	public void setValue1(Text value1) {
		this.value1 = value1;
	}

	public Text getValue2() {
		return value2;
	}

	public void setValue2(Text value2) {
		this.value2 = value2;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		value1.readFields(in);
		value2.readFields(in);
				// TODO Auto-generated method stub
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		value1.write(out);
		value2.write(out);
		// TODO Auto-generated method stub
		
	}

}
