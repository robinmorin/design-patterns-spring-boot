package com.personal.pattern.facade.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

/***
 * Control Advice Handler
 * <p>
 *     This class is a controller advice class for handling exceptions
 * </p>
 */
@ControllerAdvice
public class ControlAdviceHandler {

    ObjectMapper mapper = new ObjectMapper();

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleException(HttpClientErrorException e, WebRequest request)  {
        return ResponseEntity.status(e.getStatusCode()).body(mapper.createObjectNode().put("result", e.getStatusText()));
    }

}
