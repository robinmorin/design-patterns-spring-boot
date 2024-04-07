package com.personal.pattern.facade.service;

import com.personal.pattern.facade.model.SalesObject;

import java.math.BigDecimal;
import java.util.List;

/**
 * The SalesService interface provides the necessary methods for managing sales.
 */
public interface SalesService {

    /**
     * Creates a sale with the given identification.
     *
     * @param identification The identification of customer
     * @return The id of the created sale.
     */
    String createSale(String identification);

    /**
     * Adds a product to a sale.
     *
     * @param saleId The id of the sale.
     * @param productId The id of the product.
     * @param quantity The quantity of the product.
     * @param price The price of the product.
     */
    void addProductToSale(String saleId, Integer productId, int quantity, BigDecimal price);

    /**
     * Retrieves a sale by its id.
     *
     * @param saleId The id of the sale to retrieve.
     * @return The SalesObject representing the sale.
     */
    SalesObject getSaleById(String saleId);

    /**
     * Updates the status of an order.
     *
     * @param saleId The id of the sale.
     * @param active The new status of the order.
     */
    void updateStatusOrder(String saleId, boolean active);

    /**
     * Lists all sales.
     *
     * @return A list of all SalesObjects.
     */
    List<SalesObject> listSales();
}