package com.personal.pattern.facade.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



@RequiredArgsConstructor
public class SalesObject {
    private final String orderId;
    private final String identification;
    private final List<Product> products = new ArrayList<>();

    @Setter
    private boolean active;

    @Getter
    private BigDecimal total;

    public boolean addProduct(Product product) {
        var addResult = products.add(product);
        calculateTotal();
        return addResult;
    }
    private void calculateTotal() {
        total = products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
