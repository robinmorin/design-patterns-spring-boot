package com.personal.pattern.observer.repository;

import com.personal.pattern.observer.model.Department;
import com.personal.pattern.observer.util.MemStorageLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/***
 * Repository class for Department
 * <p>
 *     This class is a simple repository class for Department
 *     It's an implementation of Strategy Pattern of interface SimpleMemStorageRepository
 *     This Strategy is selected in runtime by the generic type of the class
 */
@Repository
@RequiredArgsConstructor
public class DepartmentRepository implements SimpleMemStorageRepository<Department, Integer> {

    private final MemStorageLocal memStorageLocal;

    public Department save(Department newDepartment) {
        return memStorageLocal.saveItem(newDepartment);
    }

    public Optional<Department> findById(Integer id) {
        return memStorageLocal.getItem(id, getGenericType());
    }
}
