package com.personal.pattern.facade.service.impl;

import com.personal.pattern.facade.model.Product;
import com.personal.pattern.facade.service.InventoryService;
import com.personal.pattern.facade.util.MemStorageLocal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

/***
 * InventoryServiceImpl class
 * This class implements the InventoryService interface
 * <Read Docs in the interface>
 */
@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final MemStorageLocal memStorageLocal;

    public Integer addProduct(Product product) {
        return memStorageLocal.saveItem(product).getId();
    }

    public Product getProduct(Integer productId) {
        return memStorageLocal.getItem(productId, Product.class).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Product not found"));
    }

    public boolean updateInventory(Integer productId, int quantity) {
        return memStorageLocal.getItem(productId, Product.class)
                .map(product -> {
                    product.setQuantity(product.getQuantity() + quantity);
                    memStorageLocal.saveItem(product);
                    return true;
                }).orElse(false);
    }

    public List<Product> listInventory() {
        return memStorageLocal.getAllItems(Product.class).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,"Not found any product in inventory"));
    }
}
