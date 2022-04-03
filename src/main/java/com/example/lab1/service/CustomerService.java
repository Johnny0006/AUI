package com.example.lab1.service;

import com.example.lab1.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lab1.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository repository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public Optional<Customer> find(int id) {
        return repository.find(id);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public void create(Customer customer) {
        repository.create(customer);
    }

    public void delete(int id) {
        repository.delete(repository.find(id).orElseThrow());
    }

    public void update(Customer customer) {
        repository.update(customer);
    }


}
