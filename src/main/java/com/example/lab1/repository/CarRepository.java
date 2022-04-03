package com.example.lab1.repository;

import com.example.lab1.dataStore.DataStore;
import com.example.lab1.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class CarRepository implements Repository<Car, String> {

    private DataStore store;

    @Autowired
    public CarRepository(DataStore store) {
        this.store = store;
    }

    @Override
    public Optional<Car> find(String registration) {
        return store.findCar(registration);
    }

    @Override
    public List<Car> findAll() {
        return store.findAllCars();
    }

    @Override
    public void create(Car entity) {
        store.createCar(entity);
    }

    @Override
    public void delete(Car entity) {
        store.deleteCar(entity.getRegistration());
    }


}
