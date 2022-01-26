package ru.adm123.logAgent;

import javassist.*;
import ru.adm123.logAgent.annotation.AnnotationHandler;
import ru.adm123.logAgent.annotation.LogExecuteTime;
import ru.adm123.logAgent.annotation.LogExecuteTimeHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

import static ru.adm123.logAgent.util.ClassReflection.getLogAnnotatedMethods;
import static ru.adm123.logAgent.util.ClassReflection.isJavaClass;

/**
 * Created by Dmitry Ushakov at 22.01.2022
 */
public class TransformerAnnotation implements ClassFileTransformer {

    private final AnnotationHandler<LogExecuteTime> logExecuteTimeHandler = new LogExecuteTimeHandler();

    @Override
    public byte[] transform(ClassLoader loader,
                            String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        if (protectionDomain == null || isJavaClass(className)) {
            return classfileBuffer;
        }
        try {
            CtClass transformClass = getCtClass(loader, classfileBuffer);
            addMethodAnnotationHandler(transformClass, logExecuteTimeHandler);
            return transformClass.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classfileBuffer;
    }

    private CtClass getCtClass(ClassLoader loader,
                               byte[] classfileBuffer) throws IOException {
        ClassPool classPool = new ClassPool();
        classPool.appendClassPath(new LoaderClassPath(loader));
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(classfileBuffer)) {
            return classPool.makeClassIfNew(byteArrayInputStream);
        }
    }

    private void addMethodAnnotationHandler(CtClass applicationClass,
                                            AnnotationHandler<?> annotationHandler) {
        getLogAnnotatedMethods(applicationClass, annotationHandler.getAnnotation()).stream()
                .filter(ctMethod -> !ctMethod.getMethodInfo().isConstructor())
                .forEach(annotationHandler::handle);
    }

}
