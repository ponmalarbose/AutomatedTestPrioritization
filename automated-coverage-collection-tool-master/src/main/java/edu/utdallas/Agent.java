
package edu.utdallas;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.instrument.Instrumentation;


/**
 * Agent
 * 
 * @author Xin Tong
 */
public class Agent {

    public static void premain(String agentArs, Instrumentation inst)
            throws IOException {
        inst.addTransformer(new CoverageClassFileTransformer());
    }

}
