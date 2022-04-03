package com.example.lab3.car.service;

import com.example.lab3.car.entity.Car;
import com.example.lab3.customer.entity.Customer;
import com.example.lab3.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lab3.car.repository.CarRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;

    private CustomerRepository customerRepository;

    @Autowired
    public CarService(CarRepository carRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public Optional<Car> find(String registration) {
        return carRepository.findById(registration);
    }

    public Optional<Car> find(Customer customer, String registration) {
        return carRepository.findByRegistrationAndCustomer(registration, customer);
    }

    public Optional<Car> find(int id, String registration) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return carRepository.findByRegistrationAndCustomer(registration, customer.get());
        } else {
            return Optional.empty();
        }
    }


    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findAll(Customer customer) {
        return carRepository.findAllByCustomer(customer);
    }


    @Transactional
    public Car create(Car car) {
        return carRepository.save(car);
    }

    @Transactional
    public void update(Car car) {
        carRepository.save(car);
    }

    @Transactional
    public void delete(String registration) {
        carRepository.deleteById(registration);
    }

}
