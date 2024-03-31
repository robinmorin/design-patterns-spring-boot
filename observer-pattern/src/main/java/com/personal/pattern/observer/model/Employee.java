package com.personal.pattern.observer.model;

import com.personal.simplememstorage.storage.annotations.IdObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/***
 * Employee Model
 * <p>
 *     This class is an entity class for the Employee
 *     The field id is annotated with @IdObject to indicate that this field is the primary key
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class Employee {

    @IdObject
    private Integer id;
    private String fullName;
    private String email;
    private String telephone;
    private Integer positionId;
    private Integer departmentId;
    private boolean active;
}
