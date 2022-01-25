package ru.adm123.logAgent.util;

import javassist.CtClass;
import javassist.CtMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dmitry Ushakov at 23.01.2022
 */
public class ClassReflection {

    private static final List<String> JAVA_PACKAGES = Arrays.asList("java", "javax", "sun", "jdk", "com/sun", "com/intellij");

    private ClassReflection() {
    }

    public static List<CtMethod> getLogAnnotatedMethods(CtClass ctClass,
                                                        Class<? extends Annotation> annotationClass) {
        return Arrays.stream(ctClass.getDeclaredMethods())
                .filter(method -> isMethodAnnotated(method, annotationClass))
                .collect(Collectors.toList());
    }

    public static boolean isJavaClass(String className) {
        return className != null && JAVA_PACKAGES.stream().anyMatch(className::startsWith);
    }

    private static boolean isMethodAnnotated(CtMethod method,
                                             Class<? extends Annotation> annotationClass) {
        try {
            return method.getAnnotation(annotationClass) != null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("method reflection error");
        }
    }

}
