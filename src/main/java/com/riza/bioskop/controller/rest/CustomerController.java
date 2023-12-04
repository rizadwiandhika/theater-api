package com.riza.bioskop.controller.rest;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.riza.bioskop.controller.dto.response.BaseResponse;
import com.riza.bioskop.controller.dto.response.DataResponse;
import com.riza.bioskop.controller.dto.response.PaginationResponse;
import com.riza.bioskop.repository.impl.postgres.entity.Customer;
import com.riza.bioskop.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public ResponseEntity<PaginationResponse<List<Customer>>> getCustomers(
      @RequestParam(name = "page", defaultValue = "0") Integer page,
      @RequestParam(name = "size", defaultValue = "5") Integer size) {
    Entry<Integer, List<Customer>> result = customerService.getCustomers(page, size);
    return ResponseEntity.ok(new PaginationResponse<>("Success", result.getValue(), result.getKey()));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DataResponse<Customer>> getCustomerById(@PathVariable Integer id) {
    Customer result = customerService.getDetailCustomer(id);
    return ResponseEntity.ok(new DataResponse<>("Success", result));
  }

  @PostMapping
  public ResponseEntity<DataResponse<Customer>> createCustomer(@RequestBody Customer customer) {
    Customer result = customerService.createNew(customer.getName(), customer.getBirthDate());
    return ResponseEntity.ok(new DataResponse<>("Success created", result));
  }

  @PutMapping
  public ResponseEntity<DataResponse<Customer>> updateCustomer(@RequestBody Customer customer) {
    Customer result = customerService.updateCustomer(customer);
    return ResponseEntity.ok(new DataResponse<>("Data updated", result));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseResponse> removeCustomerById(@PathVariable Integer id) {
    customerService.removeCustomerOfId(id);
    return ResponseEntity.ok(new BaseResponse("Customer removed"));
  }
}
