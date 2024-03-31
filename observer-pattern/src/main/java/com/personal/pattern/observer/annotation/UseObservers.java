package com.personal.pattern.observer.annotation;

import com.personal.pattern.observer.notificator.ObserverEvent;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/***
 * Use Observers annotation
 * <p>
 *     This annotation is used to define the observers that will be used in the method of the any class extends from ObservableSubject
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UseObservers {
    Class<? extends ObserverEvent>[] observers();
}
