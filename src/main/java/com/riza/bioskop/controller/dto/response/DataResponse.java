package com.riza.bioskop.controller.dto.response;

public class DataResponse<T> extends BaseResponse {
  private T data;

  public DataResponse(String message, T data) {
    super(message);
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

}
