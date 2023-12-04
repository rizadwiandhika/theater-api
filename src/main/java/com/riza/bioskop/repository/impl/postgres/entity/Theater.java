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
@Table(name = "t_theater")
public class Theater {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String theaterNumber;
  private Integer stock;
  @ManyToOne
  @JoinColumn(name = "FILM_ID")
  @JsonBackReference
  private Film film;

  public Theater() {
  }

  public Theater(String theaterNumber, Film film) {
    this.theaterNumber = theaterNumber;
    this.film = film;
    this.stock = 0;
  }

  public Theater(Integer id, String theaterNumber, Integer stock, Film film) {
    this.id = id;
    this.theaterNumber = theaterNumber;
    this.stock = stock;
    this.film = film;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTheaterNumber() {
    return theaterNumber;
  }

  public void setTheaterNumber(String theaterNumber) {
    this.theaterNumber = theaterNumber;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public Film getFilm() {
    return film;
  }

  public void setFilm(Film film) {
    this.film = film;
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
    Theater other = (Theater) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Theater [id=" + id + ", theaterNumber=" + theaterNumber + ", stock=" + stock + ", film=" + film + "]";
  }

}
