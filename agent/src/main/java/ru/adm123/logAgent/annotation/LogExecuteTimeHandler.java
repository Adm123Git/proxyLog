package ru.adm123.logAgent.annotation;

import javassist.*;

/**
 * @author Dmitry Ushakov at 25.01.2022
 */
public class LogExecuteTimeHandler implements AnnotationHandler<LogExecuteTime> {

    @Override
    public Class<LogExecuteTime> getAnnotationClass() {
        return LogExecuteTime.class;
    }

    @Override
    public void handle(CtMethod method) {
        try {
            var annotation = ((LogExecuteTime) method.getAnnotation(LogExecuteTime.class));
            var unit = annotation.unit();
            var logTemplate = annotation.template()
                    .replace("$methodName", method.getName())
                    .replace("$executeTime", "\" + (_tFinish.longValue() - _tStart.longValue()) + \"");
            var ctClassLong = ClassPool.getDefault().get(Long.class.getName());
            method.addLocalVariable("_tStart", ctClassLong);
            method.addLocalVariable("_tFinish", ctClassLong);
            method.insertBefore("_tStart = Long.valueOf(" + getTimeFixString(unit) + ");");
            method.insertAfter("_tFinish = Long.valueOf(" + getTimeFixString(unit) + ");");
            method.insertAfter(logTemplate);
        } catch (CannotCompileException | NotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getTimeFixString(LogExecuteTime.TimeUnit unit) {
        return unit == LogExecuteTime.TimeUnit.NANOS
                ? "System.nanoTime()"
                : "System.currentTimeMillis()";
    }

}
