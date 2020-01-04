package com.jackie.transformplugin;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.PrintWriter;

/**
 * Description:
 *
 * @author feifei5292190@gmail.com
 * @date 2020-01-02
 */
public class TestOne {

    public static void main(String[] args){

        ClassWriter cw = new ClassWriter(0);
        TraceClassVisitor cv = new TraceClassVisitor(cw, new PrintWriter(System.out));
        cv.visit(Opcodes.ASM5, Opcodes.ACC_PUBLIC,"com/jackie/Namej",null,"java/lang/Object",null);
        cv.visitEnd();

    }
}
