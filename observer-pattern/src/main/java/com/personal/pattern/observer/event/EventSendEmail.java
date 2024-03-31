package com.personal.pattern.observer.event;

import com.personal.pattern.observer.model.Employee;
import com.personal.pattern.observer.notificator.ObserverEvent;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

/***
 * Event Send Email
 * <p>
 *     The event is for practice purpose representing a notification for sent Email to employee when the method annotated is called
 * <p>
 *     This class is an implementation of ObserverEvent
 *     It's an implementation of Observer Pattern along with class ObservableSubject
 */
@Slf4j
public class EventSendEmail implements ObserverEvent {

    @SuppressWarnings("unchecked")
    public <T> Consumer<T> notifyAction() {
        return (T object) ->{
            String email = ((Map<String,Object>)object).values().stream()
                                                                    .filter(Employee.class::isInstance)
                                                                    .map(Employee.class::cast)
                                                                    .map(Employee::getEmail)
                                                                    .filter(Objects::nonNull)
                                                                    .findFirst()
                                                                  .orElse("[ERROR: No email found]");
            log.info("Has pushed one new event: EventSendEmail. Sending to: {}", email);
        };
    }

}
