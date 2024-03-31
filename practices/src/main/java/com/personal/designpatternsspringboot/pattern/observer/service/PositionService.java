package com.personal.designpatternsspringboot.pattern.observer.service;

import com.personal.designpatternsspringboot.pattern.observer.annotation.UseObservers;
import com.personal.designpatternsspringboot.pattern.observer.event.EventGet;
import com.personal.designpatternsspringboot.pattern.observer.event.EventNew;
import com.personal.designpatternsspringboot.pattern.observer.event.EventSendToManager;
import com.personal.designpatternsspringboot.pattern.observer.model.Position;
import com.personal.designpatternsspringboot.pattern.observer.notificator.ObservableSubject;
import com.personal.designpatternsspringboot.pattern.observer.notificator.ObserverEvent;
import com.personal.designpatternsspringboot.pattern.observer.repository.SimpleMemStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/***
 * Service class for Position
 *
 * This class it's a subject of observer pattern
 * <p>
 * All class which want to be observable should extend ObservableSubject
 * Generic ObserverEvent is the type class that the observers type allowed.
 *
 * The methods that should be observed, should be annotated with @UseObservers with parameter observers
 *
 */
@Service
@RequiredArgsConstructor
public class PositionService extends ObservableSubject<ObserverEvent> {

    private final SimpleMemStorageRepository<Position, Integer> positionDataSet;

    @UseObservers(observers = {EventNew.class, EventSendToManager.class})
    public void addPosition(Position position) {
        positionDataSet.save(position);
    }

    @UseObservers(observers = {EventGet.class})
    public Position getPosition(Integer id) {
        return positionDataSet.findById(id).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Position not found"));
    }

}
