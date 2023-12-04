package com.riza.bioskop.controller.dto.request;

public class ReserveTicketRequest {
  private Integer seatId;
  private Integer customerId;

  public ReserveTicketRequest() {
  }

  public ReserveTicketRequest(Integer seatId, Integer customerId) {
    this.seatId = seatId;
    this.customerId = customerId;
  }

  public Integer getSeatId() {
    return seatId;
  }

  public void setSeatId(Integer seatId) {
    this.seatId = seatId;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }

}
