package com.example.lab3.car.controller;

import com.example.lab3.car.dto.CreateCarRequest;
import com.example.lab3.car.dto.GetCarResponse;
import com.example.lab3.car.dto.GetCarsResponse;
import com.example.lab3.car.entity.Car;
import com.example.lab3.car.service.CarService;
import com.example.lab3.customer.entity.Customer;
import com.example.lab3.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("api/customers/{id}/cars")
public class CustomerCarController {

    private CustomerService customerService;
    private CarService carService;

    @Autowired
    public CustomerCarController(CarService carService, CustomerService customerService){
        this.carService = carService;
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<GetCarsResponse> getCars(@PathVariable("id") int id) {
        Optional<Customer> customer = customerService.find(id);
        return customer.map(value -> ResponseEntity.ok(GetCarsResponse.entityToDtoMapper().apply(carService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("{registration}")
    public ResponseEntity<GetCarResponse> getCar(@PathVariable("id") int id,
                                                       @PathVariable("registration") String registration) {
        return carService.find(id, registration)
                .map(value -> ResponseEntity.ok(GetCarResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
