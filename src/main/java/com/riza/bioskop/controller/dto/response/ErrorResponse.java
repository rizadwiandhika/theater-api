package com.riza.bioskop.controller.dto.response;

public class ErrorResponse extends BaseResponse {
  private Integer code;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public ErrorResponse(String message, Integer code) {
    super(message);
    this.code = code;
  }

}
