package ru.adm123.logAgent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogExecuteTime {

    TimeUnit unit() default TimeUnit.MILLIS;
    String template() default "System.out.println(\"method '$methodName' executed at $executeTime\");";

    enum TimeUnit {
        MILLIS,
        NANOS
    }

}
