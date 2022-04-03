package com.example.lab2.service;

import com.example.lab2.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lab2.repository.CarRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository repository;

    @Autowired
    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public Optional<Car> find(String registration) {
        return repository.findById(registration);
    }

    public List<Car> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Car create(Car customer) {
        return repository.save(customer);
    }

    @Transactional
    public void update(Car customer) {
        repository.save(customer);
    }

    @Transactional
    public void delete(String registration) {
        repository.deleteById(registration);
    }

}
