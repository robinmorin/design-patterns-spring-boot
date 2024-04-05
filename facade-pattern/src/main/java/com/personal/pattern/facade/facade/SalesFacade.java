package com.personal.pattern.facade.facade;


import com.personal.pattern.facade.model.Product;
import com.personal.pattern.facade.model.SalesObject;

import java.util.List;

public interface SalesFacade {

    String createSale(String identification, List<Product> products);
    boolean cancelSale(String orderId);
    List<SalesObject> listOrders();

}
