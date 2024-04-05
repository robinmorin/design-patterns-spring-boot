package com.personal.pattern.facade.service.impl;

import com.personal.pattern.facade.model.Product;
import com.personal.pattern.facade.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {
    public void addInventory(String product, int quantity) {

    }

    public void updateInventory(String product, int quantity) {

    }

    public void changeStatusInventory(String product, boolean active) {

    }

    public List<Product> listInventory() {
        return null;
    }
}
