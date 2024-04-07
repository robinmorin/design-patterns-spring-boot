package com.personal.pattern.facade.model;

import com.personal.simplememstorage.storage.annotations.IdObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/***
 * Product Entity
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @IdObject
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
}
