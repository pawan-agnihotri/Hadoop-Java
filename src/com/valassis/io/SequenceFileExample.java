package com.valassis.io;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapFile;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.Counters.Counter;

public class SequenceFileExample {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String data[] = {"line 1","line 2","line 3","line 4"};
		String uri = args[0];
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(uri),conf);
		Path path = new Path(uri);
		IntWritable key = new IntWritable();
		Text text = new Text();
		SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, path, IntWritable.class, Text.class);
		MapFile.Writer mapFile = new MapFile.Writer(conf, fs,uri+".map" , IntWritable.class, Text.class);
		
		for (int i =0 ; i < 100; i++)
		{
			text.set(data[i%data.length]);
			key.set(i);
			writer.append(key, text);
			mapFile.append(key, text);
		}
	IOUtils.closeStream(writer);	
	IOUtils.closeStream(mapFile);	
	SequenceFile.Reader reader = new SequenceFile.Reader(fs, path,conf );
	long pos = reader.getPosition();
	reader.sync(150L);
	while(reader.next(key, text))
	{
		System.out.println(reader.syncSeen() == true ? "Y" + pos: "N" + key  + "->" + text);
	}
		
	IOUtils.closeStream(reader);	
	}

}
