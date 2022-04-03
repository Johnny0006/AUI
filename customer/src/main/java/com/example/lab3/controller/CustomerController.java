package com.example.lab3.controller;

import com.example.lab3.dto.*;
import com.example.lab3.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.lab3.service.CustomerService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("api/customers")
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }


    @GetMapping
    public ResponseEntity<GetCustomersResponse> getCustomers() {
        List<Customer> all = customerService.findAll();
        Function<Collection<Customer>, GetCustomersResponse> mapper = GetCustomersResponse.entityToDtoMapper();
        GetCustomersResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCustomerResponse> getCustomer(@PathVariable("id") int id) {
        return customerService.find(id)
                .map(value -> ResponseEntity
                        .ok(GetCustomerResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity
                        .notFound()
                        .build());

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

    @PutMapping("{id}")
    public ResponseEntity<Void> updateCustomer(@RequestBody UpdateCustomerRequest request, @PathVariable("id") int id) {
        Optional<Customer> customer = customerService.find(id);
        if (customer.isPresent()) {
            UpdateCustomerRequest.dtoToEntityUpdater().apply(customer.get(), request);
            customerService.update(customer.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
