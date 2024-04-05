package com.personal.pattern.facade.service.impl;

import com.personal.pattern.facade.model.SalesObject;
import com.personal.pattern.facade.service.SalesService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {
    public String createOrder(String identification) {
        return null;
    }

    public boolean addProductToOrder(String product, int quantity) {
        return false;
    }

    public SalesObject getOrderById(String orderId) {
        return null;
    }

    public boolean cancelOrder(String orderId) {
        return false;
    }

    public List<SalesObject> listOrders() {
        return null;
    }
}
