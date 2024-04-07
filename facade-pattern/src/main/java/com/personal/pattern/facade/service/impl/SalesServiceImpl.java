package com.personal.pattern.facade.service.impl;

import com.personal.pattern.facade.model.SalesObject;
import com.personal.pattern.facade.service.SalesService;
import com.personal.pattern.facade.util.MemStorageLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.List;

/***
 * SalesServiceImpl class
 * This class implements the SalesService interface
 * <Read Docs in the interface>
 */
@Service
@RequiredArgsConstructor
public class SalesServiceImpl implements SalesService {

    private final MemStorageLocal memStorageLocal;
    private static final String PATTERN_SALE = "SLS%s%08d";

    public String createSale(String identification) {
        var lastSaleId = Integer.valueOf(memStorageLocal.getLastItem(SalesObject.class)
                .map(sale -> {
                    var slsId = ((String)sale);
                    return slsId.substring (slsId.length() - 8);
                }).orElse("0"));
        var newId = String.format(PATTERN_SALE, identification, lastSaleId + 1);
        var salesObject = new SalesObject(newId, identification);
        memStorageLocal.saveItem(salesObject);
        return newId;
    }

    public void addProductToSale(String saleId, Integer productId, int quantity, BigDecimal price) {
        var slsObj = memStorageLocal.getItem(saleId, SalesObject.class).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Sale not found"));
        slsObj.addProduct(productId,quantity, price);
        memStorageLocal.saveItem(slsObj);
    }

    public SalesObject getSaleById(String saleId) {
        return memStorageLocal.getItem(saleId, SalesObject.class).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Sale not found"));
    }

    public void updateStatusOrder(String saleId, boolean active) {
        memStorageLocal.getItem(saleId, SalesObject.class).ifPresent(salesObject -> {
            salesObject.setActive(active);
            memStorageLocal.saveItem(salesObject);
        });
    }

    public List<SalesObject> listSales() {
        var listSales = memStorageLocal.getAllItems(SalesObject.class);
        if (listSales.isEmpty() || listSales.get().isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND,"Not found any sale");
        }
        return listSales.get();
    }
}
