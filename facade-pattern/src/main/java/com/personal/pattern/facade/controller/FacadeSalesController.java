package com.personal.pattern.facade.controller;

import com.personal.pattern.facade.facade.SalesFacade;
import com.personal.pattern.facade.model.SalesObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

/***
 * Facade Sales Controller
 * <p>
 *     This class is a controller class for Sales Facade
 *     This class is responsible for handling the Sales requests
 * </p>
 */
@RestController
@RequestMapping("/facade/sales")
@RequiredArgsConstructor
public class FacadeSalesController {

    private final SalesFacade salesFacade;

    @Operation(tags = "Sales", summary = "New Sale add operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
        }
    )
    @Parameter(name = "identification", description = "Identification of Client")
    @PostMapping(value = "/{identification}/new", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createNewSale(@PathVariable String identification, @RequestBody Map<Integer, Integer> prodQuantity) {
        var saleId = salesFacade.createSale(identification, prodQuantity);
        URI location = URI.create("/facade/sales/"+saleId);
        return ResponseEntity.created(location).build();
    }

    @Operation(tags = "Sales", summary = "Getting Sale Info operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SalesObject.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
        }
    )
    @Parameter(name = "saleId", description = "Id of Sale")
    @GetMapping(value = "/{saleId}", produces = "application/json")
    public ResponseEntity<Object> getSale(@PathVariable String saleId) {
        return ResponseEntity.ok(salesFacade.getSale(saleId));
    }

    @Operation(tags = "Sales", summary = "Change Status Sale and reverse the inventory operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SalesObject.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
        }
    )
    @Parameter(name = "saleId", description = "Id of Sale")
    @PutMapping(value = "/{saleId}", produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> cancelSale(@PathVariable String saleId) {
        salesFacade.cancelSale(saleId);
        return ResponseEntity.noContent().build();
    }

    @Operation(tags = "Sales", summary = "List Sales operations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = SalesObject.class)))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
        }
    )
    @GetMapping(value = "/list", produces = "application/json")
    public ResponseEntity<Object> listSales() {
        return ResponseEntity.ok(salesFacade.listSales());
    }


}
