package com.example.lab1.service;

import com.example.lab1.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lab1.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository repository;

    @Autowired
    public CarService(CarRepository repository){
        this.repository=repository;
    }

    public Optional<Car> find(String registration){
        return repository.find(registration);
    }

    public List<Car> findAll(){
        return repository.findAll();
    }

    public void create(Car customer){
        repository.create(customer);
    }

    public void delete(String registration){ repository.delete(repository.find(registration).orElseThrow()); }

}
