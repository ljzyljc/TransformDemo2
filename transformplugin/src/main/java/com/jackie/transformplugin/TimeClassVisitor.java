package com.jackie.transformplugin;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


/**
 * Description:
 *
 * @author feifei5292190@gmail.com
 * @date 2020-01-04
 */
public class TimeClassVisitor extends ClassVisitor implements Opcodes {

    private String mClassName;
    private String[] mInterfaces;

    public TimeClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        System.out.println("LifecycleClassVisitor : visit -----> started ：" + name);
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
        mInterfaces = interfaces;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {
        System.out.println("LifecycleClassVisitor : visitMethod : " + name);
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if (mv != null) {
            if (name.equals("onClick") && desc.equals("(Landroid/view/View;)V") && isConatainInterfece(mInterfaces,"android/view/View$OnClickListener")) {
                mv = new MethodVisitor(Opcodes.ASM5, mv) {
                    @Override
                    public void visitCode() {
                        super.visitCode();
                        mv.visitVarInsn(ALOAD, 1);
                        mv.visitMethodInsn(INVOKESTATIC, "com/jackie/transformdemo/ReportUtil", "report", "(Landroid/view/View;)V", false);
                    }
                };
            }
        }
        //匹配FragmentActivity
        if ("com/jackie/transformdemo/MainActivity".equals(this.mClassName)) {
            if ("onCreate".equals(name)) {
                //处理onCreate
                return new LifecyclerOnCreateMethodVisior(mv);
            } else if ("onDestroy".equals(name)) {
                //处理onDestroy
                return new LifecyclerOnCreateMethodVisior(mv);
            }
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        System.out.println("LifecycleClassVisitor : visit -----> end");
        super.visitEnd();
    }

    private static boolean isConatainInterfece(String[] mInterfaces, String mInterface) {
        for (String temp : mInterfaces) {
            if (temp.equals(mInterface)) {
                return true;
            }
        }
        return false;

    }
}
