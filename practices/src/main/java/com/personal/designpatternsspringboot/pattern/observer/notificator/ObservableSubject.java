package com.personal.designpatternsspringboot.pattern.observer.notificator;

import com.personal.designpatternsspringboot.pattern.observer.annotation.UseObservers;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.BiConsumer;

/***
 * Observable Subject
 * <p>
 *     This class is used to define the Observable Subject
 *     It's an implementation of Observer Pattern acting as Subject
 *     Every class will need be Observable, must be extended from this class
 *
 *     My implementation of Observer Pattern is based on observable methods from the class Subject
 *     The methods must be annotated with @UseObservers and the observers must be defined in the annotation, simplifying the use of the pattern,
 *     avoiding the need to create a list of observers in the class, and automatically subscribes the observers to the list of observers
 *
 * @link<
 * </p>
 * @param <T> ObserverEvent
 */
public abstract class ObservableSubject<T extends ObserverEvent> {
    private Map<String, Set<T>> observers;

    protected ObservableSubject() {
        this.init();
    }

    @SuppressWarnings({"java:S112", "unchecked"})
    private void init(){
        this.observers = new HashMap<>();
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        var stackElement = stackTraceElements[3];
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            Class<?> clazz = classLoader.loadClass(stackElement.getClassName());
            Arrays.stream(clazz.getMethods()).filter(md -> md.getAnnotation(UseObservers.class) != null)
                    .forEach(md -> {
                        String methodName = md.getName();
                        Class<? extends ObserverEvent>[] obsInMethod = md.getAnnotation(UseObservers.class).observers();
                        for (Class<? extends ObserverEvent> observer : obsInMethod) {
                            try {
                                suscribe().accept(methodName, (T) observer.getConstructor().newInstance());
                            } catch (InstantiationException | IllegalAccessException |
                                     InvocationTargetException | NoSuchMethodException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error initializing ObservableSubject and Observers. Notification is disable",e);
        }
    }
    protected BiConsumer<String,T> suscribe(){
    return (methodName,observer) ->
        observers.compute(methodName, (k,v)-> {
            var vNew = Optional.ofNullable(v).orElse(new HashSet<>());
            vNew.add(observer);
            return vNew;
        });
    }

    protected BiConsumer<String,T> unsuscribe(){
        return (methodName,observer) ->
            observers.computeIfPresent(methodName, (k,v)-> {
                v.remove(observer);
                return v;
            });
    }
    protected <O> void notifyObservers(String methodName, O object){
        observers.get(methodName).stream()
                                 .filter(Objects::nonNull)
                                 .forEach(obs -> obs.notifyAction().accept(object));
    }

}
