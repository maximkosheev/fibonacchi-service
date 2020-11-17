package ru.monsterdev.fibonacciservice.controllers.advice;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class AdviceExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status,
      WebRequest request) {
    log.error(ex.getMessage(), ex);
    Error error = new Error();
    error.setTimestamp(System.currentTimeMillis());
    error.setMessage(ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(value = {Exception.class})
  public ResponseEntity<Error> handlerException(Exception ex) {
    log.error(ex.getMessage(), ex);
    Error error = new Error();
    error.setTimestamp(System.currentTimeMillis());
    error.setMessage(ex.getMessage());
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }

  @Data
  @NoArgsConstructor
  private class Error {
    private long timestamp;
    private String message;
  }
}
