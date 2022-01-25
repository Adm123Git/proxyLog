package ru.adm123.logAgent.annotation;

import javassist.CtMethod;

import java.lang.annotation.Annotation;

/**
 * @author Dmitry Ushakov at 25.01.2022
 */
public interface AnnotationHandler {
    Class<? extends Annotation> getAnnotation();
    void handle(CtMethod method);
}
