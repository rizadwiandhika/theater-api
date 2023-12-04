package com.riza.bioskop.service;

import java.util.Date;
import java.util.List;
import java.util.Map.Entry;

import com.riza.bioskop.repository.impl.postgres.entity.Customer;

public interface CustomerService {

  Entry<Integer, List<Customer>> getCustomers(Integer page, Integer size);

  Customer getDetailCustomer(Integer id);

  Customer createNew(String name, Date dob);

  Customer updateCustomer(Customer customer);

  void removeCustomerOfId(Integer id);

}
