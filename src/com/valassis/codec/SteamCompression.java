package com.valassis.codec;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionInputStream;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

public class SteamCompression {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// TODO Auto-generated method stub
		String codecClassName = args[0];
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		Class<?> codecClass =       Class.forName(codecClassName);
		CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);
		CompressionOutputStream out = codec.createOutputStream(System.out);
		IOUtils.copyBytes(System.in, out, 4096,false);
		CompressionOutputStream outFile = codec.createOutputStream(fs.create(new Path(args[1])));
		IOUtils.copyBytes(System.in, out, 4096,false);
		IOUtils.copyBytes(System.in, outFile, 4096,false);
	
		outFile.finish();
		/*		Path path = new Path(args[1]);
		
		CompressionInputStream in = codec.createInputStream(fs.open(path));
		System.out.println("print");
		System.out.println(in.read());
		
		IOUtils.copyBytes(in, System.out, conf);
		IOUtils.closeStream(in);
*/	
		
	}

}
