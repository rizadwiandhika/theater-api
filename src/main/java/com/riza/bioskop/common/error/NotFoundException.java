package com.riza.bioskop.common.error;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String message) {
    super(message);
  }

  public NotFoundException(String message, Throwable t) {
    super(message, t);
  }

  public static NotFoundException because(String message) {
    return new NotFoundException(message);
  }

  public static NotFoundException because(String message, Throwable t) {
    return new NotFoundException(message, t);
  }

}
