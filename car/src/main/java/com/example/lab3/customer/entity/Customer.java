package com.example.lab3.customer.entity;


import com.example.lab3.car.entity.Car;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
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

    @OneToMany(mappedBy = "customer",cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Car> cars;

}
