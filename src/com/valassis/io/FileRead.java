package com.valassis.io;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class FileRead {
	public static void main(String[] args) throws IOException {
		
		String url = args[0];
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		FSDataInputStream in = fs.open(new Path(url));
		IOUtils.copyBytes(in, System.out, conf);
		IOUtils.closeStream(in);
		
	}

}
