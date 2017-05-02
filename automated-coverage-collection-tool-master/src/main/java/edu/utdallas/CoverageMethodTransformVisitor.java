package edu.utdallas;


import java.util.ArrayList;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CoverageMethodTransformVisitor extends MethodVisitor implements Opcodes{
	
	boolean isTest;
	int mline;
	//public static int i=0;
	String mName;
	Label mlabel;
	static String classname;
//	static String linename1,linename2;
	static String methodname;
	int flag=0;
	public CoverageMethodTransformVisitor(int api, final MethodVisitor mv) {
		super(api, mv);
		this.mName=mName;
	}
//    
//	@Override
//    public AnnotationVisitor visitAnnotation(String desc,boolean visible){
//    	String str="Test";
//    	
//		if(desc.contains(str.subSequence(0, str.length()))){
//    		isTest=true;
//    		
//    		
//    		
//    	} 
//    	return mv.visitAnnotation(desc, visible);
//    }
//	
	@Override
    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
    	//if(isTest){
    		//mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        	//mv.visitLdcInsn(desc);
        	//i++;
        	classname=desc;
        	//mv.visitMethodInsn(INVOKESTATIC, "edu/utdallas/AddDetails", "add", "(Ljava/lang/String;)V", false);
        	
//    	}
    	super.visitFieldInsn(opcode, owner, name, desc);
    }
	
//	@Override
//	public void visitMethodInsn(int opcode,String owner,String name,String desc,boolean itf){
//		
//		//mv.visitMethodInsn(INVOKESTATIC, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//				methodname=desc;
//		
//		
//		super.visitMethodInsn(opcode, owner, name, desc, itf);
//	}
//    
    
	@Override
	public void visitLineNumber(int line, Label start) {

		mline=line;
	    mlabel=start;  
	    String linename= classname+":"+line;
	    mv.visitLdcInsn(linename);
	    mv.visitMethodInsn(INVOKESTATIC, "edu/utdallas/AddDetails", "add", "(Ljava/lang/String;)V", false);

    	super.visitLineNumber(line, start);
	}
//
//		
	@Override
	public void visitLabel(Label lbl)
	{
				  // mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
		
					String linename= classname+":"+mline;
					mv.visitLdcInsn(linename);
					mv.visitMethodInsn(INVOKESTATIC, "edu/utdallas/AddDetails", "add", "(Ljava/lang/String;)V", false);
		
				
			super.visitLabel(lbl);

	}
//    
    
    

}
