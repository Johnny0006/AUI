package com.example.lab3.initialization;

import com.example.lab3.car.entity.Car;
import com.example.lab3.customer.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.lab3.car.service.CarService;
import com.example.lab3.customer.service.CustomerService;

import javax.annotation.PostConstruct;

@Component
public class Initializer {

    private final CustomerService customerService;

    private final CarService carService;

    @Autowired
    public Initializer(CustomerService customerService, CarService carService) {
        this.customerService = customerService;
        this.carService = carService;
    }

    @PostConstruct
    private synchronized void init() {



        Customer customer1 = Customer.builder().build();
        Customer customer2 = Customer.builder().build();
        Customer customer3 = Customer.builder().build();

        this.customerService.create(customer1);
        this.customerService.create(customer2);
        this.customerService.create(customer3);

        Car car1 = Car.builder().registration("PWR2421").mark("Fiat").productionYear(2008).customer(customer1).build();
        Car car2 = Car.builder().registration("ONY8550").mark("Nissan").productionYear(2012).customer(customer2).build();
        Car car3 = Car.builder().registration("PKR5924").mark("Audi").productionYear(2015).customer(customer1).build();
        Car car4 = Car.builder().registration("ZSD6450").mark("Bmw").productionYear(2019).customer(customer3).build();
        Car car5 = Car.builder().registration("BMN3148").mark("Suzuki").productionYear(2003).customer(customer2).build();


        this.carService.create(car1);
        this.carService.create(car2);
        this.carService.create(car3);
        this.carService.create(car4);
        this.carService.create(car5);


    }

}
