package ru.adm123.logAgent.annotation;

import ru.adm123.logAgent.util.ClassReflection;
import ru.adm123.logAgent.util.Console;

import java.lang.instrument.ClassFileTransformer;
import java.lang.reflect.Method;
import java.security.ProtectionDomain;
import java.util.List;

/**
 * Created by Dmitry Ushakov at 22.01.2022
 */
public class TransformerMethodLog implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        try {
            List<Method> loggedMethods = ClassReflection.getLogAnnotatedMethods(loader.loadClass(className), Log.class);

                System.out.println("----------");
                System.out.println(loggedMethods.size() + " @Log annotated methods found");
                System.out.println("----------");

        } catch (ClassNotFoundException e) {
            Console.printLn("class " + className + " not found");
        }
        return classfileBuffer;
//        System.out.println();
//        System.out.println("load class: " + className.replaceAll("/", "."));
//        System.out.println(String.format("loaded %s classes", ++count));
//        return classfileBuffer;
    }

}
