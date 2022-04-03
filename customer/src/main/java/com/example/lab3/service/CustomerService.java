package com.example.lab3.service;

import com.example.lab3.entity.Customer;
import com.example.lab3.event.repository.CustomerEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lab3.repository.CustomerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository repository;
    private CustomerEventRepository eventRepository;

    @Autowired
    public CustomerService(CustomerRepository repository, CustomerEventRepository eventRepository) {
        this.repository = repository;
        this.eventRepository = eventRepository;
    }

    public Optional<Customer> find(int id) {
        return repository.findById(id);
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void create(Customer customer) {
        repository.save(customer);
        eventRepository.create(customer);
    }

    @Transactional
    public void update(Customer customer) {
        repository.save(customer);
    }

    @Transactional
    public void delete(Customer customer) {
        eventRepository.delete(customer);
        repository.delete(customer);
    }


}
