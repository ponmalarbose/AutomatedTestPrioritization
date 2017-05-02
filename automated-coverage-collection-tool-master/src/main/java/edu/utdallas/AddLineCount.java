package edu.utdallas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class AddLineCount {

	public static HashMap<String, Integer> testCount = new HashMap<String, Integer>();
	public static int count=0;
	

	public static void addTestCount(String s, int i)
	{
		count++;

		if(testCount.containsKey(s))
		testCount.put(s, testCount.get(s)+i);
		else
		testCount.put(s, i);
//		System.out.println("Count="+count);
		
		sort();
	}
	
	
	
	public static Object[] sort()
	{
		Object[] a = testCount.entrySet().toArray();
		Arrays.sort(a, new Comparator() {
		    public int compare(Object o1, Object o2) {
		        return ((Map.Entry<String, Integer>) o2).getValue().compareTo(((Map.Entry<String, Integer>) o1).getValue());
		    }
		});
//		for (Object e : a) {
//		    System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
//		            + ((Map.Entry<String, Integer>) e).getValue());
//		}
		
		return a;
	}
	
	
	public static void writeToFile()
	{
		
		try{
			Object[] map=sort();
			FileWriter out= new FileWriter("totalpriority.txt",true);
			
			for (Object e : map) {
			    out.write(((Map.Entry<String, Integer>) e).getKey() + " : "+ ((Map.Entry<String, Integer>) e).getValue()+'\n');
			    
			}//end of for
		out.close();
		
		PrintWriter writer = new PrintWriter("totalTestSuite.java", "UTF-8");
        
        
        writer.println("import org.junit.runner.RunWith;");
        writer.println("import org.junit.runners.Suite;");
        writer.println("import org.junit.runners.Suite.SuiteClasses;");
        writer.println("@RunWith(Suite.class)");
        writer.println("@SuiteClasses({");
        for (Object e : map) {
		    if(((Map.Entry<String, Integer>) e).getKey().equals(((Map.Entry<String, Integer>) map[map.length-1]).getKey()))
        	writer.println(((Map.Entry<String, Integer>) e).getKey() + ".class");
		    else
		    writer.println(((Map.Entry<String, Integer>) e).getKey() + ".class,");
		    
		}
        writer.println("})");
        writer.println("public class totalTestSuite {");
        writer.println("}");
        writer.close();
        System.out.println("TotalTestSuite.java created..");
//		System.out.println("Path:"+path);
			

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
	}//end of write to file.
	
	public void evaluate(String toEval) throws FileNotFoundException, UnsupportedEncodingException
    {
        
    	
    	
    	PrintWriter writer = new PrintWriter("totalTestSuite.java", "UTF-8");
        
        
        writer.println("import org.junit.runner.RunWith;");
        writer.println("import org.junit.runners.Suite;");
        writer.println("import org.junit.runners.Suite.SuiteClasses;");
        writer.println("@RunWith(Suite.class)");
        writer.println("@SuiteClasses({");
        
        for(String key: testCount.keySet())
        {
        	writer.println(key+".class,");
        	
        	
        }
        writer.println("})");
        writer.println("public class totalTestSuite {");
        writer.println("}");
        writer.close();
        System.out.println("TotalTestSuite.java created..");
    }
	
		
	
	
	
	
	
}
