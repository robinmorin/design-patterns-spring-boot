package com.personal.pattern.observer.event;

import com.personal.pattern.observer.notificator.ObserverEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/***
 * Event Update
 * <p>
 *     The event is for practice purpose representing a notification of an update method called
 * <p>
 *     This class is an implementation of ObserverEvent
 *     It's an implementation of Observer Pattern along with class ObservableSubject
 */
@Slf4j
public class EventUpdate implements ObserverEvent {
    public <T> Consumer<T> notifyAction() {
        return (T object) ->
                log.info("Has pushed one new event: EventUpdate. Values sent: {}", object.toString());
    }

}
