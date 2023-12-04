package com.riza.bioskop.repository.impl.postgres.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "t_seat")
public class Seat {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String seatNumber;
  @ManyToOne
  @JoinColumn(name = "THEATER_ID")
  @JsonBackReference
  private Theater theater;

  public Seat() {
  }

  public Seat(String seatNumber, Theater theater) {
    this.seatNumber = seatNumber;
    this.theater = theater;
  }

  public Seat(Integer id, String seatNumber, Theater theater) {
    this.id = id;
    this.seatNumber = seatNumber;
    this.theater = theater;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(String seatNumber) {
    this.seatNumber = seatNumber;
  }

  public Theater getTheater() {
    return theater;
  }

  public void setTheater(Theater theater) {
    this.theater = theater;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Seat other = (Seat) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Seat [id=" + id + ", seatNumber=" + seatNumber + ", theater=" + theater + "]";
  }

}
