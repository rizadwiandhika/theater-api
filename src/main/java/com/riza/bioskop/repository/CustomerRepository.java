package com.riza.bioskop.repository;

import com.riza.bioskop.repository.impl.postgres.entity.Customer;

public interface CustomerRepository extends CrudPaginationRepository<Customer, Integer> {

}
