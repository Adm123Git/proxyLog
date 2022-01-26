package ru.adm123.logAgent.annotation;

import javassist.CtMethod;

import java.lang.annotation.Annotation;

/**
 * @author Dmitry Ushakov at 25.01.2022
 */
public interface AnnotationHandler<T extends Annotation> {
    Class<T> getAnnotationClass();
    void handle(CtMethod method);
}
