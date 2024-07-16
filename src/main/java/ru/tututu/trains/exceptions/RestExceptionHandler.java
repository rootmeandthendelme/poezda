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
    @ExceptionHandler({AuthenticationException.class})
    protected ResponseEntity<Object> handleAuthenticationException(RuntimeException e, WebRequest request){
        return handleExceptionInternal(e, "\"Authentication error with msg - " + e.getMessage() + "\"", new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }

    @ExceptionHandler({PSQLException.class})
    protected ResponseEntity<Object> handlePSQLException(RuntimeException e, WebRequest request){
        return handleExceptionInternal(e, "\"SQL error with msg - " + e.getMessage() + "\"", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
