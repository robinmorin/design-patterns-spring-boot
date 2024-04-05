package com.personal.pattern.facade.config;

import com.personal.pattern.facade.model.Product;
import com.personal.pattern.facade.model.SalesObject;
import com.personal.pattern.facade.util.MemStorageLocal;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

/***
 * Init Config
 * <p>
 *     This class is a configuration class for initializing the data set,
 *     if the data set not initialized yet, it will be initialized when any repository implementation try to access it
 *     But this initialization is the best practices.
 * </p>
 */
@Configuration
public class InitConfig {

    public InitConfig(MemStorageLocal memStorageLocal) {
        memStorageLocal.initDataSet(Product.class, SalesObject.class);
        populateDataset(memStorageLocal);
    }

    private void populateDataset(MemStorageLocal memStorageLocal) {
        for(int i = 0; i<10; i++) {
            var prod = Product.builder().id(String.valueOf(i))
                                        .name("Product "+i)
                                        .description("Product "+i+" Description")
                                        .price(BigDecimal.valueOf(100))
                                        .quantity(100)
                                    .build();
            memStorageLocal.saveItem(prod);
        }
    }

}
