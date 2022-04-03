package com.example.lab3.initialization;

import com.example.lab3.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.lab3.service.CustomerService;

import javax.annotation.PostConstruct;

@Component
public class Initializer {

    private final CustomerService customerService;


    @Autowired
    public Initializer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostConstruct
    private synchronized void init() {



        Customer customer1 = Customer.builder().name("Adam").surname("Kowalski").build();
        Customer customer2 = Customer.builder().name("Marcin").surname("Najman").build();
        Customer customer3 = Customer.builder().name("Nikodem").surname("Kownacki").build();


        this.customerService.create(customer1);
        this.customerService.create(customer2);
        this.customerService.create(customer3);



    }

}
