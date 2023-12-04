package com.riza.bioskop.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.riza.bioskop.repository.CustomerRepository;
import com.riza.bioskop.repository.impl.postgres.entity.Customer;
import com.riza.bioskop.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
  private final CustomerRepository customerRepository;

  public CustomerServiceImpl(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public Customer createNew(String name, Date dob) {
    return customerRepository.insert(new Customer(name, dob));
  }

  @Override
  public Entry<Integer, List<Customer>> getCustomers(Integer page, Integer size) {
    return customerRepository.findAll(page, size);
  }

  @Override
  public Customer getDetailCustomer(Integer id) {
    return customerRepository.findById(id);
  }

  @Override
  public void removeCustomerOfId(Integer id) {
    customerRepository.deleteById(id);
  }

  @Override
  public Customer updateCustomer(Customer customer) {
    return customerRepository.update(customer);
  }

}
