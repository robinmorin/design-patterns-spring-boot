package com.personal.pattern.observer.notificator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Aspect class to notify observers
 * <p>
 *     This class intercepts the methods annotated with @UseObservers and notify the observers that have been set in annotation of the method
 *     For this practical propose, I'm sending the method name and the parameters of the method.
 *     The parameters are converted to a Map<String, Object> where the key is the parameter name and the value is the parameter value
 *     The method name is the key for get the observers that should be notified
 *
 *     <b>The observers should be it own logic to handle the parameters and the method name to process the event</b>
 *
 */
@Aspect
@Component
public class NotifyObserversAspect {

    ObjectMapper mapper = new ObjectMapper();

    @Pointcut("@annotation(com.personal.designpatternsspringboot.pattern.observer.annotation.UseObservers)")
    public void observersMethods() {}

@AfterReturning("observersMethods()")
public void afterAdvice(JoinPoint joinPoint) {
    var observable = (ObservableSubject<?>) joinPoint.getThis();
    CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
    String[] parameterNames = codeSignature.getParameterNames();
    Object[] args = joinPoint.getArgs();
    Class<?>[] parameterTypes = codeSignature.getParameterTypes();

    Map<String, Object> mapObjects = IntStream.range(0, parameterNames.length)
        .filter(i -> args[i] != null)
        .boxed()
        .collect(Collectors.toMap(
            i -> parameterNames[i],
            i -> isSimpleObject(args[i]) ?
                    parameterTypes[i].cast(args[i]) :
                    mapper.convertValue(args[i], parameterTypes[i])
        ));

    observable.notifyObservers(codeSignature.getName(), mapObjects);
}

    private boolean isSimpleObject(Object obj) {
        if (obj == null) {
            return false;
        }
        Class<?> clazz = obj.getClass();
        return clazz.isPrimitive() ||
               clazz == Boolean.class || clazz == Character.class || clazz == Byte.class ||
               clazz == Short.class   || clazz == Integer.class   || clazz == Long.class ||
               clazz == Float.class   || clazz == Double.class    || clazz == String.class;
    }
}