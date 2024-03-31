package com.personal.designpatternsspringboot.pattern.observer.service;

import com.personal.designpatternsspringboot.pattern.observer.annotation.UseObservers;
import com.personal.designpatternsspringboot.pattern.observer.event.EventGet;
import com.personal.designpatternsspringboot.pattern.observer.event.EventNew;
import com.personal.designpatternsspringboot.pattern.observer.event.EventSendToManager;
import com.personal.designpatternsspringboot.pattern.observer.model.Department;
import com.personal.designpatternsspringboot.pattern.observer.notificator.ObservableSubject;
import com.personal.designpatternsspringboot.pattern.observer.notificator.ObserverEvent;
import com.personal.designpatternsspringboot.pattern.observer.repository.SimpleMemStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

/***
 * Service class for Department
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
public class DepartmentService extends ObservableSubject<ObserverEvent> {

    private final SimpleMemStorageRepository<Department, Integer> departmentDataSet;

    @UseObservers(observers = {EventNew.class, EventSendToManager.class})
    public void addDepartment(Department department) {
        departmentDataSet.save(department);
    }

    @UseObservers(observers = {EventGet.class})
    public Department getDepartment(Integer id) {
        return departmentDataSet.findById(id).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Department not found"));
    }

}
