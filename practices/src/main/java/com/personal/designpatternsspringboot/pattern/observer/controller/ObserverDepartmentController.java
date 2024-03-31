package com.personal.designpatternsspringboot.pattern.observer.controller;

import com.personal.designpatternsspringboot.pattern.observer.model.Department;
import com.personal.designpatternsspringboot.pattern.observer.model.Employee;
import com.personal.designpatternsspringboot.pattern.observer.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
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
 * Observer Department Controller
 * <p>
 *     This class is a controller class for Department Entity
 *     This class is responsible for handling the Department Entity requests
 * </p>
 */
@RestController
@RequestMapping("/observer/department")
@RequiredArgsConstructor
@Tag(name = "Employee:Department", description = "Practice Employee Observer Controller")
public class ObserverDepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "New Department add operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Void.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
        }
    )
    @PostMapping(name = "/new", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> createNewDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
        URI location = URI.create("/observer/department/" + department.getId());
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Getting Department Info operation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Error.class))),
        }
    )
    @GetMapping(name = "/{id}", produces = "application/json")
    public ResponseEntity<Object> getDepartment(@PathVariable Integer id) {
        return ResponseEntity.ok(departmentService.getDepartment(id));
    }


}
