package com.personal.pattern.observer.service;

import com.personal.pattern.observer.annotation.UseObservers;
import com.personal.pattern.observer.event.*;
import com.personal.pattern.observer.model.Department;
import com.personal.pattern.observer.model.Employee;
import com.personal.pattern.observer.model.EmployeeDTO;
import com.personal.pattern.observer.model.Position;
import com.personal.pattern.observer.model.converter.EmployeeConverter;
import com.personal.pattern.observer.notificator.ObservableSubject;
import com.personal.pattern.observer.notificator.ObserverEvent;
import com.personal.pattern.observer.repository.SimpleMemStorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Objects;

/***
 * Service class for Employee
 *
 * This class it's a subject of observer pattern
 * <p>
 * All class which want to be observable should extend ObservableSubject
 * Generic ObserverEvent is the type class that the observers type allowed.
 *
 * The methods that should be observed, should be annotated with @UseObservers with parameter observers
 *
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService extends ObservableSubject<ObserverEvent> {
    private final SimpleMemStorageRepository<Employee,Integer> employeeDataSet;
    private final SimpleMemStorageRepository<Position,Integer> positionDataSet;
    private final SimpleMemStorageRepository<Department,Integer> departmentDataSet;
    private final EmployeeConverter employeeConverter;

    @UseObservers(observers = {EventNew.class, EventSendEmail.class, EventSendSMS.class})
    public void addEmployee(Employee employee) {
        validatePosition(employee.getPositionId());
        validateDepartment(employee.getDepartmentId());
        employeeDataSet.save(employee);
    }

    @UseObservers(observers = {EventGet.class, EventSendSMS.class})
    public EmployeeDTO getEmployee(Integer id) {
        return employeeConverter.toDTO(employeeDataSet.findById(id)
                                                      .orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Employee not found")));
    }

    @UseObservers(observers = {EventUpdate.class, EventSendToAccounting.class, EventSendToManager.class})
    public void updateStatus(Integer id, Boolean active) {
        var employee = employeeConverter.toEntity(getEmployee(id));
        employeeDataSet.save(employee.setActive(active));
    }

    @UseObservers(observers = {EventUpdate.class, EventSendToManager.class})
    public void updatePosition(Integer id, Integer positionId) {
        var employee = employeeConverter.toEntity(getEmployee(id));
        validatePosition(positionId);
        employeeDataSet.save(employee.setPositionId(positionId));
    }

    @UseObservers(observers = {EventUpdate.class, EventSendToManager.class})
    public void updateDepartment(Integer id, Integer departmentId) {
        var employee = employeeConverter.toEntity(getEmployee(id));
        validateDepartment(departmentId);
        employeeDataSet.save(employee.setDepartmentId(departmentId));
    }
    private void validatePosition(Integer positionId) {
        if(Objects.isNull(positionId)) return;
        var pos = positionDataSet.findById(positionId);
        if (pos.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Position not found");
        } else {
            log.info("Position found: {}", pos.get());
        }
    }

    private void validateDepartment(Integer departmentId) {
        if(Objects.isNull(departmentId)) return;
        var dpt = departmentDataSet.findById(departmentId);
        if (dpt.isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Department not found");
        } else {
            log.info("Department found: {}", dpt.get());
        }
    }
}
