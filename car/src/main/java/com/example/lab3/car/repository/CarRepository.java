package com.example.lab3.car.repository;

import com.example.lab3.car.entity.Car;
import com.example.lab3.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {

    Optional<Car> findByRegistrationAndCustomer(String registration, Customer customer);

    List<Car> findAllByCustomer(Customer customer);
}
