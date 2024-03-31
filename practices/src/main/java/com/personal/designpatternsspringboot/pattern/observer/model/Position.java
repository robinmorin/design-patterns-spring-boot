package com.personal.designpatternsspringboot.pattern.observer.model;

import com.personal.simplememstorage.storage.annotations.IdObject;
import lombok.Data;

/***
 * Position Model
 * <p>
 *     This class is an entity class for the Position
 *     The field id is annotated with @IdObject to indicate that this field is the primary key
 */
@Data
public class Position {
    @IdObject
    private Integer id;
    private String positionName;
}
