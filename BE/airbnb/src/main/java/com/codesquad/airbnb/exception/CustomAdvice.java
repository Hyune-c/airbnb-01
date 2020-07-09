package com.codesquad.airbnb.exception;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomAdvice {

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public Object handleException(Exception e) {
    return e.getMessage();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return e.getMessage();
  }

  @ExceptionHandler(MalformedJwtException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Object handleMalformedJwtException(MalformedJwtException e) {
    return e.getMessage();
  }
}
