package com.example.lab3.customer.controller;

import com.example.lab3.customer.dto.CreateCustomerRequest;
import com.example.lab3.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.lab3.customer.service.CustomerService;

import java.util.Optional;

@RestController
@RequestMapping("api/customers")
public class CustomerController {


    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping("")
    public ResponseEntity<Void> createCustomer(@RequestBody CreateCustomerRequest request, UriComponentsBuilder builder) {

        Customer customer = CreateCustomerRequest
                .dtoToEntityMapper()
                .apply(request);
        customerService.create(customer);
        return ResponseEntity.created(builder.pathSegment("api", "customers", "{id}")
                .buildAndExpand(customer.getId()).toUri()).build();

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") int id) {
        Optional<Customer> customer = customerService.find(id);
        if (customer.isPresent()) {
            customerService.delete(customer.get());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }

    }



}
