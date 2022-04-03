package com.example.lab3.car.entity;


import com.example.lab3.customer.entity.Customer;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car implements Serializable {

    @Id
    private String registration;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer; //owner
    private String mark;
    private int productionYear;

}
