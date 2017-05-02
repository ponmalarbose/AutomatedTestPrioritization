package edu.utdallas;

import org.junit.Test;

import edu.utdallas.Sleeping;

public class AgentTest {
	
	   @Test
	    public void testAgent() throws InterruptedException {
		     Sleeping sleeping = new Sleeping();
		        sleeping.randomSleep();
	        
	    }
}
