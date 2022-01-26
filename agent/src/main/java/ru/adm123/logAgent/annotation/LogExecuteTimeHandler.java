package ru.adm123.logAgent.annotation;

import javassist.*;

/**
 * @author Dmitry Ushakov at 25.01.2022
 */
public class LogExecuteTimeHandler implements AnnotationHandler<LogExecuteTime> {

    @Override
    public Class<LogExecuteTime> getAnnotation() {
        return LogExecuteTime.class;
    }

    @Override
    public void handle(CtMethod method) {
        try {
            var ctClassLong = ClassPool.getDefault().get(Long.class.getName());
            method.addLocalVariable("_tStart", ctClassLong);
            method.addLocalVariable("_tFinish", ctClassLong);
            method.insertBefore("_tStart = Long.valueOf(System.currentTimeMillis());");
            method.insertAfter("_tFinish = Long.valueOf(System.currentTimeMillis());");
            method.insertAfter("System.out.println(\"method '" + method.getName() + "' executed at \" + (_tFinish.longValue() - _tStart.longValue()) + \" ms\");");
        } catch (CannotCompileException | NotFoundException e) {
            e.printStackTrace();
        }
    }

}
