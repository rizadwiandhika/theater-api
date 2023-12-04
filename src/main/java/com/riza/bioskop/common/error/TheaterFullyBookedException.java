package com.riza.bioskop.common.error;

public class TheaterFullyBookedException extends RuntimeException {
  public TheaterFullyBookedException(String message) {
    super(message);
  }

  public TheaterFullyBookedException(String message, Throwable t) {
    super(message, t);
  }

  public static TheaterFullyBookedException because(String message) {
    return new TheaterFullyBookedException(message);
  }

  public static TheaterFullyBookedException because(String message, Throwable t) {
    return new TheaterFullyBookedException(message, t);
  }
}
