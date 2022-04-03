package com.example.lab1.dataStore;

import com.example.lab1.serialization.CloningUtility;
import com.example.lab1.entity.Car;
import com.example.lab1.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataStore {
    private Set<Customer> customers = new HashSet<>();
    private Set<Car> cars = new HashSet<>();

    public synchronized List<Customer> findAllCustomers() {
        return new ArrayList<>(customers);
    }


    public synchronized Optional<Customer> findCustomer(int id) {
        return customers.stream()
                .filter(customer -> customer.getId()==(id))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createCustomer(Customer customer) throws IllegalArgumentException {
        findCustomer(customer.getId()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The customer id \"%s\" is not unique", customer.getId()));
                },
                () -> customers.add(CloningUtility.clone(customer)));
    }

    public synchronized void deleteCustomer(int id) throws IllegalArgumentException {
        findCustomer(id).ifPresentOrElse(
                original -> customers.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The customer with id \"%d\" does not exist", id));
                });
    }

    public synchronized void updateCustomer(Customer customer) throws IllegalArgumentException {
        findCustomer(customer.getId()).ifPresentOrElse(
                original -> {
                    customers.remove(original);
                    customers.add(CloningUtility.clone(customer));
                },
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The customer with id \"%d\" does not exist", customer.getId()));
                });
    }


    public synchronized List<Car> findAllCars() {
        return new ArrayList<>(cars);
    }

    public synchronized Optional<Car> findCar(String registration) {
        return cars.stream()
                .filter(car -> car.getRegistration().equals(registration))
                .findFirst()
                .map(CloningUtility::clone);
    }

    public synchronized void createCar(Car car) throws IllegalArgumentException {
        findCar(car.getRegistration()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(
                            String.format("The car registration \"%s\" is not unique", car.getRegistration()));
                },
                () -> cars.add(CloningUtility.clone(car)));
    }

    public synchronized void deleteCar(String registration) throws IllegalArgumentException {
        findCar(registration).ifPresentOrElse(
                original -> cars.remove(original),
                () -> {
                    throw new IllegalArgumentException(
                            String.format("The car with registration \"%s\" does not exist", registration));
                });
    }


}
