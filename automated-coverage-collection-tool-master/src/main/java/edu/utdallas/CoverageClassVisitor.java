package edu.utdallas;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CoverageClassVisitor extends ClassVisitor {
	
	
	public CoverageClassVisitor(int api,ClassVisitor cv) {
		super(Opcodes.ASM5,cv);  //Opcodes.ASM5
	}
	

	
    @Override
    public MethodVisitor visitMethod(final int access, final String name,
            final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);  
        return mv == null ? null : new CoverageMethodTransformVisitor(Opcodes.ASM5, mv);
    }
    



}
