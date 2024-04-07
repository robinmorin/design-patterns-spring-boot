package com.personal.pattern.facade.facade;

import com.personal.pattern.facade.model.SalesObject;

import java.util.List;
import java.util.Map;

/**
 * The SalesFacade interface it is a facade that provides a simplified interface to the sales subsystem.
 * Facade defines a higher-level interface that makes the subsystem easier to use.
 */
public interface SalesFacade {

    /**
     * Creates a sale with the given identification and products.
     *
     * @param identification The identification of the sale.
     * @param products A map where the key is the product id and the value is the quantity.
     * @return The id of the created sale.
     */
    String createSale(String identification, Map<Integer, Integer> products);

    /**
     * Retrieves a sale by its id.
     *
     * @param saleId The id of the sale to retrieve.
     * @return The SalesObject representing the sale.
     */
    SalesObject getSale(String saleId);

    /**
     * Cancels a sale by its id.
     *
     * @param saleId The id of the sale to cancel.
     */
    void cancelSale(String saleId);

    /**
     * Lists all sales.
     *
     * @return A list of all SalesObjects.
     */
    List<SalesObject> listSales();

}
