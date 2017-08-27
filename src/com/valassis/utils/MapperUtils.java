package com.valassis.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class MapperUtils {

	/**
	 * @param args
	 */
	static Map<String, String> map = new HashMap<String, String>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 String user = "<row id=12345, name=pawan /> <row id=12346, name=sapan />";
 String score = "<row perentid=12345, CreationDate=2011-08-04T09:50:25.043, Score=4 /> <row perentid=12346, CreationDate=2011-08-04T09:50:25.043, Score=5 />";
 
 map = parseXml(user);
 parseXml(score);
	}

	private static Map parseXml(String user) {
		// TODO Auto-generated method stub
		
		String[] rows = user.split("/>");
		for(int i = 0; i < rows.length; i++)
		{			
			String line = rows[i].replaceAll("<row","");
			
			String[] tokens = line.split("=|,");
			for(int j = 0; j < tokens.length; j=j+2)
			{
				System.out.println(tokens[j].trim()+"->"+tokens[j+1].trim());
				map.put(tokens[j].trim(), tokens[j+1].trim());
				
			}
			
		}
			return map;
	}

}
