package com.valassis.custom.writables;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MyMapper extends Mapper<MyKey, MyValue, Text, Text> {

	@Override
	protected void map(MyKey key, MyValue value,
			org.apache.hadoop.mapreduce.Mapper.Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		context.write(new Text(key.getKey1().toString() + key.getKey2().toString()), new Text(value.getValue1().toString() + value.getValue2().toString()));
	}
	

}
