//package com.salt.server.exception;
//
//import com.salt.server.exception.dto.Error;
//import com.salt.server.exception.dto.ErrorResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//
//import java.util.List;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler({ Exception.class })
//    public ResponseEntity<ErrorResponse> handleException(final Exception ex) {
//
//        ErrorResponse errorResponse = new ErrorResponse(
//                List.of(new Error(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)));
//        return responseEntity(HttpStatus.INTERNAL_SERVER_ERROR, errorResponse);
//    }
//
//    private ResponseEntity<ErrorResponse> responseEntity(HttpStatus httpStatus, ErrorResponse errorResponse) {
//        return ResponseEntity.status(httpStatus)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(errorResponse);
//    }
//}