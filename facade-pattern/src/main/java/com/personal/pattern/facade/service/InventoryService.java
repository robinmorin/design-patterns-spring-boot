package com.personal.pattern.facade.service;

import com.personal.pattern.facade.model.Product;

import java.util.List;

public interface InventoryService {

    void addInventory(String product, int quantity);
    void updateInventory(String product, int quantity);
    void changeStatusInventory(String product, boolean active);
    List<Product> listInventory();

}
