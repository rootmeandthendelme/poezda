package ru.tututu.trains.exceptions;

import org.apache.tomcat.websocket.AuthenticationException;
import org.postgresql.util.PSQLException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<Object> handleRuntimeException(RuntimeException e, WebRequest request){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), "");
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({PSQLException.class})
    protected ResponseEntity<Object> handlePSQLException(RuntimeException e, WebRequest request){
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, e.getMessage(), "error during call the database");
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException e, WebRequest request){
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, e.getMessage(), "error during call the database");
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
