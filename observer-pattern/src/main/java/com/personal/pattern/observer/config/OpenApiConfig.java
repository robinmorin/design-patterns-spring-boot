package com.personal.pattern.observer.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/***
 * Open API Configuration
 * <p>
 *     This class is a configuration class for Open API for disposes the Swagger API documentation
 * </p>
 */
@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Observer Pattern API Practice",
        version = "0.0.1-SNAPSHOT",
        description = "Project to practice the Observer Pattern with Spring Boot"
))
public class OpenApiConfig {

}
