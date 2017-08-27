package com.valassis.mapsidejoin;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MapSideMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	Log log = LogFactory.getLog(getClass());

	private HashMap<Integer,String> map = new HashMap<Integer,String>();
	@Override
	protected void map(LongWritable key, Text value,Context context)
			throws IOException, InterruptedException {
		String[] token = value.toString().split(",");
		log.info(map.containsKey(new Integer(token[0])));
		if(map.containsKey(new Integer(token[0])))
		{
			context.write(new Text(map.get(new Integer(token[0]))), value);
		}
			
	}

	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		super.setup(context);
/*
		Path[] paths = context.getLocalCacheFiles();
		for(Path path : paths)
		{
			log.error(path.getName());

			if(path.getName().equals("dinputdata") )
			{
				File file  = new File(path.getName());
				FileReader fis = new FileReader(file);
				BufferedReader reader = new BufferedReader(fis);
				String line = reader.readLine();

				while(line != null)
				{
					log.error(line);

					String[] token = line.split(",");
					map.put( new Integer(token[0]), line);
				}
				
			}
		}
		*/
		File file  = new File("dinputdata");
		FileReader fis = new FileReader(file);
		BufferedReader reader = new BufferedReader(fis);
		String line = reader.readLine();

		while(line != null)
		{
			log.error(line);

			String[] token = line.split(",");
			map.put( new Integer(token[0]), line);
		}
		reader.close();
	}

}
