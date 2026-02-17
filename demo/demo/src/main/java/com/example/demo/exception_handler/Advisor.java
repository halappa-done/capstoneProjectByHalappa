package com.example.demo.exception_handler;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.exceptions.ResourceNotFoundException;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@RestControllerAdvice
public class Advisor {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){

        HashMap<String, String> map = new HashMap<>();

        map.put("message", ex.getMessage());

        ErrorResponse errorResponse =new ErrorResponse();
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessages(List.of(map));
        //return new ResponseEntity<>("Error : "+ex.getMessage(), HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){

       HashMap<String, String> map = new HashMap<>();
       ex.getBindingResult().getFieldErrors().forEach(er-> map.put(er.getField(), er.getDefaultMessage()));

       ErrorResponse errorResponse =new ErrorResponse();

       errorResponse.setTimeStamp(LocalDateTime.now());
       errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
       errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
       errorResponse.setMessages(List.of(map));

       return  new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleInternalServerErrorException(Exception ex){

        HashMap<String, String> map = new HashMap<>();

        map.put(ex.getMessage(), ex.getStackTrace().toString());

        ErrorResponse errorResponse =new ErrorResponse();
        errorResponse.setTimeStamp(LocalDateTime.now());
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.setMessages(List.of(map));
        //return new ResponseEntity<>("Error : "+ex.getMessage(), HttpStatus.NOT_FOUND);
        return  new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
