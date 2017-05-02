package edu.utdallas;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

public class CoverageClassFileTransformer implements ClassFileTransformer {


	public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
			ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
		
		if(className.contains("org/apache/commons/dbutils"))
		{	
			ClassReader cr = new ClassReader(classfileBuffer);
		
		
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
		
		
		ClassVisitor cv = new CoverageClassVisitor(Opcodes.ASM5,cw);
		
		cr.accept(cv, 0);
		
		
		return cw.toByteArray();
		}
		return null;
	}

}
