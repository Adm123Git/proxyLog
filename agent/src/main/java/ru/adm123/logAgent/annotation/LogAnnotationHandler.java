package ru.adm123.logAgent.annotation;

import javassist.CtMethod;

import java.lang.annotation.Annotation;

/**
 * @author Dmitry Ushakov at 25.01.2022
 */
public class LogAnnotationHandler implements AnnotationHandler {

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return Log.class;
    }

    @Override
    public void handle(CtMethod method) {
        // ToDo #Adm123 тут проксируем и логируем метод
    }

}
