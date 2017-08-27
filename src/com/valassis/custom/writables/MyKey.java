package com.valassis.custom.writables;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;


public class MyKey implements WritableComparable {
	private Text key1, key2;

	public MyKey() {
	key1 = new Text();
	key2 = new Text();
	
	}
	
	@Override
	public int compareTo(Object o) {
		MyKey mykey = (MyKey)o;
		int cmp = key1.compareTo(mykey.key1);
		if(cmp==0)
		{
			cmp = key2.compareTo(mykey.key2);
		}
		// TODO Auto-generated method stub
		return cmp;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		key1.readFields(in);
		key2.readFields(in);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		key1.write(out);
		key2.write(out);
		// TODO Auto-generated method stub
		
	}

	public Text getKey1() {
		return key1;
	}

	public void setKey1(Text key1) {
		this.key1 = key1;
	}

	public Text getKey2() {
		return key2;
	}

	public void setKey2(Text key2) {
		this.key2 = key2;
	}
	
	


}
