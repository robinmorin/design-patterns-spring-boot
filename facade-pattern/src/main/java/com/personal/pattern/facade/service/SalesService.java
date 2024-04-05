package com.personal.pattern.facade.service;

import com.personal.pattern.facade.model.SalesObject;

import java.util.List;

public interface SalesService {
        String createOrder(String identification);
        boolean addProductToOrder(String product, int quantity);
        SalesObject getOrderById(String orderId);
        boolean cancelOrder(String orderId);
        List<SalesObject> listOrders();
}
