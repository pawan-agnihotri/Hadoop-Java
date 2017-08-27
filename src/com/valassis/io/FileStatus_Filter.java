package com.valassis.io;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.hadoop.hdfs.tools.GetConf;

public class FileStatus_Filter {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Path path = new Path(args[0]);
		
		FileSystem fs = FileSystem.get(new Configuration());
		//listStatus takes relative and absolute path of file or directory
		//hadoop jar FileStatus_Filter.jar hdfs://localhost.localdomain:8020/user/hdfs/output* 
		//cause error as output* didnt exist but globStatus will return output,output1 and output2
//		FileStatus[] fstatus = fs.listStatus(path, new PathFilter() {
		FileStatus[] fstatus = fs.globStatus(path, new PathFilter() {
					
			@Override
			public boolean accept(Path arg0) {
				// TODO Auto-generated method stub
				if(arg0.toString().contains(".staging"))
				return false;
				return true;
			}
		});
		for(FileStatus tempfs : fstatus)
		{
			System.out.println(tempfs.getPath().toString());
			
			System.out.println(tempfs.getAccessTime());
			System.out.print(tempfs.getBlockSize());
			System.out.print(tempfs.getGroup());
			System.out.print(tempfs.getLen());
			System.out.print(tempfs.getModificationTime());
			System.out.print(tempfs.getOwner());
			System.out.print(tempfs.getPermission());
			System.out.print(tempfs.getReplication());

		}
		
	}

}
