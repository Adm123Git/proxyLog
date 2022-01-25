package ru.adm123.logAgent.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dmitry Ushakov at 23.01.2022
 */
public class ClassReflection {

    private ClassReflection() {
    }

    public static List<Method> getLogAnnotatedMethods(Class<?> clazz,
                                                        Class<? extends Annotation> annotationClass) {
        return Arrays.stream(clazz.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());
    }

}
