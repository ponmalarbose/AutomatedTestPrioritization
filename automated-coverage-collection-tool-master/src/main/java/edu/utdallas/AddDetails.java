package edu.utdallas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class AddDetails {

	public static HashSet<String> coverage = new HashSet<String>();
	public static Map<String,List<String>> coverageLists= new HashMap<String,List<String>>();
	public static List<String> covlist=new ArrayList<String>();
	
	static StringBuilder str=new StringBuilder();
	static int count=0;
	static int i=0;
	public static void add(String details)
	{
		
		//System.out.println(details);
		//System.out.println(coverage.contains(details));
		
		
		if(details!=null){
			if(details.contains("org")){
				coverage.add(details);
				covlist.add(details);
				count++;
				i++;
		
		str.append(details).append('\n');
		}
		}
//		System.out.println(i);
	}

	public int getcount()
	{
		return count;
	}
	
	public static void printdetails(){
		//Iterator<String> It=coverage.iterator();
		//StringBuilder str=new StringBuilder();
//		while(It.hasNext())
//		{
//		
//		
//			//System.out.println(It.next());
//			String name=It.next();
//			if(name.contains("org")){
////				name=name.substring(1);
//				name=name.replace("/", ".");
//				name=name.replace(";", "");
//			
//				str.append(name).append('\n');	
//			
//		}
//		}//end of while
		//str.append("total lines covered:"+i+"\n");
		
		
		writetofile(str.toString());
		
		str=new StringBuilder();
		coverage.clear();
		covlist.clear();
	}
	
	public static void addtolists(String s)
	{

		if(coverageLists.containsKey(s)){
			List<String> tempVal=new ArrayList<String>();
			tempVal.addAll(covlist);
			tempVal.addAll(coverageLists.get(s));

			coverageLists.put(s, tempVal);
		}
		else
			coverageLists.put(s, covlist);
			
	}
	
	public static void writemaptoFile()
	{
		try{
		FileWriter out= new FileWriter("totalpriority.txt",true);
		
		Object[] map=sort();
		
		
		for(Object key: map)
		{
			out.write(((Map.Entry<String, List<String>>) key).getKey() + " : "+ ((Map.Entry<String, List<String>>) key).getValue().size()+'\n');
//			System.out.println(key+":"+coverageLists.get(key)+"\n");
		}
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
		
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void writeadditional()
	{
		List<String> additional=new ArrayList<String>();
		List<String> tempVal=new ArrayList<String>();
		Object[] map= sort();
		int size;
//		additional.add(((Map.Entry<String, List<String>>) map[0]).getKey());
		while(coverageLists.size()>0)
    	{
    		map=sort();
//    		System.out.println("map:"+map);
    		String outerkey=((Map.Entry<String, List<String>>) map[0]).getKey();
    		additional.add(outerkey);
    		tempVal=((Map.Entry<String, List<String>>) map[0]).getValue();
    		for(int i=1;i<map.length;i++)
    		{
    			String key=((Map.Entry<String, List<String>>) map[i]).getKey();
    			List<String> inner=coverageLists.get(key);
    			inner.removeAll(tempVal);
    			coverageLists.put(key,inner);
    		}
    		coverageLists.remove(outerkey);
//    		System.out.println("Final List:"+additional);
    		
    	}
		
		System.out.println("Size of additional:"+additional.size());
		
		
		try{
			FileWriter out= new FileWriter("Additionallist.txt",true);
			
			for(String s:additional)
			{
				out.write(s+'\n');
			}
			out.close();
			
			PrintWriter writer = new PrintWriter("AdditionalTestSuite.java", "UTF-8");
	        writer.println("import org.junit.runner.RunWith;");
	        writer.println("import org.junit.runners.Suite;");
	        writer.println("import org.junit.runners.Suite.SuiteClasses;");
	        writer.println("@RunWith(Suite.class)");
	        writer.println("@SuiteClasses({");
	        for (String s:additional) {
			    if(s.equals(additional.get(additional.size()-1)))
	        	writer.println(s + ".class");
			    else
			    writer.println(s + ".class,");
			    
			}
	        writer.println("})");
	        writer.println("public class AdditionalTestSuite {");
	        writer.println("}");
	        writer.close();
	        System.out.println("AdditionalTestSuite.java created..");
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public static Object[] sort()
	{
		Object[] a = coverageLists.entrySet().toArray();
		Arrays.sort(a, new Comparator() {
		    public int compare(Object o1, Object o2) {
		        return ((Map.Entry<String, List<String>>) o2).getValue().size()-(((Map.Entry<String, List<String>>) o1).getValue().size());
		    }
		});
//		for (Object e : a) {
//		    System.out.println(((Map.Entry<String, Integer>) e).getKey() + " : "
//		            + ((Map.Entry<String, Integer>) e).getValue());
//		}
		
		return a;
	}
	
	
	public static void writetofile(String s)
	{
		try{
			FileWriter out= new FileWriter("Cov.txt",true);
			
			out.write(s);
			out.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
			
	}
	public static void printline(String s)
	{
//		i++;
		try{
		FileWriter out= new FileWriter("Cov.txt",true);
		out.write(s+"\n");
		//System.out.println(s);
		out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
}
