package com.riza.bioskop.repository.impl.postgres.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.riza.bioskop.common.constant.RatingEnum;

@Entity
@Table(name = "t_rating")
public class Rating {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Enumerated(EnumType.STRING)
  private RatingEnum code;
  private String description;

  public Rating() {
  }

  public Rating(RatingEnum code, String description) {
    this.code = code;
    this.description = description;
  }

  public Rating(Integer id, RatingEnum code, String description) {
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public RatingEnum getCode() {
    return code;
  }

  public void setCode(RatingEnum code) {
    this.code = code;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((code == null) ? 0 : code.hashCode());
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
    Rating other = (Rating) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (code == null) {
      if (other.code != null)
        return false;
    } else if (!code.equals(other.code))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Rating [id=" + id + ", code=" + code + ", description=" + description + "]";
  }

}
