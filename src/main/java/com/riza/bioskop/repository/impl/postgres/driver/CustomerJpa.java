package com.riza.bioskop.repository.impl.postgres.driver;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riza.bioskop.repository.impl.postgres.entity.Customer;

public interface CustomerJpa extends JpaRepository<Customer, Integer> {

}
