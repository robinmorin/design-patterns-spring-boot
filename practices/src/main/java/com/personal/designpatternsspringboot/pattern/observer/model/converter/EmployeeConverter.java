package com.personal.designpatternsspringboot.pattern.observer.model.converter;

import com.personal.designpatternsspringboot.pattern.observer.model.Department;
import com.personal.designpatternsspringboot.pattern.observer.model.Employee;
import com.personal.designpatternsspringboot.pattern.observer.model.EmployeeDTO;
import com.personal.designpatternsspringboot.pattern.observer.model.Position;
import com.personal.designpatternsspringboot.pattern.observer.repository.SimpleMemStorageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

/***
 * Employee Converter
 * <p>
 *     This class is a simple converter class for Employee Entity
 */
@Component
@RequiredArgsConstructor
public class EmployeeConverter {

    private final SimpleMemStorageRepository<Position, Integer> positionRepository;
    private final SimpleMemStorageRepository<Department, Integer> departmentRepository;

    public EmployeeDTO toDTO(Employee employee) {
        return EmployeeDTO.builder()
            .id(employee.getId())
            .fullName(employee.getFullName())
            .email(employee.getEmail())
            .telephone(employee.getTelephone())
            .position(positionRepository.findById(employee.getPositionId()).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Position not found")))
            .department(departmentRepository.findById(employee.getDepartmentId()).orElseThrow(()-> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Department not found")))
            .active(employee.isActive())
            .build();
    }

    public Employee toEntity(EmployeeDTO employeeDTO) {
        return Employee.builder()
            .id(employeeDTO.getId())
            .fullName(employeeDTO.getFullName())
            .email(employeeDTO.getEmail())
            .telephone(employeeDTO.getTelephone())
            .positionId(employeeDTO.getPosition().getId())
            .departmentId(employeeDTO.getDepartment().getId())
            .active(employeeDTO.isActive())
            .build();
    }

}
