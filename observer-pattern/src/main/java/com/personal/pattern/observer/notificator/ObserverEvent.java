package com.personal.pattern.observer.notificator;

import java.util.function.Consumer;

/***
 * Observer Event Interface
 * <p>
 *     This interface is used to define the contract of the Observer Event
 *     It's an implementation of Observer Pattern along with class ObservableSubject
 */
public interface ObserverEvent {

    <T> Consumer<T> notifyAction();

}
