package com.riza.bioskop.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.riza.bioskop.common.error.NotFoundException;
import com.riza.bioskop.common.error.RestrictedAgeException;
import com.riza.bioskop.common.error.TheaterFullyBookedException;
import com.riza.bioskop.controller.dto.response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  @ResponseBody
  public ErrorResponse handle(NotFoundException ex) {
    return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
  }

  @ExceptionHandler(RestrictedAgeException.class)
  @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
  @ResponseBody
  public ErrorResponse handle(RestrictedAgeException ex) {
    return new ErrorResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
  }

  @ExceptionHandler(TheaterFullyBookedException.class)
  @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
  @ResponseBody
  public ErrorResponse handle(TheaterFullyBookedException ex) {
    return new ErrorResponse(ex.getMessage(), HttpStatus.EXPECTATION_FAILED.value());
  }
}
