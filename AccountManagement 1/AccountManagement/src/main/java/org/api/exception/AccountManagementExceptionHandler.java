package org.api.exception;


import org.api.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AccountManagementExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGeneralException(Exception ex, WebRequest request) {
        return ResponseEntity.status(500).body(new ApiResponse<>("ERROR", "Internal Server Error"));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleNoDataFoundException(NoDataFoundException ex) {
        return ResponseEntity.status(404).body(new ApiResponse<>("ERROR", ex.getMessage()));
    }
}

