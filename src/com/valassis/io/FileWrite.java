package com.valassis.io;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class FileWrite {

	public static void main(String[] args) throws IOException {
		String url = args[0];
		Configuration conf = new Configuration();
		
		FileSystem fs = FileSystem.get(conf);
		FSDataOutputStream out = fs.create(new Path(url));
		out.writeChars("this is the file written from fs.create");
		for(Entry<String,String> entry : conf){
			String s = entry.getKey() + "=" + entry.getValue();
			System.out.println(s);
			out.writeBytes(s);
		}
		out.close();
		
	}
}
