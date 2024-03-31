package com.personal.designpatternsspringboot.pattern.observer.event;

import com.personal.designpatternsspringboot.pattern.observer.notificator.ObserverEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/***
 * Event Send To Accounting
 * <p>
 *     The event is for practice purpose representing a notification for sent to accounting any information when the method annotated is called
 * <p>
 *     This class is an implementation of ObserverEvent
 *     It's an implementation of Observer Pattern along with class ObservableSubject
 */
@Slf4j
public class EventSendToAccounting implements ObserverEvent {
    public <T> Consumer<T> notifyAction() {
        return (T object) ->
                log.info("Has pushed one new event: EventSendToAccounting. Values sent: {}", object.toString());
    }

}
