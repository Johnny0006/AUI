package com.example.lab2.entity;


import lombok.*;

import javax.persistence.*;
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
@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "customer")
    private List<Car> cars;

    public void addCar(Car car){
        if(this.cars == null) this.cars = new ArrayList<Car>();
        this.cars.add(car);
    }
}
