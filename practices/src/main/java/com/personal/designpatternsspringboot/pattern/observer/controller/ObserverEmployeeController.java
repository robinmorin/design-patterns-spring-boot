package com.personal.designpatternsspringboot.pattern.observer.controller;

import com.personal.designpatternsspringboot.pattern.observer.model.Employee;
import com.personal.designpatternsspringboot.pattern.observer.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/***
 * Observer Employee Controller
 * <p>
 *     This class is a controller class for Employee Entity
 *     This class is responsible for handling the Employee Entity requests
 * </p>
 */
@RestController
@RequestMapping("/observer/employee")
@RequiredArgsConstructor
@Tag(name = "Observer:Employee", description = "Practice Employee Observer Controller")
public class ObserverEmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "New Employee add operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
        }
    )
    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createNewEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        URI location = URI.create("/observer/Employee/" + employee.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Getting Employee Info operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
        }
    )
    @Parameter(name = "id", description = "Id of Employee")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getEmployee(@PathVariable Integer id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @Operation(summary = "Updating Employee Active Status operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
        }
    )
    @Parameter(name = "id", description = "Id of Employee")
    @Parameter(name = "active", description = "Status of Employee")
    @PatchMapping(value = "/{id}/status", produces = "application/json")
    public ResponseEntity<Object> updateStatusEmployee(@PathVariable Integer id, @RequestParam Boolean active) {
        employeeService.updateStatus(id, active);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Update Employee Position operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
        }
    )
    @Parameter(name = "id", description = "Id of Employee")
    @Parameter(name = "positionId", description = "Existing Position Id")
    @PatchMapping(value = "/{id}/position/{positionId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updatePositionEmployee(@PathVariable Integer id, @PathVariable Integer positionId) {
        employeeService.updatePosition(id, positionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Update Employee Department operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
    }
    )
    @Parameter(name = "id", description = "Id of Employee")
    @Parameter(name = "departmentId", description = "Existing Department Id")
    @PatchMapping(value = "/{id}/department/{departmentId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateDepartmentEmployee(@PathVariable Integer id, @PathVariable Integer departmentId) {
        employeeService.updateDepartment(id, departmentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
