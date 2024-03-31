package com.personal.designpatternsspringboot.pattern.observer.model;

import com.personal.simplememstorage.storage.annotations.IdObject;
import lombok.Data;

/***
 * Department Model
 * <p>
 *     This class is an entity class for the Department
 *     The field id is annotated with @IdObject to indicate that this field is the primary key
 */
@Data
public class Department {
    @IdObject
    private Integer id;
    private String departmentName;
}

