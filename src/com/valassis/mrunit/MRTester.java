package com.valassis.mrunit;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;
 
public class MRTester {
 
//Specification of Mapper
MapDriver<LongWritable, Text, Text, IntWritable> mapDriver;
//Specification of Reduce
ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
//Specification of MapReduce program
MapReduceDriver<LongWritable, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;
 
@Before
public void setUp() {
MaxTemperatureMapper mapper = new MaxTemperatureMapper();
MaxTemperatureReducer reducer = new MaxTemperatureReducer();
//Setup Mapper
mapDriver = MapDriver.newMapDriver(mapper);
//Setup Reduce
reduceDriver = ReduceDriver.newReduceDriver(reducer);
//Setup MapReduce job
mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
}
 
@Test
public void testMapper() {
//Test Mapper with this input
mapDriver.withInput(new LongWritable(), new Text("a a a a a a a"));
//Expect this output
mapDriver.withOutput(new Text("a"), new IntWritable(1));
 try {
//Run Map test with above input and ouput
mapDriver.runTest();
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
 
@Test
public void testReducer() {
List<IntWritable> values = new ArrayList<IntWritable>();
values.add(new IntWritable(1));
/*
values.add(new IntWritable(1));
values.add(new IntWritable(1));
values.add(new IntWritable(1));
values.add(new IntWritable(1));
values.add(new IntWritable(1));
values.add(new IntWritable(1));
*/
//Run Reduce with this input
reduceDriver.withInput(new Text("a"), values);
//Expect this output
reduceDriver.withOutput(new Text("a"), new IntWritable(1));
try {
//Run Reduce test with above input and ouput
reduceDriver.runTest();
} catch (Exception e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
}