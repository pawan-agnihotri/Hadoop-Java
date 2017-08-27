package com.valassis.io;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

public class HadoopWriteRead {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		Configuration conf  = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(args[0]), conf);
//reading file
		FSDataInputStream in = fs.open(new Path(args[0]));
		IOUtils.copyBytes(in, System.out, 4096,false);
		IOUtils.closeStream(in);

//writing
		FSDataOutputStream out = fs.create(new Path(args[0]), new Progressable() {
			
			@Override
			public void progress() {
				// TODO Auto-generated method stub
				System.out.println(".");
			}
		});
		for(int i = 0; i < 100000;i++)
		out.writeBytes("Hello World");
//		Thread.sleep(10000);
		out.flush();
		IOUtils.closeStream(out);
/*
 * 			

		System.out.println("re-read the file");
		in.seek(0);
		IOUtils.copyBytes(in, out, 4096,true);
		IOUtils.closeStream(in);
		IOUtils.closeStream(out);
*/
		
		//metadata
		Path p = new Path(args[0]);
		FileStatus fstatus = fs.getFileStatus(p);
		System.out.println(fstatus.getAccessTime());
		System.out.println(fstatus.getBlockSize());
		System.out.println(fstatus.getGroup());
		System.out.println(fstatus.getLen());
		System.out.println(fstatus.getModificationTime());
		System.out.println(fstatus.getOwner());
		System.out.println(fstatus.getPermission());
		System.out.println(fstatus.getReplication());
		
	}

}
