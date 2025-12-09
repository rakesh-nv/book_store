package com.example.book_store.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<Object> responseBuilder(
            String message, HttpStatus httpStatus, Object responseObject
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", httpStatus.value());
        response.put("message", message);
        response.put("data", responseObject);
        return new ResponseEntity<>(response, httpStatus);
    }
       public static ResponseEntity<Object> userResponseBuilder(
            String message, HttpStatus httpStatus,String token, Object responseObject
    ) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", httpStatus.value());
        response.put("message", message);
        response.put("token",token);
        response.put("data", responseObject);
        return new ResponseEntity<>(response, httpStatus);
    }


}
