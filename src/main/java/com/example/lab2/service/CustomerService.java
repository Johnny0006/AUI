package com.example.lab2.service;

import com.example.lab2.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lab2.repository.CustomerRepository;

import javax.transaction.Transactional;
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
        return repository.findById(id);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Customer create(Customer customer) {
        return repository.save(customer);
    }

    @Transactional
    public void update(Customer customer) {
        repository.save(customer);
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }


}
