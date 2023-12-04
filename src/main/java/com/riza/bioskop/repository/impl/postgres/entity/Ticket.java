package com.riza.bioskop.repository.impl.postgres.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trx_ticket")
public class Ticket {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne
  @JoinColumn(name = "seat_id")
  private Seat seat;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  public Ticket() {
  }

  public Ticket(Seat seat, Customer customer) {
    this.seat = seat;
    this.customer = customer;
  }

  public Ticket(Integer id, Seat seat, Customer customer) {
    this.id = id;
    this.seat = seat;
    this.customer = customer;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Seat getSeat() {
    return seat;
  }

  public void setSeat(Seat seat) {
    this.seat = seat;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

}
