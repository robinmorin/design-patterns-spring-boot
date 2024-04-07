package com.personal.pattern.facade.model;

import com.personal.simplememstorage.storage.annotations.IdObject;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***
 * Sales Object Entity for Sales
 */
@RequiredArgsConstructor
public class SalesObject {
    @IdObject
    @Getter
    public final String saleId;
    private final String identification;
    private final List<SaleItem> saleItemImpls = new ArrayList<>();

    @Setter
    public boolean active = true;

    @Getter
    private BigDecimal total;

    public void addProduct(Integer productId, int quantity, BigDecimal price) {
        saleItemImpls.add(SaleItemImpl.builder().productId(productId).quantity(quantity).price(price).build());
        calculateTotal();
    }

    public List<SaleItem> getSaleItems() {
        return Collections.unmodifiableList(saleItemImpls);
    }

    private void calculateTotal() {
        total = saleItemImpls.stream().map(saleItem -> saleItem.price().multiply(BigDecimal.valueOf(saleItem.quantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Builder
    private record SaleItemImpl(Integer productId, int quantity, BigDecimal price) implements SaleItem {
    }

    public interface SaleItem {
        Integer productId();
        int quantity();
        BigDecimal price();
    }

}
