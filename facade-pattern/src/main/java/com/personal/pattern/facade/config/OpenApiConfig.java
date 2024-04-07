package com.personal.pattern.facade.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

/***
 * Open API Configuration
 * <p>
 *     This class is a configuration class for Open API for disposes the Swagger API documentation
 * </p>
 */
@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Facade Pattern API Practice",
        version = "1.0.0",
        description = "Project to practice the Facade Pattern with Spring Boot"
))
@Tag(name = "Product", description = "Operation for Product")
@Tag(name = "Inventory", description = "Operation for Inventory")
@Tag(name = "Sales", description = "Practice Sales Controller")
public class OpenApiConfig {

}
