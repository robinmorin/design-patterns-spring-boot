package com.personal.pattern.facade.service;

import com.personal.pattern.facade.model.Product;

import java.util.List;

/**
 * The InventoryService interface provides the necessary methods for managing inventory.
 */
public interface InventoryService {

    /**
     * Adds a product to the inventory.
     *
     * @param product The product to add.
     * @return The id of the added product.
     */
    Integer addProduct(Product product);

    /**
     * Retrieves a product by its id.
     *
     * @param productId The id of the product to retrieve.
     * @return The Product object representing the product.
     */
    Product getProduct(Integer productId);

    /**
     * Updates the inventory quantity of a product.
     *
     * @param productId The id of the product.
     * @param quantity The new quantity of the product.
     * @return A boolean indicating whether the update was successful.
     */
    boolean updateInventory(Integer productId, int quantity);

    /**
     * Lists all products in the inventory.
     *
     * @return A list of all Product objects in the inventory.
     */
    List<Product> listInventory();

}