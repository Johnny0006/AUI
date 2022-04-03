package com.example.lab1.repository;

import com.example.lab1.dataStore.DataStore;
import com.example.lab1.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class CustomerRepository implements Repository<Customer, Integer> {

    private DataStore store;

    @Autowired
    public CustomerRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Customer> find(Integer id) {
        return store.findCustomer(id);
    }

    @Override
    public List<Customer> findAll() {
        return store.findAllCustomers();
    }

    @Override
    public void create(Customer entity) {
        store.createCustomer(entity);
    }

    @Override
    public void delete(Customer entity) {
        store.deleteCustomer(entity.getId());
    }

    public void update(Customer entity) {
        store.updateCustomer(entity);
    }


}
