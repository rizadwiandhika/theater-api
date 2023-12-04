package com.riza.bioskop.repository.impl.postgres.entity;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  private Date birthDate;

  public Customer() {
  }

  public Customer(String name, Date date) {
    this.name = name;
    this.birthDate = date;
  }

  public Customer(Integer id, String name, Date birthDate) {
    this.id = id;
    this.name = name;
    this.birthDate = birthDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public Integer getAge() {
    Calendar customerCalendar = Calendar.getInstance();
    Calendar currentCalendar = Calendar.getInstance();

    customerCalendar.setTime(birthDate);
    currentCalendar.setTime(Date.from(Instant.now()));

    return currentCalendar.get(Calendar.YEAR) - customerCalendar.get(Calendar.YEAR);
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
    Customer other = (Customer) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Customer [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
  }

}
