package com.personal.pattern.observer.repository;

import com.personal.pattern.observer.model.Employee;
import com.personal.pattern.observer.util.MemStorageLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/***
 * Repository class for Employee
 * <p>
 *     This class is a simple repository class for Employee
 *     It's an implementation of Strategy Pattern of interface SimpleMemStorageRepository
 *     This Strategy is selected in runtime by the generic type of the class
 */
@Repository
@RequiredArgsConstructor
public class EmployeeRepository implements SimpleMemStorageRepository<Employee, Integer> {

    private final MemStorageLocal memStorageLocal;

    public Employee save(Employee newEmployee) {
        return memStorageLocal.saveItem(newEmployee);
    }

    public Optional<Employee> findById(Integer id) {
        return memStorageLocal.getItem(id, getGenericType());
    }
}
