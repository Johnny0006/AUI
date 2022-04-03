package com.example.lab2.controller;

import com.example.lab2.dto.CreateCarRequest;
import com.example.lab2.dto.GetCarResponse;
import com.example.lab2.dto.GetCarsResponse;
import com.example.lab2.dto.UpdateCarRequest;
import com.example.lab2.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.lab2.service.CarService;
import com.example.lab2.service.CustomerService;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@RestController
@RequestMapping("cars")
public class CarController {

    private CarService carService;
    private CustomerService customerService;

    @Autowired
    public CarController(CarService carService, CustomerService customerService) {
        this.carService = carService;
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<GetCarsResponse> getCars() {
        List<Car> all = carService.findAll();
        Function<Collection<Car>, GetCarsResponse> mapper = GetCarsResponse.entityToDtoMapper();
        GetCarsResponse response = mapper.apply(all);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{registration}")
    public ResponseEntity<GetCarResponse> getCar(@PathVariable("registration") String registration) {
        return carService.find(registration)
                .map(value -> ResponseEntity.ok(GetCarResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody CreateCarRequest request, UriComponentsBuilder builder) {

        Car car = CreateCarRequest
                .dtoToEntityMapper(id -> customerService.find(id).orElseThrow())
                .apply(request);
        car = carService.create(car);
        return ResponseEntity.created(builder.pathSegment("api", "cars", "{registration}")
                .buildAndExpand(car.getRegistration()).toUri()).build();


    }

    @DeleteMapping("{registration}")
    public ResponseEntity<Void> deleteCar(@PathVariable("registration") String registration) {
        Optional<Car> car = carService.find(registration);
        if (car.isPresent()) {
            carService.delete(car.get().getRegistration());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{registration}")
    public ResponseEntity<Void> updateCar(@RequestBody UpdateCarRequest request, @PathVariable("registration") String registration) {
        Optional<Car> car = carService.find(registration);
        if (car.isPresent()) {
            UpdateCarRequest.dtoToEntityUpdater().apply(car.get(), request);
            carService.update(car.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
