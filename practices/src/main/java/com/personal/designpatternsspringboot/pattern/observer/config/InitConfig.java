package com.personal.designpatternsspringboot.pattern.observer.config;

import com.personal.designpatternsspringboot.pattern.observer.model.Department;
import com.personal.designpatternsspringboot.pattern.observer.model.Employee;
import com.personal.designpatternsspringboot.pattern.observer.model.Position;
import com.personal.designpatternsspringboot.pattern.observer.util.MemStorageLocal;
import org.springframework.context.annotation.Configuration;

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
        memStorageLocal.initDataSet(Employee.class, Position.class, Department.class);
    }
}
