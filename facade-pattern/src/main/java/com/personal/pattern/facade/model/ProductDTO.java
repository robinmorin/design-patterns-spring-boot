package com.personal.pattern.facade.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/***
 * Product Model for Add Product
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
