package edu.utdallas;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;

public class JUnitExecListener extends RunListener {
	
	long start;
	private RunNotifier runNotifier;
	
	public void testRunStarted(Description description) throws Exception 
		{
			start = System.currentTimeMillis();
		}
	public void testStarted(Description description) throws Exception {
//    	AddDetails.clearlist();
    	AddDetails.printline(description.getClassName()+"."+description.getMethodName());
//    	System.out.println("Starting: " + description.getMethodName());
    }

    public void testFinished(Description description) throws Exception {
    	AddDetails.addtolists(description.getClassName());
    	AddDetails.printdetails();
        
        AddLineCount.addTestCount(description.getClassName(), AddDetails.count);
        AddDetails.count=0;
        //AddDetails.clearlist();
    	
    }
    
    public void testFailure(Failure failure) throws Exception {
//    	super(testFailure);
        System.out.println("Failed: " + failure.getDescription().getClassName());
        System.out.println("Time taken:"+(System.currentTimeMillis()-start));
        runNotifier.pleaseStop();
        System.exit(0);
        
    }


    public void testRunFinished(Result result) throws Exception {
//    	AddLineCount.writeToFile();
    	AddDetails.writemaptoFile();
    	AddDetails.writeadditional();
    	System.out.println("Number of tests executed: " + result.getRunCount());
//    	System.out.println("Number:"+AddLineCount.count);
    	
        
    }

    

}
