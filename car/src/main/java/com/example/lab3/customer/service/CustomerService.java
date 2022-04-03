package com.example.lab3.customer.service;

import com.example.lab3.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lab3.customer.repository.CustomerRepository;

import javax.transaction.Transactional;
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

//    public List<Customer> findAll() {
//        return repository.findAll();
//    }

    @Transactional
    public void create(Customer customer) {
        repository.save(customer);
    }

//    @Transactional
//    public void update(Customer customer) {
//        repository.save(customer);
//    }

    @Transactional
    public void delete(Customer customer) {
        repository.delete(customer);
    }


}
