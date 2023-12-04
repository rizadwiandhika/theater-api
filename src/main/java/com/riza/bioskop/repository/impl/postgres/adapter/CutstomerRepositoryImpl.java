package com.riza.bioskop.repository.impl.postgres.adapter;

import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.riza.bioskop.common.error.NotFoundException;
import com.riza.bioskop.repository.CustomerRepository;
import com.riza.bioskop.repository.impl.postgres.driver.CustomerJpa;
import com.riza.bioskop.repository.impl.postgres.entity.Customer;

@Repository
public class CutstomerRepositoryImpl implements CustomerRepository {

  private static final String CUSTOMER_NOT_FOUND_OF_ID = "Customer not found of ID: ";
  private final CustomerJpa customerJpa;

  public CutstomerRepositoryImpl(CustomerJpa repo) {
    this.customerJpa = repo;
  }

  @Override
  public Customer insert(Customer entity) {
    entity.setId(null);
    return customerJpa.save(entity);
  }

  @Override
  public void deleteById(Integer id) {
    if (!customerJpa.findById(id).isPresent()) {
      throw NotFoundException.because(CUSTOMER_NOT_FOUND_OF_ID + id);
    }
    customerJpa.deleteById(id);
  }

  @Override
  public Entry<Integer, List<Customer>> findAll(Integer page, Integer size) {
    Page<Customer> result = customerJpa.findAll(PageRequest.of(page, size));
    return new SimpleEntry<>(result.getTotalPages(), result.getContent());
  }

  @Override
  public Customer findById(Integer id) {
    return customerJpa.findById(id).orElseThrow(() -> NotFoundException.because(CUSTOMER_NOT_FOUND_OF_ID + id));
  }

  @Override
  public Customer update(Customer entity) throws NotFoundException {
    if (!customerJpa.findById(entity.getId()).isPresent()) {
      throw NotFoundException.because(CUSTOMER_NOT_FOUND_OF_ID + entity.getId());
    }

    return customerJpa.save(entity);
  }

}
