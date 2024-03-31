package com.personal.pattern.observer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/***
 * Employee DTO
 * <p>
 *     This class is a simple DTO class for Employee Entity
 */
@Data
@Builder
@AllArgsConstructor
public class EmployeeDTO {

    private Integer id;
    private String fullName;
    private String email;
    private String telephone;
    private Position position;
    private Department department;
    private boolean active;
}
