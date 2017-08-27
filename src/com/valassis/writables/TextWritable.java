package com.valassis.writables;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;

public class TextWritable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Text text = new Text("hadoop");
		System.out.println("find it at position:" + text.find("o"));
		System.out.println(text.getLength());
		System.out.println(text.getBytes().length);
		text.set(new Text("oop"));
		System.out.println("find it at position:" + text.find("o"));
		System.out.println(text.getLength());
		System.out.println(text.getBytes().length);

		text.set("oop");
		System.out.println("find it at position:" + text.find("o"));
		System.out.println(text.getLength());
		System.out.println(text.getBytes().length);

		
		System.out.println("find it at position:" + (char)text.charAt(2));
		System.out.println("find it at position:" + text.charAt(20));
		
		System.out.println("find it at position:" + text.charAt(2));
		
		MapWritable map = new MapWritable();
		map.put(new IntWritable(1), new Text("one"));
		map.put(new IntWritable(2), new Text("second"));
		System.out.println(map.size() + " integer will return null--" + map.get(1));
		System.out.println(map.size() + "-" + map.get(new IntWritable(1)));
		System.out.println(map.size() + " will return null--" + map.get(5));
		
			
	}

}
