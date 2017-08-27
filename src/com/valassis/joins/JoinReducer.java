package com.valassis.joins;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class JoinReducer extends Reducer<Text, Text, Text, Text> {

	private Text userName = new Text();
	private Text statusCode = new Text();
	private static Map<String,String> map = new HashMap<String, String>();
	
	@Override
	protected void setup(Context context) throws IOException,
			InterruptedException {
		String line = null;
BufferedReader bf = new BufferedReader(new FileReader(new File("delivery_code")));
line = bf.readLine();
while (line != null && !line.equalsIgnoreCase("")){
	System.out.println(line);
	String[] tokens = line.split(",");
	map.put(tokens[0].trim(), tokens[1].trim());
	line = bf.readLine();

}
	
	}

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
		
		for(Text val : values)
		{
			String[] tokens = val.toString().split(":");
			if(tokens[0].startsWith("CD"))
			{
				userName.set(tokens[1]);
				
			}
			if(tokens[0].startsWith("DS"))
			{				
				statusCode.set(map.get(tokens[1].trim()));
			}
			System.out.println(userName.toString() +"->"+statusCode.toString());
		}
		context.write(userName, statusCode);

	}
	
}
