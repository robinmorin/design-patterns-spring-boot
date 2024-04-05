package com.personal.pattern.facade.facade.impl;


import com.personal.pattern.facade.facade.SalesFacade;
import com.personal.pattern.facade.model.Product;
import com.personal.pattern.facade.model.SalesObject;
import com.personal.pattern.facade.service.InventoryService;
import com.personal.pattern.facade.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesFacadeImpl implements SalesFacade {

        private final SalesService salesService;
        private final InventoryService inventoryService;

        public String createSale(String identification, List<Product> products) {
            return salesService.createOrder(identification);
        }

        public boolean cancelSale(String orderId) {
          //  salesService.addProductToOrder(product, quantity);
            return true;
        }

         public List<SalesObject> listOrders() {
            return salesService.listOrders();
        }

}
