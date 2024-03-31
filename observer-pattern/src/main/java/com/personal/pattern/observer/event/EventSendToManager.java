package com.personal.pattern.observer.event;

import com.personal.pattern.observer.notificator.ObserverEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/***
 * Event Send To Manager
 * <p>
 *     The event is for practice purpose representing a notification for sent to manager any information when the method annotated is called
 * <p>
 *     This class is an implementation of ObserverEvent
 *     It's an implementation of Observer Pattern along with class ObservableSubject
 */
@Slf4j
public class EventSendToManager implements ObserverEvent {
    public <T> Consumer<T> notifyAction() {
        return (T object) ->
                log.info("Has pushed one new event: EventSendToManager. Values sent: {}", object.toString());
    }

}
