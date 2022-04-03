package com.example.lab1.entity;


import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    private int id;
    private String name;
    private String surname;
//    private List<Car> cars;
//
//    public void addCar(Car car){
//        if(this.cars == null) this.cars = new ArrayList<Car>();
//        this.cars.add(car);
//    }
}
