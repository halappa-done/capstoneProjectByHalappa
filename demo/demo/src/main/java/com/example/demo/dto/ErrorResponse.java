package com.example.demo.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ErrorResponse {
    private LocalDateTime timeStamp;
    private int statusCode;
    private HttpStatus httpStatus;
    private List<Map<String, String>> messages;

    public ErrorResponse() {
    }

    public ErrorResponse(LocalDateTime timeStamp, int statusCode, HttpStatus httpStatus, List<Map<String, String>> messages) {
        this.timeStamp = timeStamp;
        this.statusCode = statusCode;
        this.httpStatus = httpStatus;
        this.messages = messages;
    }
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public List<Map<String, String>> getMessages() {
        return messages;
    }

    public void setMessages(List<Map<String, String>> messages) {
        this.messages = messages;
    }
}