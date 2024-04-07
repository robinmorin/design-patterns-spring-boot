package com.personal.pattern.facade.facade.impl;


import com.personal.pattern.facade.facade.SalesFacade;
import com.personal.pattern.facade.model.SalesObject;
import com.personal.pattern.facade.service.InventoryService;
import com.personal.pattern.facade.service.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/***
 * SalesFacadeImpl class
 * This class implements the SalesFacade interface
 */
@Service
@RequiredArgsConstructor
public class SalesFacadeImpl implements SalesFacade {

        private final SalesService salesService;
        private final InventoryService inventoryService;

    /**
     * This method creates a sale. Simplifying the complex process of creating a sale.
     * @param identification The identification of the sale.
     * @param products A map where the key is the product id and the value is the quantity.
     * @return
     */
    public String createSale(String identification, Map<Integer, Integer> products) {
        var saleId = salesService.createSale(identification);
        products.forEach((productId, quantity) -> {
            var product = inventoryService.getProduct(productId);
            salesService.addProductToSale(saleId, productId, quantity, product.getPrice());
            updateInventory(productId, -quantity);
        });
        return saleId;
    }

    private void updateInventory(Integer productId, Integer quantity) {
        inventoryService.updateInventory(productId, quantity);
    }

    public SalesObject getSale(String saleId) {
        return salesService.getSaleById(saleId);
    }

    /***
     * This method cancels a sale by its id. Simplifying the complex process of canceling a sale.
     * @param saleId The id of the sale to cancel.
     */
    public void cancelSale(String saleId) {
        salesService.updateStatusOrder(saleId, false);
        var sale = salesService.getSaleById(saleId);
        sale.getSaleItems().forEach(saleItem -> inventoryService.updateInventory(saleItem.productId(), saleItem.quantity()));
    }

     public List<SalesObject> listSales() {
        return salesService.listSales();
    }

}
