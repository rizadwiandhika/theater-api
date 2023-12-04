package com.riza.bioskop.common.error;

public class RestrictedAgeException extends RuntimeException {

  public RestrictedAgeException(String message) {
    super(message);
  }

  public RestrictedAgeException(String message, Throwable t) {
    super(message, t);
  }

  public static RestrictedAgeException because(String message) {
    return new RestrictedAgeException(message);
  }

  public static RestrictedAgeException because(String message, Throwable t) {
    return new RestrictedAgeException(message, t);
  }
}
