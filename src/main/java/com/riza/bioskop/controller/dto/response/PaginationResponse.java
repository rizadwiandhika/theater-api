package com.riza.bioskop.controller.dto.response;

public class PaginationResponse<T> extends DataResponse<T> {
  private Integer totalPage;

  public PaginationResponse(String message, T data, Integer totalPage) {
    super(message, data);
    this.totalPage = totalPage;
  }

  public Integer getTotalPage() {
    return totalPage;
  }

  public void setTotalPage(Integer totalPage) {
    this.totalPage = totalPage;
  }

}
