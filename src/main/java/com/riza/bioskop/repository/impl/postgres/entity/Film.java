package com.riza.bioskop.repository.impl.postgres.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "t_film")
public class Film {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;
  private Integer duration;
  private Date showDate;
  private Integer price;
  @ManyToOne
  @JoinColumn(name = "RATING_ID")
  @JsonBackReference
  private Rating rating;

  public Film() {
  }

  public Film(String title, Integer duration, Date showDate, Integer price, Rating rating) {
    this.title = title;
    this.duration = duration;
    this.showDate = showDate;
    this.price = price;
    this.rating = rating;
  }

  public Film(Integer id, String title, Integer duration, Date showDate, Integer price, Rating rating) {
    this.id = id;
    this.title = title;
    this.duration = duration;
    this.showDate = showDate;
    this.price = price;
    this.rating = rating;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getDuration() {
    return duration;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }

  public Date getShowDate() {
    return showDate;
  }

  public void setShowDate(Date showDate) {
    this.showDate = showDate;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Rating getRating() {
    return rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
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
    Film other = (Film) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Fillm [id=" + id + ", title=" + title + ", duration=" + duration + ", showDate=" + showDate + ", price="
        + price + ", rating=" + rating + "]";
  }

}
